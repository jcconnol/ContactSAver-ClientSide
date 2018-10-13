package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
import edu.uark.uarkregisterapp.models.api.Product;

public class ContactTransition implements Parcelable {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public ContactTransition setId(UUID id) {
		this.id = id;
		return this;
	}

	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public ContactTransition setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}

	private int count;
	public int getCount() {
		return this.count;
	}
	public ContactTransition setCount(int count) {
		this.count = count;
		return this;
	}

	private Date createdOn;
	public Date getCreatedOn() {
		return this.createdOn;
	}
	public ContactTransition setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	@Override
	public void writeToParcel(Parcel destination, int flags) {
		destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
		destination.writeString(this.lookupCode);
		destination.writeInt(this.count);
		destination.writeLong(this.createdOn.getTime());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<ContactTransition> CREATOR = new Parcelable.Creator<ContactTransition>() {
		public ContactTransition createFromParcel(Parcel productTransitionParcel) {
			return new ContactTransition(productTransitionParcel);
		}

		public ContactTransition[] newArray(int size) {
			return new ContactTransition[size];
		}
	};

	public ContactTransition() {
		this.count = -1;
		this.id = new UUID(0, 0);
		this.createdOn = new Date();
		this.lookupCode = StringUtils.EMPTY;
	}

	public ContactTransition(Product product) {
		this.id = product.getId();
		this.count = product.getCount();
		this.createdOn = product.getCreatedOn();
		this.lookupCode = product.getLookupCode();
	}

	private ContactTransition(Parcel productTransitionParcel) {
		this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(productTransitionParcel.createByteArray()).execute();
		this.lookupCode = productTransitionParcel.readString();
		this.count = productTransitionParcel.readInt();

		this.createdOn = new Date();
		this.createdOn.setTime(productTransitionParcel.readLong());
	}
}
