package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uark.uarkregisterapp.models.api.fields.UserLoginFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;

public class UserLogin implements ConvertToJsonInterface {
    private String employeeId;
    public String getEmployeeUsername() {
        return this.employeeId;
    }
    public UserLogin setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }
    private String password;
    public String getPassword() {
        return this.password;
    }
    public UserLogin setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(UserLoginFieldName.EMPLOYEE_ID.getFieldName(), this.employeeId);
            jsonObject.put(UserLoginFieldName.PASSWORD.getFieldName(), this.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public UserLogin() {
        this.password = StringUtils.EMPTY;
        this.employeeId = StringUtils.EMPTY;
    }
}