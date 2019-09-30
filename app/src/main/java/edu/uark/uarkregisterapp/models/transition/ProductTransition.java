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
<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ContactTransition.java
	public Integer getContactId() {
		return this.contactID;
	}
	public String getMimeType() { return this.mimetype; }
	public String [] getData() { return this.data; }

	public ContactTransition setId(UUID id) {
=======
	public ProductTransition setId(UUID id) {
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ProductTransition.java
		this.id = id;
		return this;
	}

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ContactTransition.java
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
=======
	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public ProductTransition setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}

	private int count;
	public int getCount() {
		return this.count;
	}
	public ProductTransition setCount(int count) {
		this.count = count;
		return this;
	}

	private Date createdOn;
	public Date getCreatedOn() {
		return this.createdOn;
	}
	public ProductTransition setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ProductTransition.java
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

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ContactTransition.java
	public static final Parcelable.Creator<ContactTransition> CREATOR = new Parcelable.Creator<ContactTransition>() {
		public ContactTransition createFromParcel(Parcel contactTransitionParcel) {
			return new ContactTransition(contactTransitionParcel);
=======
	public static final Parcelable.Creator<ProductTransition> CREATOR = new Parcelable.Creator<ProductTransition>() {
		public ProductTransition createFromParcel(Parcel productTransitionParcel) {
			return new ProductTransition(productTransitionParcel);
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ProductTransition.java
		}

		public ProductTransition[] newArray(int size) {
			return new ProductTransition[size];
		}
	};

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ContactTransition.java
	public ContactTransition() {
=======
	public ProductTransition() {
		this.count = -1;
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ProductTransition.java
		this.id = new UUID(0, 0);
		this.contactID = -1;
		this.mimetype = "text";
		this.data = new String[dataSize];
	}

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ContactTransition.java
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
=======
	public ProductTransition(Product product) {
		this.id = product.getId();
		this.count = product.getCount();
		this.createdOn = product.getCreatedOn();
		this.lookupCode = product.getLookupCode();
	}

	private ProductTransition(Parcel productTransitionParcel) {
		this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(productTransitionParcel.createByteArray()).execute();
		this.lookupCode = productTransitionParcel.readString();
		this.count = productTransitionParcel.readInt();

		this.createdOn = new Date();
		this.createdOn.setTime(productTransitionParcel.readLong());
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/models/transition/ProductTransition.java
	}
}
