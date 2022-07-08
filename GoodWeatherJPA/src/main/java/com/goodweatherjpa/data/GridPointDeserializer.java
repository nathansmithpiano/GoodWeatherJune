package com.goodweatherjpa.data;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.goodweatherjpa.entities.Context;
import com.goodweatherjpa.entities.Coordinates;
import com.goodweatherjpa.entities.CustomError;
import com.goodweatherjpa.entities.ForecastOffice;
import com.goodweatherjpa.entities.Geometry;
import com.goodweatherjpa.entities.GridPoint;
import com.goodweatherjpa.entities.ValuePairCollection;

public class GridPointDeserializer extends StdDeserializer<GridPoint> {

	private static final long serialVersionUID = 1L;
	private DeserializerVerifier dv;

	public GridPointDeserializer() {
		this(null);
	}

	public GridPointDeserializer(Class<GridPoint> vc) {
		super(vc);
		dv = new DeserializerVerifier();
	}

	@Override
	public GridPoint deserialize(JsonParser parser, DeserializationContext deserializer) {

		// for debugging
		final String origin = "com.goodweatherjpa.data.PointDeserializer";
		final String methodName = "Point deserialize(parser, deserializer)";
		String errorType = null;

		// initialize
		ObjectCodec codec = parser.getCodec();
		JsonNode node = null;
		GridPoint gridPoint = null;

		// attempt to parse into JsonNode
		try {
			node = codec.readTree(parser);
		} catch (IOException e) {
			errorType = e.getClass().getSimpleName();
			e.printStackTrace();
		}

		if (errorType != null) {
			String details = "codec.readTree(parser) threw IOException";
			dv.customError(null, null, origin, methodName, details);
		}

		gridPoint = createGridPoint(node);

		if (gridPoint == null) {
			String details = "gridPoint == null, issue with deserialization";
			dv.customError(null, null, origin, methodName, details);
		}

		// print errors, if any
		for (CustomError customError : dv.getErrorLogger().getErrorList()) {
			System.err.println(customError);
		}

		return gridPoint;
	}

	private GridPoint createGridPoint(JsonNode node) {

		// for debugging
		final String origin = "com.goodweatherjpa.data.GridPointDeserializer";
		final String methodName = "GridPoint createGridPoint(node)";

		GridPoint entity = new GridPoint();
		String prop = null;
		String propParent = null;

		// @context
		prop = "@context";
		if (dv.verifyNodeHasPropAndIsArrayOfLength(node, prop, propParent, 2, origin, methodName)) {
			Context context = new Context();
			context.setSource(entity.getClass().getSimpleName());
			entity.setContext(context);

			JsonNode subNode = node.get(prop);

			// URL, index 0
			propParent = null;
			int index = 0;
			prop = prop + "[" + index + "]";
			if (dv.verifyNodeIsTextual(subNode.get(0), prop + "[0]", propParent, origin, methodName)) {
				if (!subNode.get(0).isNull()) {
					context.setUrl(subNode.get(0).asText());
				}
			}

			// Object of other values, index 1
			propParent = null;
			int length = 3;
			index = 1;
			if (dv.verifyNodeIsObjectOfLength(subNode.get(1), prop + "[1]", propParent, length, origin, methodName)) {
				subNode = subNode.get(1);
				propParent = prop;

				// @version
				prop = "@version";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setVersion(subNode.get(prop).asText());
					}
				}

				// wmoUnit
				prop = "wmoUnit";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setWmoUnit(subNode.get(prop).asText());
					}
				}

				// nwsUnit
				prop = "nwsUnit";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setNwsUnit(subNode.get(prop).asText());
					}
				}
			}
		}

		// @id
		prop = "id";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setUri(node.get(prop).asText());
			}
		}

		// type
		prop = "type";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setType(node.get(prop).asText());
			}
		}

		// geometry
		prop = "geometry";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsObjectOfLength(node, prop, propParent, 2, origin, methodName)) {
			JsonNode subNode = node.get(prop);
			propParent = prop;
			Geometry geometry = new Geometry();
			entity.setGeometry(geometry);

			// type
			prop = "type";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					geometry.setType(subNode.get(prop).asText());
				}
			}

			// coordinates
			prop = "coordinates";
			int length = 1;
			if (dv.verifyNodeHasPropAndIsArrayOfLength(subNode, prop, propParent, length, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					JsonNode subNode2 = subNode.get(prop);
					prop = prop + "[" + 0 + "]";
					length = 1;

					if (dv.verifyNodeIsArrayOfLength(subNode2, prop, propParent, length, origin, methodName)) {
						prop = prop + "[" + 0 + "]";
						subNode2 = subNode2.get(0);
						length = 5;

						if (dv.verifyNodeIsArrayOfLength(subNode2, prop, propParent, length, origin, methodName)) {

							for (int i = 0; i < subNode2.size(); i++) {
								JsonNode coordinateNode = subNode2.get(i);
								length = 2;

								if (dv.verifyNodeIsArrayOfLength(coordinateNode, prop, propParent, length, origin,
										methodName)) {
									Coordinates coordinates = new Coordinates();
									coordinates.setLatitude(coordinateNode.get(1).asDouble());
									coordinates.setLongitude(coordinateNode.get(0).asDouble());
									coordinates.setGeometry(geometry);
									geometry.addCoordinates(coordinates);
								}

							}
						}
					}
				}
			}

			propParent = null;
		}

		// properties
		prop = "properties";
		propParent = null;
		int length = 66;
		if (dv.verifyNodeHasPropAndIsObjectOfLength(node, prop, propParent, length, origin, methodName)) {
			JsonNode subNode = node.get(prop);
			propParent = prop;
			
			// used for collections
			Iterator<String> it = subNode.fieldNames();
			List<String> fieldNames = new ArrayList<>();
			
			while (it.hasNext()) {
				String fieldName = it.next();
				fieldNames.add(fieldName);
			}

			// @id
			prop = "@id";
			fieldNames.remove(prop); // used for collections only
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					entity.setPropertiesUri(subNode.get(prop).asText());
				}

				// verify properties.@id and id are equal
				String prop1 = entity.getPropertiesUri();
				String prop2 = entity.getUri();
				String prop2Name = "id";
				dv.verifyStringsAreEqual(prop1, prop2, prop, prop2Name, origin, methodName);
			}

			// @type
			prop = "@type";
			fieldNames.remove(prop); // used for collections only
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					entity.setPropertiesAtType(subNode.get(prop).asText());
				}
			}

			// updateTime
			prop = "updateTime";
			fieldNames.remove(prop); // used for collections only
			if (dv.verifyNodeHasPropAndIsParsableToOffsetDateTime(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String odtString = subNode.get(prop).asText();
					entity.setUpdateTime(OffsetDateTime.parse(odtString));
				}
			}

			// validTimes
			prop = "validTimes";
			fieldNames.remove(prop); // used for collections only
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String validTimesString = subNode.get(prop).asText();
					int delimIndex = validTimesString.indexOf("/");
					String validTimesBeginString = validTimesString.substring(0, delimIndex);
					String periodString = validTimesString.substring(delimIndex + 1);

					if (dv.verifyStringIsParsableToOffsetDateTime(validTimesBeginString, prop, propParent, origin,
							methodName)) {
						entity.setValidTimesBegin(OffsetDateTime.parse(validTimesBeginString));
					}

					if (dv.verifyStringIsPeriod(periodString, prop, propParent, origin, methodName)) {
						int pIndex = periodString.indexOf("P");
						int dtIndex = periodString.indexOf("DT");
						int hIndex = periodString.indexOf("H");
						int days = Integer.parseInt(periodString.substring(pIndex + 1, dtIndex));
						int hours = Integer.parseInt(periodString.substring(dtIndex + 2, hIndex));

						OffsetDateTime validTimesEnd = OffsetDateTime.parse(validTimesBeginString);
						validTimesEnd = validTimesEnd.plusDays(days);
						validTimesEnd = validTimesEnd.plusHours(hours);
						entity.setValidTimesEnd(validTimesEnd);
					}
				}
			}

			// elevation
			prop = "elevation";
			fieldNames.remove(prop); // used for collections only
			length = 2;
			if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
				String propParent2 = prop;
				JsonNode subNode2 = subNode.get(prop);

				prop = "unitCode";
				if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
					if (!subNode2.get(prop).isNull()) {
						entity.setElevationUnitCode(subNode2.get(prop).asText());
					}
				}

				prop = "value";
				if (dv.verifyNodeHasPropAndIsADouble(subNode2, prop, propParent2, origin, methodName)) {
					if (!subNode2.get(prop).isNull()) {
						entity.setElevation(subNode2.get(prop).asDouble());
					}
				}
			}

			// forecastOffice
			prop = "forecastOffice";
			fieldNames.remove(prop); // used for collections only
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String uri = subNode.get(prop).asText();
					ForecastOfficeParser forecastOfficeParser = new ForecastOfficeParser();
					ForecastOffice forecastOffice = forecastOfficeParser.parse(uri);
					entity.setForecastOffice(forecastOffice);
				}

			}

			// gridId
			prop = "gridId";
			fieldNames.remove(prop); // used for collections only
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					entity.setGridId(subNode.get(prop).asText());
				}
			}

			// gridX
			prop = "gridX";
			fieldNames.remove(prop); // used for collections only
			if (dv.verifyNodeHasPropAndIsParsableToAnInt(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					int gridX = Integer.parseInt(subNode.get(prop).asText());
					entity.setGridX(gridX);
				}
			}

			// gridY
			prop = "gridY";
			fieldNames.remove(prop); // used for collections only
			if (dv.verifyNodeHasPropAndIsParsableToAnInt(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					int gridY = Integer.parseInt(subNode.get(prop).asText());
					entity.setGridY(gridY);
				}
			}

			// ValuePairCollections
			ValuePairParser valuePairParser = new ValuePairParser();
			
			for (String fieldName : fieldNames) {
				prop = fieldName;
				
				JsonNode tempNode = subNode.get(prop);
				
				ValuePairCollection collection = valuePairParser.parse(subNode, prop, propParent, origin, methodName, dv);
				
				if (collection != null) {
					entity.addValuePairCollection(collection);
				}
				
			}
			
			
			
//			ValuePairParser valuePairParser = new ValuePairParser();
//			ValuePairCollection dewpoint = valuePairParser.parse(subNode, prop, propParent, origin, methodName, dv);
//			if (dewpoint != null) {
//				entity.addValuePairCollection(dewpoint);
//			}

//			// dewpoint (ValuePairCollection)
//			prop = "dewpoint";
//			ValuePairParser valuePairParser = new ValuePairParser();
//			ValuePairCollection dewpoint = valuePairParser.parse(subNode, prop, propParent, origin, methodName, dv);
//			if (dewpoint != null) {
//				entity.addValuePairCollection(dewpoint);
//			}
		}

		return entity;
	}

}
