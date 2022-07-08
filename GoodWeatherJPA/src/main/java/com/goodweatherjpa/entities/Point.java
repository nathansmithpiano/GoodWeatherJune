package com.goodweatherjpa.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "point")
public class Point {

	@Id
	private String uri;

	private String type;

	@Column(name = "properties_uri")
	private String propertiesUri;

	@Column(name = "properties_at_type")
	private String propertiesAtType;

	@Column(name = "forecast_office_uri")
	private String forecastOfficeUri;

	private LocalDateTime created;

	// ----- RELATIONSHIPS -----

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "context_id", referencedColumnName = "id")
	private Context context;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "geometry_id", referencedColumnName = "id")
	private Geometry geometry;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "grid_point_uri", referencedColumnName = "uri")
	private GridPoint gridPoint;

	public Point() {
		super();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPropertiesUri() {
		return propertiesUri;
	}

	public void setPropertiesUri(String propertiesUri) {
		this.propertiesUri = propertiesUri;
	}

	public String getPropertiesAtType() {
		return propertiesAtType;
	}

	public void setPropertiesAtType(String propertiesAtType) {
		this.propertiesAtType = propertiesAtType;
	}

	public String getForecastOfficeUri() {
		return forecastOfficeUri;
	}

	public void setForecastOfficeUri(String forecastOfficeUri) {
		this.forecastOfficeUri = forecastOfficeUri;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	// ----- RELATIONSHIPS -----

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public GridPoint getGridPoint() {
		return gridPoint;
	}

	public void setGridPoint(GridPoint gridPoint) {
		this.gridPoint = gridPoint;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uri);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return Objects.equals(uri, other.uri);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("----- Point -----");
		builder.append("\nuri = ");
		builder.append(uri);
		builder.append("\ntype = ");
		builder.append(type);
		builder.append("\npropertiesUri = ");
		builder.append(propertiesUri);
		builder.append("\npropertiesAtType = ");
		builder.append(propertiesAtType);
		builder.append("\nforecastOfficeUri = ");
		builder.append(forecastOfficeUri);
		builder.append("\ncreated = ");
		builder.append(created);

		// ----- RELATIONSHIPS -----
//		builder.append("\n" + context);
//		builder.append("\n" + geometry);
		builder.append("\n" + gridPoint);

		builder.append("\n----- END Point -----");
		return builder.toString();
	}

}