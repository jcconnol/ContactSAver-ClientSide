package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum ContactFieldName implements FieldNameInterface {
	ID("id"),
	LOOKUP_CODE("lookupCode"),
	COUNT("count"),
	API_REQUEST_STATUS("apiRequestStatus"),
	API_REQUEST_MESSAGE("apiRequestMessage"),
	CREATED_ON("createdOn");

	private String fieldName;
	public String getFieldName() {
		return this.fieldName;
	}

	ContactFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
