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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "value_pair_collection")
public class ValuePairCollection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;
	private String uom;
	private LocalDateTime created;

	// ----- RELATIONSHIPS -----

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "grid_point_uri", referencedColumnName = "uri")
	private GridPoint gridPoint;

	@OneToMany(mappedBy = "valuePairCollection", cascade = { CascadeType.ALL })
	private List<ValuePair> valuePairs;

	public ValuePairCollection() {
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

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
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

	public List<ValuePair> getValuePairs() {
		return valuePairs;
	}

	public void setValuePairs(List<ValuePair> valuePairs) {
		this.valuePairs = valuePairs;
	}

	public void addValuePair(ValuePair valuePair) {
		if (this.valuePairs == null) {
			this.valuePairs = new ArrayList<>();
		}
		valuePair.setValuePairCollection(this);
		this.valuePairs.add(valuePair);
	}

	public void removeValuePair(ValuePair valuePair) {
		if (this.valuePairs != null) {
			valuePair.setValuePairCollection(null);
			this.valuePairs.remove(valuePair);
		}
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
		ValuePairCollection other = (ValuePairCollection) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("----- ValuePairCollection -----");
		builder.append("\nid = ");
		builder.append(id);
		builder.append("\ntype = ");
		builder.append(type);
		builder.append("\nuom = ");
		builder.append(uom);
		builder.append("\ncreated = ");
		builder.append(created);

		// ----- RELATIONSHIPS -----
		if (gridPoint != null) {
			builder.append("\ngridPoint.getUri() = ");
			builder.append(gridPoint.getUri());
		} else {
			builder.append("\ngridPoint = ");
			builder.append(gridPoint);
		}
		
		if (this.valuePairs != null) {
			builder.append("\nvaluePairs.size() = ");
			builder.append(valuePairs.size());

			for (ValuePair valuePair : this.valuePairs) {
				builder.append(valuePair);
			}
		} else {
			builder.append("\nvaluePairs = ");
			builder.append(valuePairs);
		}
		builder.append("\n----- END ValuePairCollection -----");
		return builder.toString();
	}

}
