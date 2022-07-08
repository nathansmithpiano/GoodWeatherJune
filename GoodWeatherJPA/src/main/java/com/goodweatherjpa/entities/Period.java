package com.goodweatherjpa.entities;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "period")
public class Period {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int number;
	private String name;

	@Column(name = "start_time")
	private OffsetDateTime startTime;

	@Column(name = "end_time")
	private OffsetDateTime endTime;

	@Column(name = "is_daytime")
	private Boolean isDaytime;

	private double temperature;

	@Column(name = "temperature_unit")
	private String temperatureUnit;

	@Column(name = "temperature_trend")
	private String temperatureTrend;

	@Column(name = "wind_speed_min")
	private Integer windSpeedMin;

	@Column(name = "wind_speed_max")
	private Integer windSpeedMax;

	@Column(name = "wind_speed_unit")
	private String windSpeedUnit;

	@Column(name = "wind_direction")
	private String windDirection;

	@Column(name = "icon_url")
	private String iconUrl;

	@Column(name = "short_forecast")
	private String shortForecast;

	@Column(name = "detailed_forecast")
	private String detailedForecast;

	private LocalDateTime created;

	// ----- RELATIONSHIPS -----
	@ManyToOne
	@JoinColumn(name = "forecast_uri")
	private Forecast forecast;

	public Period() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OffsetDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(OffsetDateTime startTime) {
		this.startTime = startTime;
	}

	public OffsetDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(OffsetDateTime endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsDaytime() {
		return isDaytime;
	}

	public void setIsDaytime(Boolean isDaytime) {
		this.isDaytime = isDaytime;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getTemperatureUnit() {
		return temperatureUnit;
	}

	public void setTemperatureUnit(String temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}

	public String getTemperatureTrend() {
		return temperatureTrend;
	}

	public void setTemperatureTrend(String temperatureTrend) {
		this.temperatureTrend = temperatureTrend;
	}

	public Integer getWindSpeedMin() {
		return windSpeedMin;
	}

	public void setWindSpeedMin(Integer windSpeedMin) {
		this.windSpeedMin = windSpeedMin;
	}

	public Integer getWindSpeedMax() {
		return windSpeedMax;
	}

	public void setWindSpeedMax(Integer windSpeedMax) {
		this.windSpeedMax = windSpeedMax;
	}

	public String getWindSpeedUnit() {
		return windSpeedUnit;
	}

	public void setWindSpeedUnit(String windSpeedUnit) {
		this.windSpeedUnit = windSpeedUnit;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getShortForecast() {
		return shortForecast;
	}

	public void setShortForecast(String shortForecast) {
		this.shortForecast = shortForecast;
	}

	public String getDetailedForecast() {
		return detailedForecast;
	}

	public void setDetailedForecast(String detailedForecast) {
		this.detailedForecast = detailedForecast;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	// ----- RELATIONSHIPS -----

	public Forecast getForecast() {
		return forecast;
	}

	public void setForecast(Forecast forecast) {
		this.forecast = forecast;
	}

	@Override
	public int hashCode() {
		return Objects.hash(forecast, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Period other = (Period) obj;
		return Objects.equals(forecast, other.forecast) && number == other.number;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("----- Period ----- \nid = ");
		builder.append(id);
		builder.append("\nnumber = ");
		builder.append(number);
		builder.append("\nname = ");
		builder.append(name);
		builder.append("\nstartTime = ");
		builder.append(startTime);
		builder.append("\nendTime = ");
		builder.append(endTime);
		builder.append("\nisDaytime = ");
		builder.append(isDaytime);
		builder.append("\ntemperature = ");
		builder.append(temperature);
		builder.append("\ntemperatureUnit = ");
		builder.append(temperatureUnit);
		builder.append("\ntemperatureTrend = ");
		builder.append(temperatureTrend);
		builder.append("\nwindSpeedMin = ");
		builder.append(windSpeedMin);
		builder.append("\nwindSpeedMax = ");
		builder.append(windSpeedMax);
		builder.append("\nwindSpeedUnit = ");
		builder.append(windSpeedUnit);
		builder.append("\nwindDirection = ");
		builder.append(windDirection);
		builder.append("\niconUrl = ");
		builder.append(iconUrl);
		builder.append("\nshortForecast = ");
		builder.append(shortForecast);
		builder.append("\ndetailedForecast = ");
		builder.append(detailedForecast);
		builder.append("\ncreated = ");
		builder.append(created);

		// ----- RELATIONSHIPS -----
		if (forecast != null) {
			builder.append("\nforecast.getUri() = ");
			builder.append(forecast.getUri());
		} else {
			builder.append("\nforecast = ");
			builder.append(forecast);
		}
		builder.append("\n----- END Period -----");
		return builder.toString();
	}

}
