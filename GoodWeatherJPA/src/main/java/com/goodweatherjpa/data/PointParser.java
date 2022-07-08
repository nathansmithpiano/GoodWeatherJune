package com.goodweatherjpa.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.goodweatherjpa.entities.CustomError;
import com.goodweatherjpa.entities.Point;
import com.goodweatherjpa.logger.ErrorLogger;

public class PointParser {

	public Point parse(String urlString) {

		// initialize, register, and configure
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("PointDeserializer", new Version(1, 0, 0, null, null, null));
		module.addDeserializer(Point.class, new PointDeserializer());
		mapper.registerModule(module);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Point point = null;

		// for debugging
		final String origin = "com.goodweatherjpa.data.PointParser";
		final String methodName = "Point parse(" + urlString + ")";
		String errorType = null;

		try {
			point = mapper.readValue(new URL(urlString), Point.class);
		} catch (MalformedURLException e) {
			errorType = e.getClass().getSimpleName();
			e.printStackTrace();
		} catch (StreamReadException e) {
			errorType = e.getClass().getSimpleName();
			e.printStackTrace();
		} catch (DatabindException e) {
			errorType = e.getClass().getSimpleName();
			e.printStackTrace();
		} catch (IOException e) {
			errorType = e.getClass().getSimpleName();
			e.printStackTrace();
		}

		// print errors, if any
		if (errorType != null) {
			ErrorLogger errorLogger = new ErrorLogger();
			errorLogger.log(origin, methodName, errorType,
					"mapper.readValue(urlString, Point.class) threw IOException");

			for (CustomError customError : errorLogger.getErrorList()) {
				System.err.println(customError);
			}
		}

		return point;
	}

}
