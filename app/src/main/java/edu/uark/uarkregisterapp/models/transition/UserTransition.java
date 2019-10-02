package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
import edu.uark.uarkregisterapp.models.api.User;

public class UserTransition implements Parcelable {
    private UUID id;
    public UUID getId() {
        return this.id;
    }
    public UserTransition setId(UUID id) {
        this.id = id;
        return this;
    }

    private String userId;
    public String getUserId() {
        return this.userId;
    }
    public UserTransition setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    private String firstName;
    public String getFirstName() {
        return this.firstName;
    }
    public UserTransition setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    private String lastName;
    public String getLastName() {
        return this.lastName;
    }
    public UserTransition setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    private String password;
    public String getPassword() {
        return this.password;
    }
    public UserTransition setPassword(String password) {
        this.password = password;
        return this;
    }

    private Date createdOn;
    public Date getCreatedOn() {
        return this.createdOn;
    }
    public UserTransition setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }
    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
        destination.writeString(this.userId);
        destination.writeString(this.firstName);
        destination.writeString(this.lastName);
        destination.writeString(this.password);
        destination.writeLong(this.createdOn.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<UserTransition> CREATOR = new Parcelable.Creator<UserTransition>() {
        public UserTransition createFromParcel(Parcel userTransitionParcel) {
            return new UserTransition(userTransitionParcel);
        }

        public UserTransition[] newArray(int size) {
            return new UserTransition[size];
        }
    };

    public UserTransition() {
        this.id = new UUID(0, 0);
        this.createdOn = new Date();
        this.lastName = StringUtils.EMPTY;
        this.password = StringUtils.EMPTY;
        this.firstName = StringUtils.EMPTY;
        this.userId = StringUtils.EMPTY;
    }

    public UserTransition(User user) {
        this.id = user.getId();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.createdOn = user.getCreatedOn();
        this.firstName = user.getFirstName();
        this.userId = user.getUserId();
    }

    public UserTransition(Parcel userTransitionParcel) {
        this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(userTransitionParcel.createByteArray()).execute();
        this.userId = userTransitionParcel.readString();
        this.firstName = userTransitionParcel.readString();
        this.lastName = userTransitionParcel.readString();
        this.password = userTransitionParcel.readString();
        this.createdOn = new Date();
        this.createdOn.setTime(userTransitionParcel.readLong());
    }
}
