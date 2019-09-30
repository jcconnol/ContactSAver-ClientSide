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

    private String userName;
    public String getEmployeeId() {
        return this.userName;
    }
    public UserTransition setUserName(String userName) {
        this.userName = userName;
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
        destination.writeString(this.userName);
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
        public UserTransition createFromParcel(Parcel employeeTransitionParcel) {
            return new UserTransition(employeeTransitionParcel);
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
        this.userName = StringUtils.EMPTY;
    }

    public UserTransition(User user) {
        this.id = user.getId();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.createdOn = user.getCreatedOn();
        this.firstName = user.getFirstName();
        this.userName = user.getUserName();
    }

    public UserTransition(Parcel employeeTransitionParcel) {
        this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(employeeTransitionParcel.createByteArray()).execute();
        this.userName = employeeTransitionParcel.readString();
        this.firstName = employeeTransitionParcel.readString();
        this.lastName = employeeTransitionParcel.readString();
        this.password = employeeTransitionParcel.readString();
        this.createdOn = new Date();
        this.createdOn.setTime(employeeTransitionParcel.readLong());
    }
}
