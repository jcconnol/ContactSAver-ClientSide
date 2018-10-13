package edu.uark.uarkregisterapp.models.api.enums;

import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public enum ContactApiMethod implements PathElementInterface {
	NONE(""),
	BY_LOOKUP_CODE("byLookupCode");

	@Override
	public String getPathValue() {
		return value;
	}

	private String value;

	ContactApiMethod(String value) {
		this.value = value;
	}
}
