package edu.uark.uarkregisterapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import edu.uark.uarkregisterapp.R;
import edu.uark.uarkregisterapp.models.api.Contact;

public class ContactListAdapter extends ArrayAdapter<Contact> {
	@NonNull
	@Override
	public View getView(int position, View convertView, @NonNull ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
			view = inflater.inflate(R.layout.list_view_item_contact, parent, false);
		}

		Contact contact = this.getItem(position);
		if (contact != null) {
			TextView contactIdTextView = (TextView) view.findViewById(R.id.list_view_item_contact_contact_id);
			if (contactIdTextView != null) {
				contactIdTextView.setText(contact.getContactId());
			}

			TextView countTextView = (TextView) view.findViewById(R.id.list_view_item_contact_data1);
			if (countTextView != null) {
				countTextView.setText(contact.getData()[0]);
			}
		}

		return view;
	}

	public ContactListAdapter(Context context, List<Contact> contacts) {
		super(context, R.layout.list_view_item_contact, contacts);
	}
}
