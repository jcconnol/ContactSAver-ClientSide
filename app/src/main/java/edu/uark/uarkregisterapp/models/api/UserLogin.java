package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uark.uarkregisterapp.models.api.fields.UserLoginFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;

public class UserLogin implements ConvertToJsonInterface {
    private String userId;
    public String getUserId() {
        return this.userId;
    }
    public UserLogin setUserId(String userId) {
        this.userId = userId;
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
            jsonObject.put(UserLoginFieldName.USER_ID.getFieldName(), this.userId);
            jsonObject.put(UserLoginFieldName.PASSWORD.getFieldName(), this.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public UserLogin() {
        this.password = StringUtils.EMPTY;
        this.userId = StringUtils.EMPTY;
    }
}
