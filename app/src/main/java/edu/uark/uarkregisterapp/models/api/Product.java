package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;

import edu.uark.uarkregisterapp.models.api.fields.ProductFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class Product implements ConvertToJsonInterface, LoadFromJsonInterface<Product> {
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
	public String[] getData() { return this.data; }

	public Contact setId(UUID id) {
		this.id = id;
		return this;
	}

	public Contact setId(Integer contactID) {
		this.contactID = contactID;
		return this;
	}

	public Contact setMimeType(String mimetype) {
		this.mimetype = mimetype;
		return this;
	}

	public Contact setData(String[] data) {
		this.data = new String[dataSize];
		for(int i = 0; i < 15; i++) {
			if(!this.data[i].equals(data[i])) {
				this.data[i] = data[i];
			}
		}
		return this;
	}

	@Override
	public Product loadFromJson(JSONObject rawJsonObject) {
		String value = rawJsonObject.optString(ProductFieldName.ID.getFieldName());
		if (!StringUtils.isBlank(value)) {
			this.id = UUID.fromString(value);
		}

		this.contactID = rawJsonObject.optInt(ContactFieldName.CONTACT_ID.getFieldName());
		this.mimetype = rawJsonObject.optString(ContactFieldName.MIMETYPE.getFieldName());

		JSONArray ja = rawJsonObject.optJSONArray(ContactFieldName.DATA.getFieldName());

		if (!StringUtils.isBlank(value)) {
			for(int i = 0; i < ja.length(); i++){
				try {
					JSONObject row = ja.getJSONObject(i);
					data[i] = row.getString(ContactFieldName.DATA.getFieldName());
				} catch (JSONException e){
					e.printStackTrace();
				}
			}
		}

		return this;
	}

	@Override
	public JSONObject convertToJson() {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put(ContactFieldName.ID.getFieldName(), this.id.toString());
			jsonObject.put(ContactFieldName.CONTACT_ID.getFieldName(), this.contactID);
			jsonObject.put(ContactFieldName.MIMETYPE.getFieldName(), this.mimetype);
			JSONArray jsonArray = new JSONArray();
			for(int i = 0; i < dataSize; i++) {
				jsonArray.put(data[i]);
			}
			jsonObject.put(ContactFieldName.DATA.getFieldName(), jsonArray);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	public Contact() {

		this.id = new UUID(0, 0);
		this.contactID = -1;
		this.mimetype = "text";
		this.data = new String[dataSize];
	}

	public Contact(ContactTransition contactTransition) {
		this.id = contactTransition.getId();
		this.contactID = contactTransition.getContactId();
		this.mimetype = contactTransition.getMimeType();
		this.data = contactTransition.getData();
	}
}
