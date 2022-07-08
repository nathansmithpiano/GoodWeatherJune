package com.goodweatherjpa.entities;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "value_pair")
public class ValuePair {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "valid_time_begin")
	private OffsetDateTime validTimeBegin;

	@Column(name = "valid_time_end")
	private OffsetDateTime validTimeEnd;

	private Double value;

	private LocalDateTime created;

	// ----- RELATIONSHIPS -----

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "value_pair_collection_id", referencedColumnName = "id")
	private ValuePairCollection valuePairCollection;

	public ValuePair() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OffsetDateTime getValidTimeBegin() {
		return validTimeBegin;
	}

	public void setValidTimeBegin(OffsetDateTime validTimeBegin) {
		this.validTimeBegin = validTimeBegin;
	}

	public OffsetDateTime getValidTimeEnd() {
		return validTimeEnd;
	}

	public void setValidTimeEnd(OffsetDateTime validTimeEnd) {
		this.validTimeEnd = validTimeEnd;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public ValuePairCollection getValuePairCollection() {
		return valuePairCollection;
	}

	public void setValuePairCollection(ValuePairCollection valuePairCollection) {
		this.valuePairCollection = valuePairCollection;
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
		ValuePair other = (ValuePair) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n-- valuePair (id: " + id + ")");
		builder.append(" validTimeBegin = ");
		builder.append(validTimeBegin);
		builder.append(", validTimeEnd = ");
		builder.append(validTimeEnd);
		builder.append(", value = ");
		builder.append(value);
		builder.append(", created = ");
		builder.append(created);
		builder.append(", valuePairCollection.getId() = ");
		builder.append(valuePairCollection.getId());
		return builder.toString();
	}

}
