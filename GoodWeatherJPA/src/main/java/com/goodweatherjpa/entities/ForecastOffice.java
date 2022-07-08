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
@Table(name = "forecast_office")
public class ForecastOffice {

	@Id
	private String id;

	private String uri;
	private String type;
	private String name;
	private String telephone;

	@Column(name = "fax_number")
	private String faxNumber;

	private String email;

	@Column(name = "same_as")
	private String sameAs;

	@Column(name = "nws_region")
	private String nwsRegion;

	@Column(name = "parent_organization_uri")
	private String parentOrganizationUri;

	private LocalDateTime created;

	// ----- RELATIONSHIPS -----

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "context_id", referencedColumnName = "id")
	private Context context;

	public ForecastOffice() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSameAs() {
		return sameAs;
	}

	public void setSameAs(String sameAs) {
		this.sameAs = sameAs;
	}

	public String getNwsRegion() {
		return nwsRegion;
	}

	public void setNwsRegion(String nwsRegion) {
		this.nwsRegion = nwsRegion;
	}

	public String getParentOrganizationUri() {
		return parentOrganizationUri;
	}

	public void setParentOrganizationUri(String parentOrganizationUri) {
		this.parentOrganizationUri = parentOrganizationUri;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForecastOffice other = (ForecastOffice) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("----- ForecastOffice -----");
		builder.append("\nid = ");
		builder.append(id);
		builder.append("\nuri = ");
		builder.append(uri);
		builder.append("\ntype = ");
		builder.append(type);
		builder.append("\nname = ");
		builder.append(name);
		builder.append("\ntelephone = ");
		builder.append(telephone);
		builder.append("\nfaxNumber = ");
		builder.append(faxNumber);
		builder.append("\nemail = ");
		builder.append(email);
		builder.append("\nsameAs = ");
		builder.append(sameAs);
		builder.append("\nnwsRegion = ");
		builder.append(nwsRegion);
		builder.append("\nparentOrganizationUri = ");
		builder.append(parentOrganizationUri);
		builder.append("\ncreated = ");
		builder.append(created);
		
		// ----- RELATIONSHIPS -----
		builder.append("\ncontext.getVersion() = ");
		builder.append(context.getVersion());
		builder.append("\ncontext.getVocab() = ");
		builder.append(context.getVocab());
		
		builder.append("\n----- END ForecastOffice -----");
		return builder.toString();
	}

}
