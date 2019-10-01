package edu.uark.uarkregisterapp.models.api.enums;

import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public enum UserApiMethod implements PathElementInterface {
    NONE(""),
    LOGIN("login");

    @Override
    public String getPathValue() {
        return value;
    }

    private String value;

    UserApiMethod(String value) {
        this.value = value;
    }
}
