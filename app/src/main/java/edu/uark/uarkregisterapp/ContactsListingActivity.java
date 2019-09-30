package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.uark.uarkregisterapp.adapters.ProductListAdapter;
import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Contact;
import edu.uark.uarkregisterapp.models.api.services.ContactService;
import edu.uark.uarkregisterapp.models.transition.ContactTransition;

public class ProductsListingActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts_listing);
		setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

		ActionBar actionBar = this.getSupportActionBar();
		if (actionBar != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		this.contacts = new ArrayList<>();
		this.contactListAdapter = new ContactListAdapter(this, this.contacts);

		this.getContactsListView().setAdapter(this.contactListAdapter);

		this.getContactsListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//TODO makes each contact in list clickable, when clicked checks box to add to Heroku
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		(new RetrieveContactsTask()).execute();
	}

	private ListView getContactsListView() {
		return (ListView) this.findViewById(R.id.list_view_contacts);
	}

	private class RetrieveContactsTask extends AsyncTask<Void, Void, ApiResponse<List<Contact>>> {
		@Override
		protected void onPreExecute() {
			this.loadingContactsAlert.show();
		}

		@Override
		protected ApiResponse<List<Contact>> doInBackground(Void... params) {
			ApiResponse<List<Contact>> apiResponse = (new ContactService()).getContacts();
			if (apiResponse.isValidResponse()) {
				contacts.clear();
				contacts.addAll(apiResponse.getData());
			}

			return apiResponse;
		}

		@Override
		protected void onPostExecute(ApiResponse<List<Contact>> apiResponse) {
			if (apiResponse.isValidResponse()) {
				contactListAdapter.notifyDataSetChanged();
			}

			this.loadingContactsAlert.dismiss();

			if (!apiResponse.isValidResponse()) {
				new AlertDialog.Builder(ContactsListingActivity.this).
					setMessage(R.string.alert_dialog_contacts_load_failure).
					setPositiveButton(
						R.string.button_dismiss,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						}
					).
					create().
					show();
			}
		}

		private AlertDialog loadingContactsAlert;

		private RetrieveContactsTask() {
			this.loadingContactsAlert = new AlertDialog.Builder(ContactsListingActivity.this).
				setMessage(R.string.alert_dialog_contacts_loading).

				create();
		}
	}

	private List<Contact> contacts;
	private ContactListAdapter contactListAdapter;

}
