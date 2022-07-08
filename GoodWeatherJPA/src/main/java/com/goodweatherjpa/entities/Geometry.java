package com.goodweatherjpa.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "geometry")
public class Geometry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;
	private LocalDateTime created;

	// ----- RELATIONSHIPS -----
	
	@OneToMany(mappedBy = "geometry", cascade = { CascadeType.ALL })
	private List<Coordinates> coordinates;

	public Geometry() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	// ----- RELATIONSHIPS -----

	public List<Coordinates> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinates> coordinates) {
		this.coordinates = coordinates;
	}

	public void addCoordinates(Coordinates coordinates) {
		if (this.coordinates == null) {
			this.coordinates = new ArrayList<>();
		}
		coordinates.setGeometry(this);
		this.coordinates.add(coordinates);
	}

	public void removeCoordinates(Coordinates coordinates) {
		if (this.coordinates != null) {
			coordinates.setGeometry(null);
			this.coordinates.remove(coordinates);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordinates, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometry other = (Geometry) obj;
		return Objects.equals(coordinates, other.coordinates) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("----- Geometry ----- \nid = ");
		builder.append(id);
		builder.append("\ntype = ");
		builder.append(type);
		builder.append("\ncreated = ");
		builder.append(created);

		// ----- RELATIONSHIPS -----
		if (this.coordinates != null) {
			builder.append("\ncoordinates.size() = ");
			builder.append(coordinates.size());
			
			for (Coordinates coordinates : this.coordinates) {
				builder.append(coordinates);
			}
		} else {
			builder.append("\ncoordinates = ");
			builder.append(coordinates);
		}

		builder.append("\n----- END Geometry -----");
		return builder.toString();
	}

}
