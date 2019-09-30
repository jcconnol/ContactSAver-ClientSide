package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum UserFieldName implements FieldNameInterface {
    ID("id"),
    USERNAME("userName"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    PASSWORD("password"),
    CREATED_ON("createdOn");

    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    UserFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
