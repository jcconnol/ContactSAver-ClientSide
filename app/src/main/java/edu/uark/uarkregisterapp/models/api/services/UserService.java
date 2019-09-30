package edu.uark.uarkregisterapp.models.api.services;

import org.json.JSONObject;

import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.User;
import edu.uark.uarkregisterapp.models.api.UserLogin;
import edu.uark.uarkregisterapp.models.api.enums.ApiObject;
import edu.uark.uarkregisterapp.models.api.enums.UserApiMethod;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public class UserService extends BaseRemoteService {
    public ApiResponse<User> getUser(UUID UserName) {
        return this.readUserDetailsFromResponse(
                this.<User>performGetRequest(
                        this.buildPath(UserName)
                )
        );
    }

    public ApiResponse<User> updateUser(User user) {
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

    public ApiResponse<String> deleteUser(UUID userName) {
        return this.<String>performDeleteRequest(
                this.buildPath(userName)
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

    public UserService() { super(ApiObject.USER); }
}