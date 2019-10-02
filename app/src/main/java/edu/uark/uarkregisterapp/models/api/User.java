package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.fields.UserFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;

public class User implements ConvertToJsonInterface, LoadFromJsonInterface<User> {
    private UUID id;
    public UUID getId() {
        return this.id;
    }
    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    private String userId;
    public String getUserId() {
        return this.userId;
    }
    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    private String firstName;
    public String getFirstName() {
        return this.firstName;
    }
    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    private String lastName;
    public String getLastName() {
        return this.lastName;
    }
    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    private String password;
    public String getPassword() {
        return this.password;
    }
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    private Date createdOn;
    public Date getCreatedOn() {
        return this.createdOn;
    }
    public User setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public User loadFromJson(JSONObject rawJsonObject) {
        String value = rawJsonObject.optString(UserFieldName.ID.getFieldName());
        if (!StringUtils.isBlank(value)) {
            this.id = UUID.fromString(value);
        }

        this.userId = rawJsonObject.optString(UserFieldName.USER_ID.getFieldName());
        this.firstName = rawJsonObject.optString(UserFieldName.FIRST_NAME.getFieldName());
        this.lastName = rawJsonObject.optString(UserFieldName.FIRST_NAME.getFieldName());

        value = rawJsonObject.optString(UserFieldName.CREATED_ON.getFieldName());
        if (!StringUtils.isBlank(value)) {
            try {
                this.createdOn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US).parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(UserFieldName.ID.getFieldName(), this.id.toString());
            jsonObject.put(UserFieldName.USER_ID.getFieldName(), this.userId);
            jsonObject.put(UserFieldName.FIRST_NAME.getFieldName(), this.firstName);
            jsonObject.put(UserFieldName.LAST_NAME.getFieldName(), this.lastName);
            jsonObject.put(UserFieldName.PASSWORD.getFieldName(), this.password);
            jsonObject.put(UserFieldName.CREATED_ON.getFieldName(), (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)).format(this.createdOn));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public User() {
        this.id = new UUID(0, 0);
        this.createdOn = new Date();
        this.lastName = StringUtils.EMPTY;
        this.password = StringUtils.EMPTY;
        this.firstName = StringUtils.EMPTY;
        this.userId = StringUtils.EMPTY;
    }
}
