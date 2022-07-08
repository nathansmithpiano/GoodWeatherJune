package com.goodweatherjpa.entities;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "grid_point")
public class GridPoint {

	@Id
	private String uri;

	private String type;

	@Column(name = "grid_id")
	private String gridId;

	@Column(name = "grid_x")
	private int gridX;

	@Column(name = "grid_y")
	private int gridY;

	@Column(name = "properties_uri")
	private String propertiesUri;

	@Column(name = "properties_at_type")
	private String propertiesAtType;

	@Column(name = "update_time")
	private OffsetDateTime updateTime;

	@Column(name = "valid_times_begin")
	private OffsetDateTime validTimesBegin;

	@Column(name = "valid_times_end")
	private OffsetDateTime validTimesEnd;

	@Column(name = "elevation_unit_code")
	private String elevationUnitCode;

	@Column(name = "elevation")
	private Double elevation;

	private LocalDateTime created;

	// ----- RELATIONSHIPS -----

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "context_id", referencedColumnName = "id")
	private Context context;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "geometry_id", referencedColumnName = "id")
	private Geometry geometry;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "forecast_office_id", referencedColumnName = "id")
	private ForecastOffice forecastOffice;

	@OneToMany(mappedBy = "gridPoint", cascade = { CascadeType.ALL })
	private List<Forecast> forecasts;

	@OneToMany(mappedBy = "gridPoint", cascade = { CascadeType.ALL })
	private List<ValuePairCollection> valuePairCollections;

	public GridPoint() {
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

	public String getGridId() {
		return gridId;
	}

	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
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

	public OffsetDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(OffsetDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public OffsetDateTime getValidTimesBegin() {
		return validTimesBegin;
	}

	public void setValidTimesBegin(OffsetDateTime validTimesBegin) {
		this.validTimesBegin = validTimesBegin;
	}

	public OffsetDateTime getValidTimesEnd() {
		return validTimesEnd;
	}

	public void setValidTimesEnd(OffsetDateTime validTimesEnd) {
		this.validTimesEnd = validTimesEnd;
	}

	public String getElevationUnitCode() {
		return elevationUnitCode;
	}

	public void setElevationUnitCode(String elevationUnitCode) {
		this.elevationUnitCode = elevationUnitCode;
	}

	public Double getElevation() {
		return elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
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

	public ForecastOffice getForecastOffice() {
		return forecastOffice;
	}

	public void setForecastOffice(ForecastOffice forecastOffice) {
		this.forecastOffice = forecastOffice;
	}

	public List<Forecast> getForecasts() {
		return forecasts;
	}

	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}

	public void addForecast(Forecast forecast) {
		if (this.forecasts == null) {
			this.forecasts = new ArrayList<>();
		}
		forecast.setGridPoint(this);
		this.forecasts.add(forecast);
	}

	public void removeForecast(Forecast forecast) {
		if (this.forecasts != null) {
			forecast.setGridPoint(null);
			this.forecasts.remove(forecast);
		}
	}

	public List<ValuePairCollection> getValuePairCollections() {
		return valuePairCollections;
	}

	public void setValuePairCollections(List<ValuePairCollection> valuePairCollections) {
		this.valuePairCollections = valuePairCollections;
	}
	
	public void addValuePairCollection(ValuePairCollection valuePairCollection) {
		if (this.valuePairCollections == null) {
			this.valuePairCollections = new ArrayList<>();
		}
		valuePairCollection.setGridPoint(this);
		this.valuePairCollections.add(valuePairCollection);
	}

	public void removeValuePairCollection(ValuePairCollection valuePairCollection) {
		if (this.valuePairCollections != null) {
			valuePairCollection.setGridPoint(null);
			this.valuePairCollections.remove(valuePairCollection);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(gridId, gridX, gridY);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridPoint other = (GridPoint) obj;
		return Objects.equals(gridId, other.gridId) && gridX == other.gridX && gridY == other.gridY;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("----- GridPoint -----");
//		builder.append("\nuri = ");
//		builder.append(uri);
//		builder.append("\ntype = ");
//		builder.append(type);
//		builder.append("\ngridId = ");
//		builder.append(gridId);
//		builder.append("\ngridX = ");
//		builder.append(gridX);
//		builder.append("\ngridY = ");
//		builder.append(gridY);
//		builder.append("\npropertiesUri = ");
//		builder.append(propertiesUri);
//		builder.append("\npropertiesAtType = ");
//		builder.append(propertiesAtType);
//		builder.append("\nupdateTime = ");
//		builder.append(updateTime);
//		builder.append("\nvalidTimesBegin = ");
//		builder.append(validTimesBegin);
//		builder.append("\nvalidTimesEnd = ");
//		builder.append(validTimesEnd);
//		builder.append("\nelevationUnitCode = ");
//		builder.append(elevationUnitCode);
//		builder.append("\nelevation = ");
//		builder.append(elevation);
//		builder.append("\ncreated = ");
//		builder.append(created);

		// ----- RELATIONSHIPS -----
//		if (context != null) {
//			builder.append("\ncontext.getId() = ");
//			builder.append(context.getId());
//			builder.append("\ncontext.getSource() = ");
//			builder.append(context.getSource());
//			builder.append("\ncontext.getUrl() = ");
//			builder.append(context.getUrl());
//			builder.append("\ncontext.getVersion() = ");
//			builder.append(context.getVersion());
//			builder.append("\ncontext.getWmoUnit() = ");
//			builder.append(context.getWmoUnit());
//			builder.append("\ncontext.getNwsUnit() = ");
//			builder.append(context.getNwsUnit());
//		} else {
//			builder.append("\ncontext = ");
//			builder.append(context);
//		}
//		builder.append("\ngeometry = ");
//		builder.append(geometry);
//		builder.append("\nforecastOffice = ");
//		builder.append(forecastOffice);

//		if (forecasts != null) {
//			builder.append("\nforecasts.size() = ");
//			builder.append(forecasts.size());
//			for (Forecast forecast : forecasts) {
//				builder.append("\n" + forecast);
//			}
//		} else {
//			builder.append("\nforecasts = ");
//			builder.append(forecasts);
//		}
		
		if (valuePairCollections != null) {
			builder.append("\nvaluePairCollections.size() = ");
			builder.append(valuePairCollections.size());
			for (ValuePairCollection valuePairCollection : valuePairCollections) {
				builder.append("\n" + valuePairCollection);
			}
		} else {
			builder.append("\nvaluePairCollections = ");
			builder.append(valuePairCollections);
		}

		builder.append("\n----- END GridPoint -----");
		return builder.toString();
	}

}
