package edu.uark.uarkregisterapp.models.api.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Contact;
import edu.uark.uarkregisterapp.models.api.enums.ApiObject;
import edu.uark.uarkregisterapp.models.api.enums.ContactApiMethod;
import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public class ContactService extends BaseRemoteService {
	public ApiResponse<Contact> getContact(UUID id) {
		return this.readContactDetailsFromResponse(
			this.<Contact>performGetRequest(
				this.buildPath(id)
			)
		);
	}

	public ApiResponse<Contact> getContactByContactId(String contactContactId) {
		return this.readContactDetailsFromResponse(
			this.<Contact>performGetRequest(
				this.buildPath(
					(new PathElementInterface[] { ContactApiMethod.BY_CONTACT_NAME })
					, contactContactId
				)
			)
		);
	}

	public ApiResponse<List<Contact>> getContacts() {
		ApiResponse<List<Contact>> apiResponse = this.performGetRequest(
			this.buildPath()
		);

		JSONArray rawJsonArray = this.rawResponseToJSONArray(apiResponse.getRawResponse());
		if (rawJsonArray != null) {
			ArrayList<Contact> contacts = new ArrayList<>(rawJsonArray.length());
			for (int i = 0; i < rawJsonArray.length(); i++) {
				try {
					contacts.add((new Contact()).loadFromJson(rawJsonArray.getJSONObject(i)));
				} catch (JSONException e) {
					Log.d("GET Contacts", e.getMessage());
				}
			}

			apiResponse.setData(contacts);
		} else {
			apiResponse.setData(new ArrayList<Contact>(0));
		}

		return apiResponse;
	}

	public ApiResponse<Contact> updateContact(Contact contact) {
		return this.readContactDetailsFromResponse(
			this.<Contact>performPutRequest(
				this.buildPath(contact.getId())
				, contact.convertToJson()
			)
		);
	}

	public ApiResponse<Contact> createContact(Contact contact) {
		return this.readContactDetailsFromResponse(
			this.<Contact>performPostRequest(
				this.buildPath()
				, contact.convertToJson()
			)
		);
	}

	public ApiResponse<String> deleteContact(UUID contactId) {
		return this.<String>performDeleteRequest(
			this.buildPath(contactId)
		);
	}

	private ApiResponse<Contact> readContactDetailsFromResponse(ApiResponse<Contact> apiResponse) {
		JSONObject rawJsonObject = this.rawResponseToJSONObject(
			apiResponse.getRawResponse()
		);

		if (rawJsonObject != null) {
			apiResponse.setData(
				(new Contact()).loadFromJson(rawJsonObject)
			);
		}

		return apiResponse;
	}

	public ContactService() { super(ApiObject.CONTACT); }
}
