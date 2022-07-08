package com.goodweatherjpa.data;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.goodweatherjpa.entities.Context;
import com.goodweatherjpa.entities.CustomError;
import com.goodweatherjpa.entities.ForecastOffice;

public class ForecastOfficeDeserializer extends StdDeserializer<ForecastOffice> {

	private static final long serialVersionUID = 1L;
	private DeserializerVerifier dv;

	public ForecastOfficeDeserializer() {
		this(null);
	}

	public ForecastOfficeDeserializer(Class<ForecastOffice> vc) {
		super(vc);
		dv = new DeserializerVerifier();
	}

	@Override
	public ForecastOffice deserialize(JsonParser parser, DeserializationContext deserializer) {

		// for debugging
		final String origin = "com.goodweatherjpa.data.ForecastOfficeDeserializer";
		final String methodName = "ForecastOffice deserialize(parser, deserializer)";
		String errorType = null;

		// initialize
		ObjectCodec codec = parser.getCodec();
		JsonNode node = null;
		ForecastOffice forecastOffice = null;

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

		forecastOffice = createForecastOffice(node);

		if (forecastOffice == null) {
			String details = "forecastOffice == null, issue with deserialization";
			dv.customError(null, null, origin, methodName, details);
		}

		// print errors, if any
		for (CustomError customError : dv.getErrorLogger().getErrorList()) {
			System.err.println(customError);
		}

		return forecastOffice;
	}

	private ForecastOffice createForecastOffice(JsonNode node) {
		// for debugging
		final String origin = "com.goodweatherjpa.data.ForecastOfficeDeserializer";
		final String methodName = "ForecastOffice createForecastOffice(node)";

		ForecastOffice entity = new ForecastOffice();
		String prop = null;
		String propParent = null;

		// @context
		prop = "@context";
		int length = 2;
		if (dv.verifyNodeHasPropAndIsObjectOfLength(node, prop, propParent, length, origin, methodName)) {
			Context context = new Context();
			context.setSource(entity.getClass().getSimpleName());
			entity.setContext(context);

			propParent = prop;
			JsonNode subNode = node.get(prop);

			// @version
			prop = "@version";
			if (dv.verifyNodeHasPropAndIsTextual(subNode, prop, propParent, origin, methodName)) {
				if (!subNode.get(prop).isNull()) {
					context.setVersion(subNode.get(prop).asText());
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

		// @type
		prop = "@type";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setType(node.get(prop).asText());
			}
		}

		// @id
		prop = "@id";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setUri(node.get(prop).asText());
			}
		}

		// id
		prop = "id";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setId(node.get(prop).asText());
			}
		}

		// name
		prop = "name";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setName(node.get(prop).asText());
			}
		}

		// telephone
		prop = "telephone";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setTelephone(node.get(prop).asText());
			}
		}

		// faxNumber
		prop = "faxNumber";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setFaxNumber(node.get(prop).asText());
			}
		}

		// email
		prop = "email";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setEmail(node.get(prop).asText());
			}
		}

		// sameAs
		prop = "sameAs";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setSameAs(node.get(prop).asText());
			}
		}

		// nwsRegion
		prop = "nwsRegion";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setNwsRegion(node.get(prop).asText());
			}
		}

		// parentOrganization
		prop = "parentOrganization";
		propParent = null;
		if (dv.verifyNodeHasPropAndIsTextual(node, prop, propParent, origin, methodName)) {
			if (!node.get(prop).isNull()) {
				entity.setParentOrganizationUri(node.get(prop).asText());
			}
		}

		return entity;
	}

}
