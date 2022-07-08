package com.goodweatherjpa.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "coordinates")
public class Coordinates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double latitude;
	private double longitude;
	private LocalDateTime created;

	// ----- RELATIONSHIPS -----

	@ManyToOne
	@JoinColumn(name = "geometry_id")
	private Geometry geometry;

	public Coordinates() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	// ----- RELATIONSHIPS -----

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		return Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n-- coordinate (id: " + id + ")");
		builder.append(" latitude = ");
		builder.append(latitude);
		builder.append(", longitude = ");
		builder.append(longitude);
		builder.append(", created = ");
		builder.append(created);
		builder.append(", geometry.getId() = ");
		builder.append(geometry.getId());
		return builder.toString();
	}

}
