package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum UserLoginFieldName implements FieldNameInterface {
    USER_ID("userId"),
    PASSWORD("password");

    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    UserLoginFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
