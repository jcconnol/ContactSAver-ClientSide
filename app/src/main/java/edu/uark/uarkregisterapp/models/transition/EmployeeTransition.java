package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
import edu.uark.uarkregisterapp.models.api.Employee;

public class EmployeeTransition implements Parcelable {
    private UUID id;
    public UUID getId() {
        return this.id;
    }
    public EmployeeTransition setId(UUID id) {
        this.id = id;
        return this;
    }

    private String employeeId;
    public String getEmployeeId() {
        return this.employeeId;
    }
    public EmployeeTransition setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    private String firstName;
    public String getFirstName() {
        return this.firstName;
    }
    public EmployeeTransition setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    private String lastName;
    public String getLastName() {
        return this.lastName;
    }
    public EmployeeTransition setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    private String password;
    public String getPassword() {
        return this.password;
    }
    public EmployeeTransition setPassword(String password) {
        this.password = password;
        return this;
    }

    private Date createdOn;
    public Date getCreatedOn() {
        return this.createdOn;
    }
    public EmployeeTransition setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }
    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
        destination.writeString(this.employeeId);
        destination.writeString(this.firstName);
        destination.writeString(this.lastName);
        destination.writeString(this.password);
        destination.writeLong(this.createdOn.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<EmployeeTransition> CREATOR = new Parcelable.Creator<EmployeeTransition>() {
        public EmployeeTransition createFromParcel(Parcel employeeTransitionParcel) {
            return new EmployeeTransition(employeeTransitionParcel);
        }

        public EmployeeTransition[] newArray(int size) {
            return new EmployeeTransition[size];
        }
    };

    public EmployeeTransition() {
        this.id = new UUID(0, 0);
        this.createdOn = new Date();
        this.lastName = StringUtils.EMPTY;
        this.password = StringUtils.EMPTY;
        this.firstName = StringUtils.EMPTY;
        this.employeeId = StringUtils.EMPTY;
    }

    public EmployeeTransition(Employee employee) {
        this.id = employee.getId();
        this.lastName = employee.getLastName();
        this.password = employee.getPassword();
        this.createdOn = employee.getCreatedOn();
        this.firstName = employee.getFirstName();
        this.employeeId = employee.getEmployeeId();
    }

    public EmployeeTransition(Parcel employeeTransitionParcel) {
        this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(employeeTransitionParcel.createByteArray()).execute();
        this.employeeId = employeeTransitionParcel.readString();
        this.firstName = employeeTransitionParcel.readString();
        this.lastName = employeeTransitionParcel.readString();
        this.password = employeeTransitionParcel.readString();
        this.createdOn = new Date();
        this.createdOn.setTime(employeeTransitionParcel.readLong());
    }
}
