package com.goodweatherjpa.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.goodweatherjpa.data.PointParser;

class PointTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("GoodWeatherFinderJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}
	
	@Test
	@DisplayName("Database Connection")
	void test_DB_connection() {
		assertNotNull(em);
	}
	
	@Test
	@DisplayName("Point Deserializer")
	void test_Point_deserialize() {
		
		// Point
		String urlString = "https://api.weather.gov/points/39.9884,-105.2336";
		Point point = new Point();
		
		PointParser pointParser = new PointParser();
		point = pointParser.parse(urlString);
		
		em.getTransaction().begin();
		
		Point foundPoint = em.find(Point.class, point.getUri());
		
		// remove existing data
		// later, put in historical table
		if (foundPoint != null) {
			em.remove(foundPoint);
		}
		em.persist(point);
		
//		if (foundPoint == null) {
//			em.persist(point);
//		} else {
//			em.remove(foundPoint);
//			em.persist(point);
////			em.merge(point);
//		}
		em.getTransaction().commit();
		
		foundPoint = em.find(Point.class, point.getUri());
		
		System.out.println(foundPoint);
	}

}
