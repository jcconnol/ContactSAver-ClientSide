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

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/adapters/ContactListAdapter.java
public class ContactListAdapter extends ArrayAdapter<Contact> {
=======
public class ProductListAdapter extends ArrayAdapter<Product> {
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/adapters/ProductListAdapter.java
	@NonNull
	@Override
	public View getView(int position, View convertView, @NonNull ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
			view = inflater.inflate(R.layout.list_view_item_contact, parent, false);
		}

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/adapters/ContactListAdapter.java
		Contact contact = this.getItem(position);
		if (contact != null) {
			TextView contactIdTextView = (TextView) view.findViewById(R.id.list_view_item_contact_contact_id);
			if (contactIdTextView != null) {
				contactIdTextView.setText(contact.getContactId());
			}

			TextView countTextView = (TextView) view.findViewById(R.id.list_view_item_contact_data1);
=======
		Product product = this.getItem(position);
		if (product != null) {
			TextView lookupCodeTextView = (TextView) view.findViewById(R.id.list_view_item_contact_name);
			if (lookupCodeTextView != null) {
				lookupCodeTextView.setText(product.getLookupCode());
			}

			TextView countTextView = (TextView) view.findViewById(R.id.list_view_item_contact_number);
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/adapters/ProductListAdapter.java
			if (countTextView != null) {
				countTextView.setText(contact.getData()[0]);
			}
		}

		return view;
	}

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/adapters/ContactListAdapter.java
	public ContactListAdapter(Context context, List<Contact> contacts) {
		super(context, R.layout.list_view_item_contact, contacts);
=======
	public ProductListAdapter(Context context, List<Product> products) {
		super(context, R.layout.list_view_item_product, products);
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/adapters/ProductListAdapter.java
	}
}
