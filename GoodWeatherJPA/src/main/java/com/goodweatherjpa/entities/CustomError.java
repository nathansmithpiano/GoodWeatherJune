package com.goodweatherjpa.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "custom_error")
public class CustomError {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String origin;
	private String method;
	private String type;
	private String details;
	private LocalDateTime created;

	public CustomError() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created, details, id, method, origin, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomError other = (CustomError) obj;
		return Objects.equals(created, other.created) && Objects.equals(details, other.details) && id == other.id
				&& Objects.equals(method, other.method) && Objects.equals(origin, other.origin)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("----- CustomError -----");
		builder.append("\nid = ");
		builder.append(id);
		builder.append("\norigin = ");
		builder.append(origin);
		builder.append("\nmethod = ");
		builder.append(method);
		builder.append("\ntype = ");
		builder.append(type);
		builder.append("\ndetails = ");
		builder.append(details);
		builder.append("\ncreated = ");
		builder.append(created);
		builder.append("\n----- END CustomError -----");
		return builder.toString();
	}

}
