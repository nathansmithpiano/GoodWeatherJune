package com.goodweatherjpa.data;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.goodweatherjpa.logger.ErrorLogger;

public class DeserializerVerifier {

	private ErrorLogger errorLogger;

	public DeserializerVerifier() {
		errorLogger = new ErrorLogger();
	}

	public ErrorLogger getErrorLogger() {
		return this.errorLogger;
	}

	public boolean verifyNodeHasProp(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (node.has(property)) {
			return true;
		} else {
			String errorType = null;

			if (propParent != null) {
				property = propParent + "." + property;
			}

			String details = property + " missing from node";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyNodeIsTextual(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (node.isTextual() || node.isNull()) {
			return true;
		} else {
			String errorType = null;

			if (propParent != null) {
				property = propParent + "." + property;
			}

			String details = property + "(" + node.get(property).asText() + ") is not textual";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyNodeIsADouble(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (node.isDouble() || node.isInt() || node.isNull()) {
			return true;
		} else {
			String errorType = null;

			if (propParent != null) {
				property = propParent + "." + property;
			}

			String details = property + "(" + node.get(property).asText() + ") is not a Double";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyNodeIsAnInt(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (node.isInt() || node.isNull()) {
			return true;
		} else {
			String errorType = null;

			if (propParent != null) {
				property = propParent + "." + property;
			}
			String details = property + "(" + node.get(property).asText() + ") is not an Int";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyNodeIsABoolean(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (node.isBoolean() || node.isNull()) {
			return true;
		} else {
			String errorType = null;

			if (propParent != null) {
				property = propParent + "." + property;
			}
			String details = property + "(" + node.get(property).asText() + ") is not a Boolean";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyStringIsParsableToOffsetDateTime(String odtString, String property, String propParent,
			String origin, String methodName) {

		String errorType = null;

		try {
			OffsetDateTime.parse(odtString);
		} catch (DateTimeParseException e) {
			errorType = "DateTimeParseException";
			e.printStackTrace();
		}

		if (propParent != null) {
			property = propParent + "." + property;
		}

		if (errorType == null) {
			return true;
		} else {
			String details = property + " (" + odtString + ") could not be parsed to OffsetDateTime";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyStringIsParsableToAnInt(String intString, String property, String propParent, String origin,
			String methodName) {

		String errorType = null;

		try {
			Integer.parseInt(intString);
		} catch (NumberFormatException e) {
			errorType = "NumberFormatException";
			e.printStackTrace();
		}

		if (propParent != null) {
			property = propParent + "." + property;
		}

		if (errorType == null) {
			return true;
		} else {
			String details = property + " (" + intString + ") could not be parsed to an Int";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyStringIsPeriod(String periodString, String property, String propParent, String origin,
			String methodName) {

		if (propParent != null) {
			property = propParent + "." + property;
		}

		String errorType = null;
		String details = property + " (" + periodString + ") is invalid, expecting P#DT##H format";

		if (!periodString.contains("P") || !periodString.contains("DT") || !periodString.contains("H")) {
			errorLogger.log(origin, methodName, errorType, details);
			return false;
		}

		int pIndex = periodString.indexOf("P");
		int dtIndex = periodString.indexOf("DT");
		int hIndex = periodString.indexOf("H");

		try {
			Integer.parseInt(periodString.substring(pIndex + 1, dtIndex));
			Integer.parseInt(periodString.substring(dtIndex + 2, hIndex));
		} catch (NumberFormatException e) {
			errorType = "NumberFormatException";
			e.printStackTrace();
		}

		if (errorType == null) {
			return true;
		} else {
			errorLogger.log(origin, methodName, errorType, details);
		}

		return true;
	}

	public boolean verifyStringIsPeriodHouryOnly(String periodString, String property, String propParent, String origin,
			String methodName) {

		if (propParent != null) {
			property = propParent + "." + property;
		}

		String errorType = null;
		String details = property + " (" + periodString + ") is invalid, expecting PT#H or PT##H format";

		if (!periodString.contains("P") || !periodString.contains("T") || !periodString.contains("H")) {
			errorLogger.log(origin, methodName, errorType, details);
			return false;
		}

		int pIndex = periodString.indexOf("P");
		int tIndex = periodString.indexOf("T");
		int hIndex = periodString.indexOf("H");

		if (pIndex + 1 != tIndex) {
			details = property + " (" + periodString + ") is invalid, expecting only hours";
			errorLogger.log(origin, methodName, errorType, details);
			return false;
		}

		try {
			Integer.parseInt(periodString.substring(tIndex + 1, hIndex));
		} catch (NumberFormatException e) {
			errorType = "NumberFormatException";
			e.printStackTrace();
		}

		if (errorType == null) {
			return true;
		} else {
			errorLogger.log(origin, methodName, errorType, details);
		}

		return true;
	}

	public boolean verifyNodeHasPropAndIsWindSpeed(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsTextual(subNode, property, propParent, origin, methodName)) {

				if (propParent != null) {
					property = propParent + "." + property;
				}

				if (subNode.isNull()) {
					return true;
				}

				String str = subNode.asText();
				String errorType = null;

				final String regex = "[0-9]+";
				final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
				final Matcher matcher = pattern.matcher(str);

				List<String> matches = new ArrayList<>();

				while (matcher.find()) {
					matches.add(matcher.group(0));
				}

				// should contain 1-2 numbers
				if (matches.size() == 0 || matches.size() > 2) {
					String details = property + " (" + str + ") is invalid, expecting 1-2 numbers format";
					errorLogger.log(origin, methodName, errorType, details);
					return false;
				}

				// numbers should be int
				try {
					for (String numStr : matches) {
						Integer.parseInt(numStr);
					}
				} catch (NumberFormatException e) {
					errorType = "NumberFormatException";
					e.printStackTrace();
				}

				if (errorType != null) {
					String details = property + " (" + str + ") is invalid, each should be an int";
					errorLogger.log(origin, methodName, errorType, details);
					return false;
				}

				int min = Integer.parseInt(matches.get(0));
				int max = min;

				if (matches.size() == 2) {
					max = Integer.parseInt(matches.get(1));
				}

				String unit = str.substring(str.lastIndexOf(' ') + 1, str.length());

				if (unit == null || unit.length() == 0) {
					String details = property + " (" + str + ") is invalid, unit is missing";
					errorLogger.log(origin, methodName, errorType, details);
					return false;
				}

				// return true if made it this far
				return true;

			}

		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsTextual(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsTextual(subNode, property, propParent, origin, methodName)) {
				return true;
			}
		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsAnInt(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsAnInt(subNode, property, propParent, origin, methodName)) {
				return true;
			}
		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsADouble(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsADouble(subNode, property, propParent, origin, methodName)) {
				return true;
			}
		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsABoolean(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsABoolean(subNode, property, propParent, origin, methodName)) {
				return true;
			}
		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsParsableToOffsetDateTime(JsonNode node, String property, String propParent,
			String origin, String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsTextual(subNode, property, propParent, origin, methodName)) {
				String odtString = subNode.asText();

				if (verifyStringIsParsableToOffsetDateTime(odtString, property, propParent, origin, methodName)) {
					return true;
				}
			}

		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsParsableToAnInt(JsonNode node, String property, String propParent,
			String origin, String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsTextual(subNode, property, propParent, origin, methodName)) {
				String intString = subNode.asText();

				if (verifyStringIsParsableToAnInt(intString, property, propParent, origin, methodName)) {
					return true;
				}
			}

		}

		return false;
	}

	public boolean verifyStringsAreEqual(String string1, String string2, String property1, String property2,
			String origin, String methodName) {

		if (string1.equals(string2)) {
			return true;
		} else {
			String errorType = null;

			String details = string1 + " (" + property1 + ") and " + string2 + " (" + property2 + ") are not equal";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyNodeIsObject(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (node.isObject() || node.isNull()) {
			return true;
		} else {
			String errorType = null;

			if (propParent != null) {
				property = propParent + "." + property;
			}

			String details = property + " is not an Object";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyNodeIsObjectOfLength(JsonNode node, String property, String propParent, int length,
			String origin, String methodName) {

		if (verifyNodeIsObject(node, property, propParent, origin, methodName)) {
			if (node.size() == length) {
				return true;
			} else {
				String errorType = null;

				if (propParent != null) {
					property = propParent + "." + property;
				}

				String details = property + " is an Object but size is " + node.size() + " (expected " + length + ")";
				errorLogger.log(origin, methodName, errorType, details);
			}
		} else {
			return false;
		}

		return false;
	}

	public boolean verifyNodeIsArray(JsonNode node, String property, String propParent, String origin,
			String methodName) {
		if (node.isArray() || node.isNull()) {
			return true;
		} else {
			String errorType = null;

			if (propParent != null) {
				property = propParent + "." + property;
			}

			String details = property + " is not an array";
			errorLogger.log(origin, methodName, errorType, details);
			return false;
		}
	}

	public boolean verifyNodeIsArrayOfLength(JsonNode node, String property, String propParent, int length,
			String origin, String methodName) {

		if (verifyNodeIsArray(node, property, propParent, origin, methodName)) {
			if (node.size() == length) {
				return true;
			} else {
				String errorType = null;

				if (propParent != null) {
					property = propParent + "." + property;
				}

				String details = property + " is an array but size is " + node.size() + " (expected " + length + ")";
				errorLogger.log(origin, methodName, errorType, details);
			}
		} else {
			String errorType = null;

			if (propParent != null) {
				property = propParent + "." + property;
			}

			String details = property + " is not an Array";
			errorLogger.log(origin, methodName, errorType, details);
		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsObject(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsObject(subNode, property, propParent, origin, methodName)) {
				return true;
			}
		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsObjectOfLength(JsonNode node, String property, String propParent, int length,
			String origin, String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsObjectOfLength(subNode, property, propParent, length, origin, methodName)) {
				return true;
			}
		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsArray(JsonNode node, String property, String propParent, String origin,
			String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsArray(subNode, property, propParent, origin, methodName)) {
				return true;
			}
		}

		return false;
	}

	public boolean verifyNodeHasPropAndIsArrayOfLength(JsonNode node, String property, String propParent, int length,
			String origin, String methodName) {

		if (verifyNodeHasProp(node, property, propParent, origin, methodName)) {
			JsonNode subNode = node.get(property);

			if (verifyNodeIsArrayOfLength(subNode, property, propParent, length, origin, methodName)) {
				return true;
			}
		}

		return false;
	}

	public void customError(String property, String propParent, String origin, String methodName, String details) {
		if (propParent != null) {
			property = propParent + "." + property;
		}

		errorLogger.log(origin, methodName, methodName, details);
	}

}
