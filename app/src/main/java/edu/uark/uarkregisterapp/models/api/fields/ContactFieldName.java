package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum ContactFieldName implements FieldNameInterface {
	ID("id"),
	CONTACT_ID("contactId"),
	MIMETYPE("mimetype"),
	DATA("data"),
	API_REQUEST_STATUS("apiRequestStatus"),
	API_REQUEST_MESSAGE("apiRequestMessage");

	private String fieldName;
	public String getFieldName() {
		return this.fieldName;
	}

	ContactFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
