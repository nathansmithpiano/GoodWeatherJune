package com.goodweatherjpa.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "context")
public class Context {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String source;

	private String url;
	private String version;
	private String wx;
	private String s;
	private String geo;
	private String unit;
	private String vocab;

	@Column(name = "geometry_id")
	private String geometryId;

	@Column(name = "geometry_type")
	private String geometryType;

	private String city;
	private String state;

	@Column(name = "distance_id")
	private String distanceId;

	@Column(name = "distance_type")
	private String distanceType;

	@Column(name = "bearing_type")
	private String bearingType;

	@Column(name = "value_id")
	private String valueId;

	@Column(name = "unit_code_id")
	private String unitCodeId;

	@Column(name = "unit_code_type")
	private String unitCodeType;

	@Column(name = "forecast_office_type")
	private String forecastOfficeType;

	@Column(name = "forecast_grid_data_type")
	private String forecastGridDataType;

	@Column(name = "public_zone_type")
	private String publicZoneType;

	@Column(name = "county_type")
	private String countyType;

	@Column(name = "wmo_unit")
	private String wmoUnit;

	@Column(name = "nws_unit")
	private String nwsUnit;

	private LocalDateTime created;

	public Context() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getWx() {
		return wx;
	}

	public void setWx(String wx) {
		this.wx = wx;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getVocab() {
		return vocab;
	}

	public void setVocab(String vocab) {
		this.vocab = vocab;
	}

	public String getGeometryId() {
		return geometryId;
	}

	public void setGeometryId(String geometryId) {
		this.geometryId = geometryId;
	}

	public String getGeometryType() {
		return geometryType;
	}

	public void setGeometryType(String geometryType) {
		this.geometryType = geometryType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistanceId() {
		return distanceId;
	}

	public void setDistanceId(String distanceId) {
		this.distanceId = distanceId;
	}

	public String getDistanceType() {
		return distanceType;
	}

	public void setDistanceType(String distanceType) {
		this.distanceType = distanceType;
	}

	public String getBearingType() {
		return bearingType;
	}

	public void setBearingType(String bearingType) {
		this.bearingType = bearingType;
	}

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}

	public String getUnitCodeId() {
		return unitCodeId;
	}

	public void setUnitCodeId(String unitCodeId) {
		this.unitCodeId = unitCodeId;
	}

	public String getUnitCodeType() {
		return unitCodeType;
	}

	public void setUnitCodeType(String unitCodeType) {
		this.unitCodeType = unitCodeType;
	}

	public String getForecastOfficeType() {
		return forecastOfficeType;
	}

	public void setForecastOfficeType(String forecastOfficeType) {
		this.forecastOfficeType = forecastOfficeType;
	}

	public String getForecastGridDataType() {
		return forecastGridDataType;
	}

	public void setForecastGridDataType(String forecastGridDataType) {
		this.forecastGridDataType = forecastGridDataType;
	}

	public String getPublicZoneType() {
		return publicZoneType;
	}

	public void setPublicZoneType(String publicZoneType) {
		this.publicZoneType = publicZoneType;
	}

	public String getCountyType() {
		return countyType;
	}

	public void setCountyType(String countyType) {
		this.countyType = countyType;
	}

	public String getWmoUnit() {
		return wmoUnit;
	}

	public void setWmoUnit(String wmoUnit) {
		this.wmoUnit = wmoUnit;
	}

	public String getNwsUnit() {
		return nwsUnit;
	}

	public void setNwsUnit(String nwsUnit) {
		this.nwsUnit = nwsUnit;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bearingType, city, countyType, created, distanceId, distanceType, forecastGridDataType,
				forecastOfficeType, geo, geometryId, geometryType, id, nwsUnit, publicZoneType, s, source, state, unit,
				unitCodeId, unitCodeType, url, valueId, version, vocab, wmoUnit, wx);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Context other = (Context) obj;
		return Objects.equals(bearingType, other.bearingType) && Objects.equals(city, other.city)
				&& Objects.equals(countyType, other.countyType) && Objects.equals(created, other.created)
				&& Objects.equals(distanceId, other.distanceId) && Objects.equals(distanceType, other.distanceType)
				&& Objects.equals(forecastGridDataType, other.forecastGridDataType)
				&& Objects.equals(forecastOfficeType, other.forecastOfficeType) && Objects.equals(geo, other.geo)
				&& Objects.equals(geometryId, other.geometryId) && Objects.equals(geometryType, other.geometryType)
				&& id == other.id && Objects.equals(nwsUnit, other.nwsUnit)
				&& Objects.equals(publicZoneType, other.publicZoneType) && Objects.equals(s, other.s)
				&& Objects.equals(source, other.source) && Objects.equals(state, other.state)
				&& Objects.equals(unit, other.unit) && Objects.equals(unitCodeId, other.unitCodeId)
				&& Objects.equals(unitCodeType, other.unitCodeType) && Objects.equals(url, other.url)
				&& Objects.equals(valueId, other.valueId) && Objects.equals(version, other.version)
				&& Objects.equals(vocab, other.vocab) && Objects.equals(wmoUnit, other.wmoUnit)
				&& Objects.equals(wx, other.wx);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("----- Context -----");
		builder.append("\nid = ");
		builder.append(id);
		builder.append("\nsource = ");
		builder.append(source);
		builder.append("\nurl = ");
		builder.append(url);
		builder.append("\nversion = ");
		builder.append(version);
		builder.append("\nwx = ");
		builder.append(wx);
		builder.append("\ns = ");
		builder.append(s);
		builder.append("\ngeo = ");
		builder.append(geo);
		builder.append("\nunit = ");
		builder.append(unit);
		builder.append("\nvocab = ");
		builder.append(vocab);
		builder.append("\ngeometryId = ");
		builder.append(geometryId);
		builder.append("\ngeometryType = ");
		builder.append(geometryType);
		builder.append("\ncity = ");
		builder.append(city);
		builder.append("\nstate = ");
		builder.append(state);
		builder.append("\ndistanceId = ");
		builder.append(distanceId);
		builder.append("\ndistanceType = ");
		builder.append(distanceType);
		builder.append("\nbearingType = ");
		builder.append(bearingType);
		builder.append("\nvalueId = ");
		builder.append(valueId);
		builder.append("\nunitCodeId = ");
		builder.append(unitCodeId);
		builder.append("\nunitCodeType = ");
		builder.append(unitCodeType);
		builder.append("\nforecastOfficeType = ");
		builder.append(forecastOfficeType);
		builder.append("\nforecastGridDataType = ");
		builder.append(forecastGridDataType);
		builder.append("\npublicZoneType = ");
		builder.append(publicZoneType);
		builder.append("\ncountyType = ");
		builder.append(countyType);
		builder.append("\nwmoUnit = ");
		builder.append(wmoUnit);
		builder.append("\nnwsUnit = ");
		builder.append(nwsUnit);
		builder.append("\ncreated = ");
		builder.append(created);
		builder.append("\n----- END Context -----");
		return builder.toString();
	}

}
