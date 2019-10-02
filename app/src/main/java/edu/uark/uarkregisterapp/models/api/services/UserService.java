package edu.uark.uarkregisterapp.models.api.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.User;
import edu.uark.uarkregisterapp.models.api.UserLogin;
import edu.uark.uarkregisterapp.models.api.enums.ApiObject;
import edu.uark.uarkregisterapp.models.api.enums.UserApiMethod;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public class UserService extends BaseRemoteService {
    public ApiResponse<User> getUser(UUID userId) {
        return this.readUserDetailsFromResponse(
            this.<User>performGetRequest(
                this.buildPath(userId)
            )
        );
    }

    public ApiResponse<List<User>> getUsers() {
        ApiResponse<List<User>> apiResponse = this.performGetRequest(
                this.buildPath()
        );

        JSONArray rawJsonArray = this.rawResponseToJSONArray(apiResponse.getRawResponse());
        if (rawJsonArray != null) {
            ArrayList<User> users = new ArrayList<>(rawJsonArray.length());
            for (int i = 0; i < rawJsonArray.length(); i++) {
                try {
                    users.add((new User()).loadFromJson(rawJsonArray.getJSONObject(i)));
                } catch (JSONException e) {
                    Log.d("GET USER", e.getMessage());
                }
            }

            apiResponse.setData(users);
        } else {
            apiResponse.setData(new ArrayList<User>(0));
        }

        return apiResponse;
    }

    public ApiResponse<User> updateEmployee(User user) {
        return this.readUserDetailsFromResponse(
            this.<User>performPutRequest(
                this.buildPath(user.getId())
                , user.convertToJson()
            )
        );
    }

    public ApiResponse<User> createUser(User user) {
        return this.readUserDetailsFromResponse(
            this.<User>performPostRequest(
                this.buildPath()
                , user.convertToJson()
            )
        );
    }

    public ApiResponse<String> deleteUser(UUID userId) {
        return this.<String>performDeleteRequest(
            this.buildPath(userId)
        );
    }

    public ApiResponse<User> logIn(UserLogin userLogin) {
        return this.readUserDetailsFromResponse(
            this.<User>performPostRequest(
                this.buildPath(
                    (new PathElementInterface[] { UserApiMethod.LOGIN })
                )
                , userLogin.convertToJson()
            )
        );
    }

    private ApiResponse<User> readUserDetailsFromResponse(ApiResponse<User> apiResponse) {
        return this.readDetailsFromResponse(
            apiResponse, (new User())
        );
    }

    private <T extends LoadFromJsonInterface<T>> ApiResponse<T> readDetailsFromResponse(ApiResponse<T> apiResponse, T apiObject) {
        JSONObject rawJsonObject = this.rawResponseToJSONObject(
            apiResponse.getRawResponse()
        );

        if (rawJsonObject != null) {
            apiResponse.setData(
                apiObject.loadFromJson(rawJsonObject)
            );
        }

        return apiResponse;
    }

    public UserService() { super(ApiObject.EMPLOYEE); }
}
