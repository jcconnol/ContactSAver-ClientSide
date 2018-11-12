package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum ApiResponseFieldName implements FieldNameInterface {

    NONUNIQUE_EMPLOYEE("employee id nonunique");

    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    ApiResponseFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}