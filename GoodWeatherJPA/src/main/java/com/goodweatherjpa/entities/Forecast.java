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
@Table(name = "forecast")
public class Forecast {

	@Id
	private String uri;

	private String type;
	private OffsetDateTime updated;
	private String units;

	@Column(name = "forecast_generator")
	private String forecastGenerator;

	@Column(name = "generated_at")
	private OffsetDateTime generatedAt;

	@Column(name = "update_time")
	private OffsetDateTime updateTime;

	@Column(name = "valid_times_begin")
	private OffsetDateTime validTimesBegin;

	@Column(name = "valid_times_end")
	private OffsetDateTime validTimesEnd;

	@Column(name = "elevation_unit_code")
	private String elevationUnitCode;

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
	@JoinColumn(name = "grid_point_uri", referencedColumnName = "uri")
	private GridPoint gridPoint;

	@OneToMany(mappedBy = "forecast", cascade = { CascadeType.ALL })
	private List<Period> periods;

	public Forecast() {
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

	public OffsetDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(OffsetDateTime updated) {
		this.updated = updated;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getForecastGenerator() {
		return forecastGenerator;
	}

	public void setForecastGenerator(String forecastGenerator) {
		this.forecastGenerator = forecastGenerator;
	}

	public OffsetDateTime getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(OffsetDateTime generatedAt) {
		this.generatedAt = generatedAt;
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
	
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	// ----- RELATIONSHIPS -----

	public GridPoint getGridPoint() {
		return gridPoint;
	}

	public void setGridPoint(GridPoint gridPoint) {
		this.gridPoint = gridPoint;
	}

	public List<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}
	
	public void addPeriod(Period period) {
		if (this.periods == null) {
			this.periods = new ArrayList<>();
		}
		period.setForecast(this);
		this.periods.add(period);
	}
	
	public void removePeriod(Period period) {
		if (this.periods != null) {
			period.setForecast(null);
			this.periods.remove(period);
		}
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
		Forecast other = (Forecast) obj;
		return Objects.equals(uri, other.uri);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("----- Forecast -----");
		builder.append("\nuri = ");
		builder.append(uri);
		builder.append("\ntype = ");
		builder.append(type);
		builder.append("\nupdated = ");
		builder.append(updated);
		builder.append("\nunits = ");
		builder.append(units);
		builder.append("\nforecastGenerator = ");
		builder.append(forecastGenerator);
		builder.append("\ngeneratedAt = ");
		builder.append(generatedAt);
		builder.append("\nupdateTime = ");
		builder.append(updateTime);
		builder.append("\nvalidTimesBegin = ");
		builder.append(validTimesBegin);
		builder.append("\nvalidTimesEnd = ");
		builder.append(validTimesEnd);
		builder.append("\nelevationUnitCode = ");
		builder.append(elevationUnitCode);
		builder.append("\nelevation = ");
		builder.append(elevation);

		// ----- RELATIONSHIPS -----
//		builder.append("\ncontext.getUrl() = ");
//		builder.append(context.getUrl());
//		builder.append("\ncontext.getVersion() = ");
//		builder.append(context.getVersion());
//		builder.append("\ncontext.getWx() = ");
//		builder.append(context.getWx());
//		builder.append("\ncontext.getGeo() = ");
//		builder.append(context.getGeo());
//		builder.append("\ncontext.getVocab() = ");
//		builder.append(context.getVocab());
//		builder.append("\ncreated = ");
//		builder.append(created);

//		builder.append("\ngeometry = ");
//		builder.append(geometry);
		
		if (periods != null) {
			builder.append("\nperiods.size() = ");
			builder.append(periods.size());
		} else {
			builder.append("\nperiods = ");
			builder.append(periods);
		}
		
		builder.append("\n----- END Forecast -----");
		return builder.toString();
	}

}
