package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
import edu.uark.uarkregisterapp.models.api.Contact;

public class ProductTransition implements Parcelable {
	private UUID id;
	private Integer contactID;
	private String mimetype;
	private String [] data;
	private Integer dataSize = 15;

	public UUID getId() {
		return this.id;
	}
	public Integer getContactId() {
		return this.contactID;
	}
	public String getMimeType() { return this.mimetype; }
	public String [] getData() { return this.data; }

	public ContactTransition setId(UUID id) {
		this.id = id;
		return this;
	}

	public ContactTransition setId(Integer contactID) {
		this.contactID = contactID;
		return this;
	}

	public ContactTransition setMimeType(String mimetype) {
		this.mimetype = mimetype;
		return this;
	}

	public ContactTransition setData(String[] data) {
		this.data = new String[dataSize];
		for(int i = 0; i < 15; i++) {
			if(!this.data[i].equals(data[i])) {
				this.data[i] = data[i];
			}
		}
		return this;
	}

	@Override
	public void writeToParcel(Parcel destination, int flags) {
		destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
		destination.writeInt(this.contactID);
		destination.writeString(this.mimetype);
		destination.writeStringArray(this.data);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<ContactTransition> CREATOR = new Parcelable.Creator<ContactTransition>() {
		public ContactTransition createFromParcel(Parcel contactTransitionParcel) {
			return new ContactTransition(contactTransitionParcel);

		}

		public ProductTransition[] newArray(int size) {
			return new ProductTransition[size];
		}
	};

	public ContactTransition() {
		this.id = new UUID(0, 0);
		this.contactID = -1;
		this.mimetype = "text";
		this.data = new String[dataSize];
	}

	public ContactTransition(Contact contact) {
		this.id = contact.getId();
		this.contactID = contact.getContactId();
		this.mimetype = contact.getMimeType();
		this.data = contact.getData();
	}

	private ContactTransition(Parcel contactTransitionParcel) {
		this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(contactTransitionParcel.createByteArray()).execute();
		this.contactID = contactTransitionParcel.readInt();
		this.mimetype = contactTransitionParcel.readString();
		contactTransitionParcel.readStringArray(this.data);
	}
}
