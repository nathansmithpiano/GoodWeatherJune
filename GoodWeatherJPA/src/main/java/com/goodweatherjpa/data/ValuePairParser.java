package com.goodweatherjpa.data;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.goodweatherjpa.entities.ValuePair;
import com.goodweatherjpa.entities.ValuePairCollection;

public class ValuePairParser {

	public ValuePairCollection parse(JsonNode node, String prop, String propParent, String origin, String methodName,
			DeserializerVerifier dv) {
		// pair has default length of 2 (validTime and value)
//		int length = 2;

		ValuePairCollection collection = null;
		
		if (dv.verifyNodeHasPropAndIsObject(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				collection = parseValuePair(node, prop, propParent, origin, methodName, dv);
			}
			JsonNode subNode = node.get(prop);
			
			System.err.println(prop + ", size = " + subNode.size());
			
			// ValuePair
			if (subNode.size() == 2) {
				
			}
		}

		return collection;

	}
	
	private ValuePairCollection parseValuePair(JsonNode node, String prop, String propParent, String origin, String methodName, DeserializerVerifier dv) {
		ValuePairCollection collection = new ValuePairCollection();
		
		int length = 2;
		if (dv.verifyNodeHasPropAndIsObjectOfLength(node, prop, propParent, length, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				
				JsonNode subNode2 = node.get(prop);
				collection = new ValuePairCollection();

				// type
				collection.setType(prop);

				// uom
				prop = "uom";
				if (dv.verifyNodeHasPropAndIsTextual(subNode2, prop, propParent, origin, methodName)) {
					if (!subNode2.get(prop).isNull()) {
						collection.setUom(subNode2.get(prop).asText());
					}
				}

				// values
				prop = "values";
				if (dv.verifyNodeHasPropAndIsArray(subNode2, prop, propParent, origin, methodName)) {
					JsonNode arrNode = subNode2.get(prop);
					propParent = prop;

					// values
//					for (int i = 0; i < arrNode.size(); i++) {
//						JsonNode valueNode = arrNode.get(i);
//
////						length = 2;
//						prop = prop + "[" + i + "]";
//						if (dv.verifyNodeIsObject(valueNode, prop, propParent, origin, methodName)) {
//
//							ValuePair valuePair = new ValuePair();
//							collection.addValuePair(valuePair);
//
//							// validTime (validTimeBegin, validTimeEnd)
//							propParent = prop;
//							prop = "validTime";
//							if (dv.verifyNodeHasPropAndIsTextual(valueNode, prop, propParent, origin, methodName)) {
//								if (!valueNode.get(prop).isNull()) {
//									String validTimeString = valueNode.get(prop).asText();
//									int delimIndex = validTimeString.indexOf("/");
//									String validTimeBeginString = validTimeString.substring(0, delimIndex);
//									String periodString = validTimeString.substring(delimIndex + 1);
//
//									if (dv.verifyStringIsParsableToOffsetDateTime(validTimeBeginString, prop,
//											propParent, origin, methodName)) {
//										valuePair.setValidTimeBegin(OffsetDateTime.parse(validTimeBeginString));
//									}
//
//									if (dv.verifyStringIsPeriodHouryOnly(periodString, prop, propParent, origin,
//											methodName)) {
//										int tIndex = periodString.indexOf("T");
//										int hIndex = periodString.indexOf("H");
//										int hours = Integer.parseInt(periodString.substring(tIndex + 1, hIndex));
//
//										OffsetDateTime validTimeEnd = OffsetDateTime.parse(validTimeBeginString);
//										validTimeEnd = validTimeEnd.plusHours(hours);
//										valuePair.setValidTimeEnd(validTimeEnd);
//									}
//								}
//							}
//
//							// value
//							prop = "value";
//							if (dv.verifyNodeHasPropAndIsADouble(valueNode, prop, propParent, origin, methodName)) {
//								if (!valueNode.get(prop).isNull()) {
//									valuePair.setValue(valueNode.get(prop).asDouble());
//								}
//							}
//						}
//					}
				}
			}
		} // end ValuePairCollection
		
		return collection;
	}

}
