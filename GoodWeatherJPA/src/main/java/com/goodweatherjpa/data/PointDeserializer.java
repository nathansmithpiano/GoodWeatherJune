package com.goodweatherjpa.data;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.goodweatherjpa.entities.Context;
import com.goodweatherjpa.entities.Coordinates;
import com.goodweatherjpa.entities.CustomError;
import com.goodweatherjpa.entities.Forecast;
import com.goodweatherjpa.entities.ForecastOffice;
import com.goodweatherjpa.entities.Geometry;
import com.goodweatherjpa.entities.GridPoint;
import com.goodweatherjpa.entities.Point;

public class PointDeserializer extends StdDeserializer<Point> {

	private static final long serialVersionUID = 1L;
	private DeserializerVerifier dv;

	public PointDeserializer() {
		this(null);
	}

	public PointDeserializer(Class<Point> vc) {
		super(vc);
		dv = new DeserializerVerifier();
	}

	@Override
	public Point deserialize(JsonParser parser, DeserializationContext deserializer) {

		// for debugging
		final String origin = "com.goodweatherjpa.data.PointDeserializer";
		final String methodName = "Point deserialize(parser, deserializer)";
		String errorType = null;

		// initialize
		ObjectCodec codec = parser.getCodec();
		JsonNode node = null;
		Point point = null;

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

		point = createPoint(node);

		if (point == null) {
			String details = "point == null, issue with deserialization";
			dv.customError(null, null, origin, methodName, details);
		}

		// print errors, if any
		for (CustomError customError : dv.getErrorLogger().getErrorList()) {
			System.err.println(customError);
		}

		return point;
	}

	private Point createPoint(JsonNode node) {

		// for debugging
		final String origin = "com.goodweatherjpa.data.PointDeserializer";
		final String methodName = "Point createPoint(node)";

		Point entity = new Point();
		String prop = null;
		String propParent = null;

		// @id
		prop = "id";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setUri(node.get(prop).asText());
			}
		}

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
			int length = 17;
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

				// wx
				prop = "wx";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setWx(subNode.get(prop).asText());
					}
				}

				// s
				prop = "s";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setS(subNode.get(prop).asText());
					}
				}

				// geo
				prop = "geo";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setGeo(subNode.get(prop).asText());
					}
				}

				// unit
				prop = "unit";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setUnit(subNode.get(prop).asText());
					}
				}

				// @vocab
				prop = "@vocab";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setVocab(subNode.get(prop).asText());
					}
				}

				// geometry
				prop = "geometry";
				length = 2;
				if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
					JsonNode subNode2 = subNode.get(prop);
					String propParent2 = propParent + "." + prop;

					prop = "@id";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setGeometryId(subNode2.get(prop).asText());
						}
					}

					prop = "@type";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setGeometryType(subNode2.get(prop).asText());
						}
					}
				}

				// city
				prop = "city";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setCity(subNode.get(prop).asText());
					}
				}

				// state
				prop = "state";
				if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
					if (!subNode.get(prop).isNull()) {
						context.setState(subNode.get(prop).asText());
					}
				}

				// distance
				prop = "distance";
				length = 2;
				if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
					JsonNode subNode2 = subNode.get(prop);
					String propParent2 = propParent + "." + prop;

					prop = "@id";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setDistanceId(subNode2.get(prop).asText());
						}
					}

					prop = "@type";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setDistanceType(subNode2.get(prop).asText());
						}
					}
				}

				// bearing
				prop = "bearing";
				length = 1;
				if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
					JsonNode subNode2 = subNode.get(prop);
					String propParent2 = propParent + "." + prop;

					prop = "@type";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setBearingType(subNode2.get(prop).asText());
						}
					}
				}

				// value
				prop = "value";
				length = 1;
				if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
					JsonNode subNode2 = subNode.get(prop);
					String propParent2 = propParent + "." + prop;

					prop = "@id";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setValueId(subNode2.get(prop).asText());
						}
					}
				}

				// unitCode
				prop = "unitCode";
				length = 2;
				if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
					JsonNode subNode2 = subNode.get(prop);
					String propParent2 = propParent + "." + prop;

					prop = "@id";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setUnitCodeId(subNode2.get(prop).asText());
						}
					}

					prop = "@type";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setUnitCodeType(subNode2.get(prop).asText());
						}
					}
				}

				// forecastOffice
				prop = "forecastOffice";
				length = 1;
				if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
					JsonNode subNode2 = subNode.get(prop);
					String propParent2 = propParent + "." + prop;

					prop = "@type";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setForecastOfficeType(subNode2.get(prop).asText());
						}
					}
				}

				// forecastGridData
				prop = "forecastGridData";
				length = 1;
				if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
					JsonNode subNode2 = subNode.get(prop);
					String propParent2 = propParent + "." + prop;

					prop = "@type";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setForecastGridDataType(subNode2.get(prop).asText());
						}
					}
				}

				// publicZone
				prop = "publicZone";
				length = 1;
				if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
					JsonNode subNode2 = subNode.get(prop);
					String propParent2 = propParent + "." + prop;

					prop = "@type";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setPublicZoneType(subNode2.get(prop).asText());
						}
					}
				}

				// county
				prop = "county";
				length = 1;
				if (dv.verifyNodeHasPropAndIsObjectOfLength(subNode, prop, propParent, length, origin, methodName)) {
					JsonNode subNode2 = subNode.get(prop);
					String propParent2 = propParent + "." + prop;

					prop = "@type";
					if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent2, origin, methodName)) {
						if (!subNode2.get(prop).isNull()) {
							context.setCountyType(subNode2.get(prop).asText());
						}
					}
				}

				propParent = null;
				subNode = null;
			}
		}

		// id (uri)
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
			int length = 2;
			if (dv.verifyNodeHasPropAndIsArrayOfLength(subNode, prop, propParent, length, origin, methodName)) {
				subNode = subNode.get(prop);

				if (!subNode.isNull()) {
					for (int i = 0; i < subNode.size() / 2; i++) {
						JsonNode latitudeNode = subNode.get(i + 1);
						JsonNode longitudeNode = subNode.get(i);

						prop = prop + "[" + (i + 1) + "]";
						if (dv.verifyNodeIsADouble(latitudeNode, prop, propParent, origin, methodName)) {
							prop = prop + "[" + i + "]";

							if (dv.verifyNodeIsADouble(longitudeNode, prop, propParent, origin, methodName)) {
								Coordinates coordinates = new Coordinates();
								if (!latitudeNode.isNull()) {
									coordinates.setLatitude(latitudeNode.asDouble());
								}
								if (!longitudeNode.isNull()) {
									coordinates.setLongitude(longitudeNode.asDouble());
								}
								geometry.addCoordinates(coordinates);
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
		int length = 17;
		if (dv.verifyNodeHasPropAndIsObjectOfLength(node, prop, propParent, length, origin, methodName)) {
			JsonNode subNode = node.get(prop);
			propParent = prop;

			// @id
			prop = "@id";
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
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					entity.setPropertiesAtType(subNode.get(prop).asText());
				}
			}

			// forecastGridData (GridPoint)
			prop = "forecastGridData";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String uri = subNode.get(prop).asText();
					GridPointParser gridPointParser = new GridPointParser();
					GridPoint gridPoint = gridPointParser.parse(uri);
					entity.setGridPoint(gridPoint);
				}
			}

			// forecastOffice
			prop = "forecastOffice";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String uri = subNode.get(prop).asText();
					entity.setForecastOfficeUri(uri);
					
					// verify properties.forecastOffice (uri) and gridPoint.getForecastOffice().getUri() are
					// equal
					String prop2 = entity.getGridPoint().getForecastOffice().getUri();
					String prop2Name = "entity.getGridPoint().getForecastOffice().getUri()";
					dv.verifyStringsAreEqual(uri, prop2, prop, prop2Name, origin, methodName);
				}
			}

			// cwa (GridPoint)
			prop = "cwa";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {

					// verify properties.gridId and gridPoint.getForecastOffice().getGridId() are
					// equal
					String cwa = subNode.get(prop).asText();
					String prop2 = entity.getGridPoint().getGridId();
					String prop2Name = "forecastOffice.getGridId()";
					dv.verifyStringsAreEqual(cwa, prop2, prop, prop2Name, origin, methodName);
				}
			}

			// gridId (GridPoint)
			prop = "gridId";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {

					// verify properties.gridId and gridPoint.getForecastOffice().getGridId() are
					// equal
					String gridId = subNode.get(prop).asText();
					String prop2 = entity.getGridPoint().getGridId();
					String prop2Name = "forecastOffice.getGridId()";
					dv.verifyStringsAreEqual(gridId, prop2, prop, prop2Name, origin, methodName);
				}
			}

			// gridX (GridPoint)
			prop = "gridX";
			if (dv.verifyNodeHasPropAndIsADouble(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {

					// verify properties.gridX and gridPoint.getForecastOffice().getGridX() are
					// equal
					String gridX = subNode.get(prop).asText();
					String prop2 = entity.getGridPoint().getGridX() + "";
					String prop2Name = "forecastOffice.getGridX()";
					dv.verifyStringsAreEqual(gridX, prop2, prop, prop2Name, origin, methodName);
				}
			}

			// gridY (GridPoint)
			prop = "gridY";
			if (dv.verifyNodeHasPropAndIsADouble(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {

					// verify properties.gridY and gridPoint.getForecastOffice().getGridY() are
					// equal
					String gridY = subNode.get(prop).asText();
					String prop2 = entity.getGridPoint().getGridY() + "";
					String prop2Name = "forecastOffice.getGridY()";
					dv.verifyStringsAreEqual(gridY, prop2, prop, prop2Name, origin, methodName);
				}
			}

			// forecast
			prop = "forecast";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String uri = subNode.get(prop).asText();
					ForecastParser forecastParser = new ForecastParser();
					Forecast forecast = forecastParser.parse(uri);
					forecast.setUri(uri);
					entity.getGridPoint().addForecast(forecast);
				}
			}

			// forecast
			prop = "forecastHourly";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String uri = subNode.get(prop).asText();
					ForecastParser forecastParser = new ForecastParser();
					Forecast forecast = forecastParser.parse(uri);
					forecast.setUri(uri);
					entity.getGridPoint().addForecast(forecast);
				}
			}

		}

		return entity;
	}

}
