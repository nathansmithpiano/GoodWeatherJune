package com.goodweatherjpa.logger;

import java.util.ArrayList;
import java.util.List;

import com.goodweatherjpa.entities.CustomError;

public class ErrorLogger {

	private List<CustomError> errorList;

	public ErrorLogger() {
		errorList = new ArrayList<>();
	}

	public void log(String origin, String methodName, String errorType, String details) {
		CustomError customError = new CustomError();
		customError.setOrigin(origin);
		customError.setMethod(methodName);
		customError.setType(errorType);
		customError.setDetails(details);
		errorList.add(customError);
	}

	public List<CustomError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<CustomError> list) {
		this.errorList = list;
	}

}
