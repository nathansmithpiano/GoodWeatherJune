package com.goodweatherjpa.data;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.goodweatherjpa.entities.Context;
import com.goodweatherjpa.entities.Coordinates;
import com.goodweatherjpa.entities.CustomError;
import com.goodweatherjpa.entities.Forecast;
import com.goodweatherjpa.entities.Geometry;
import com.goodweatherjpa.entities.Period;

public class ForecastDeserializer extends StdDeserializer<Forecast> {

	private static final long serialVersionUID = 1L;
	private DeserializerVerifier dv;

	public ForecastDeserializer() {
		this(null);
	}

	public ForecastDeserializer(Class<Forecast> vc) {
		super(vc);
		dv = new DeserializerVerifier();
	}

	@Override
	public Forecast deserialize(JsonParser parser, DeserializationContext deserializer) {

		// for debugging
		final String origin = "com.goodweatherjpa.data.ForecastDeserializer";
		final String methodName = "Forecast deserialize(parser, deserializer)";
		String errorType = null;

		// initialize
		ObjectCodec codec = parser.getCodec();
		JsonNode node = null;
		Forecast forecast = null;

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

		forecast = createForecast(node);

		if (forecast == null) {
			String details = "forecast == null, issue with deserialization";
			dv.customError(null, null, origin, methodName, details);
		}

		// print errors, if any
		for (CustomError customError : dv.getErrorLogger().getErrorList()) {
			System.err.println(customError);
		}

		return forecast;
	}

	private Forecast createForecast(JsonNode node) {

		// for debugging
		final String origin = "com.goodweatherjpa.data.ForecastDeserializer";
		final String methodName = "Forecast createForecast(node)";
		DeserializerVerifier dv = new DeserializerVerifier();

		Forecast entity = new Forecast();
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
			int length = 5;
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
			}

			propParent = null;
			subNode = null;
		}

		// type
		prop = "type";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (node.get(prop) != null) {
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
		int length = 8;
		if (dv.verifyNodeHasPropAndIsObjectOfLength(node, prop, propParent, length, origin, methodName)) {
			JsonNode subNode = node.get(prop);
			propParent = prop;

			// updated
			prop = "updated";
			if (dv.verifyNodeHasPropAndIsParsableToOffsetDateTime(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String odtString = subNode.get(prop).asText();
					entity.setUpdated(OffsetDateTime.parse(odtString));
				}
			}

			// units
			prop = "units";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					entity.setUnits(subNode.get(prop).asText());
				}
			}

			// forecastGenerator
			prop = "forecastGenerator";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					entity.setForecastGenerator(subNode.get(prop).asText());
				}
			}

			// generatedAt
			prop = "generatedAt";
			if (dv.verifyNodeHasPropAndIsParsableToOffsetDateTime(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String odtString = subNode.get(prop).asText();
					entity.setGeneratedAt(OffsetDateTime.parse(odtString));
				}
			}

			// updateTime
			prop = "updateTime";
			if (dv.verifyNodeHasPropAndIsParsableToOffsetDateTime(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					String odtString = subNode.get(prop).asText();
					entity.setUpdateTime(OffsetDateTime.parse(odtString));
				}
			}

			// validTimes
			prop = "validTimes";
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

			// periods
			prop = "periods";
			length = 14;
			int hourlyLength = 156;
			if (dv.verifyNodeHasProp(subNode, prop, propParent, origin, methodName)) {
				JsonNode subNode2 = subNode.get(prop);

				if (subNode2.size() != length && subNode2.size() != hourlyLength) {
					String details = "periods.size() is not 14 or 156";
					dv.customError(prop, propParent, origin, methodName, details);
				} else {

					length = subNode2.size();
					if (dv.verifyNodeIsArrayOfLength(subNode2, prop, propParent, length, origin, methodName)) {

						for (int i = 0; i < length; i++) {
							JsonNode periodNode = subNode2.get(i);

							int periodLength = 13;

							if (dv.verifyNodeIsObjectOfLength(periodNode, prop, propParent, periodLength, origin,
									methodName)) {
								propParent = "periods" + "[" + i + "]";
								Period period = new Period();
								entity.addPeriod(period);

								// number
								prop = "number";
								if (dv.verifyNodeHasPropAndIsAnInt(periodNode, prop, propParent, origin, methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setNumber(periodNode.get(prop).asInt());
									}
								}

								// name
								prop = "name";
								if (dv.verifyNodeHasPropAndIsTextual(periodNode, prop, propParent, origin,
										methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setName(periodNode.get(prop).asText());
									}
								}

								// startTime
								prop = "startTime";
								if (dv.verifyNodeHasPropAndIsParsableToOffsetDateTime(periodNode, prop, propParent,
										origin, methodName)) {
									if (!periodNode.get(prop).isNull()) {
										String odtString = periodNode.get(prop).asText();
										period.setStartTime(OffsetDateTime.parse(odtString));
									}
								}

								// endTime
								prop = "endTime";
								if (dv.verifyNodeHasPropAndIsParsableToOffsetDateTime(periodNode, prop, propParent,
										origin, methodName)) {
									if (!periodNode.get(prop).isNull()) {
										String odtString = periodNode.get(prop).asText();
										period.setEndTime(OffsetDateTime.parse(odtString));
									}
								}

								// isDaytime
								prop = "isDaytime";
								if (dv.verifyNodeHasPropAndIsABoolean(periodNode, prop, propParent, origin,
										methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setIsDaytime(periodNode.get(prop).asBoolean());
									}
								}

								// temperature
								prop = "temperature";
								if (dv.verifyNodeHasPropAndIsADouble(periodNode, prop, propParent, origin,
										methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setTemperature(periodNode.get(prop).asDouble());
									}
								}

								// temperatureUnit
								prop = "temperatureUnit";
								if (dv.verifyNodeHasPropAndIsTextual(periodNode, prop, propParent, origin,
										methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setTemperatureUnit(periodNode.get(prop).asText());
									}
								}

								// temperatureTrend
								prop = "temperatureTrend";
								if (dv.verifyNodeHasPropAndIsTextual(periodNode, prop, propParent, origin,
										methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setTemperatureTrend(periodNode.get(prop).asText());
									}
								}

								// windSpeed
								prop = "windSpeed";
								if (dv.verifyNodeHasPropAndIsWindSpeed(periodNode, prop, propParent, origin,
										methodName)) {

									if (!periodNode.get(prop).isNull()) {
										String str = periodNode.get(prop).asText();

										// split into groups of numbers and add to list
										String regex = "[0-9]+";
										Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
										Matcher matcher = pattern.matcher(str);

										List<String> matches = new ArrayList<>();
										while (matcher.find()) {
											matches.add(matcher.group(0));
										}

										// parse as Integers and set to period
										int min = Integer.parseInt(matches.get(0));
										int max = min;

										if (matches.size() == 2) {
											max = Integer.parseInt(matches.get(1));
										}

										String unit = str.substring(str.lastIndexOf(' ') + 1, str.length());

										period.setWindSpeedMin(min);
										period.setWindSpeedMax(max);
										period.setWindSpeedUnit(unit);
									}
								}

								// windDirection
								prop = "windDirection";
								if (dv.verifyNodeHasPropAndIsTextual(periodNode, prop, propParent, origin,
										methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setWindDirection(periodNode.get(prop).asText());
									}
								}

								// icon
								prop = "icon";
								if (dv.verifyNodeHasPropAndIsTextual(periodNode, prop, propParent, origin,
										methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setIconUrl(periodNode.get(prop).asText());
									}
								}

								// shortForecast
								prop = "shortForecast";
								if (dv.verifyNodeHasPropAndIsTextual(periodNode, prop, propParent, origin,
										methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setShortForecast(periodNode.get(prop).asText());
									}
								}

								// detailedForecast
								prop = "detailedForecast";
								if (dv.verifyNodeHasPropAndIsTextual(periodNode, prop, propParent, origin,
										methodName)) {
									if (!periodNode.get(prop).isNull()) {
										period.setDetailedForecast(periodNode.get(prop).asText());
									}
								}
							}
						}
					}
				}
			}
		}

		return entity;
	}

}
