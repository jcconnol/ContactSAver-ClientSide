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
<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/ContactsListingActivity.java
import edu.uark.uarkregisterapp.models.api.Contact;
import edu.uark.uarkregisterapp.models.api.services.ContactService;
import edu.uark.uarkregisterapp.models.transition.ContactTransition;
=======
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.services.ProductService;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/ProductsListingActivity.java

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

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/ContactsListingActivity.java
		this.contacts = new ArrayList<>();
		this.contactListAdapter = new ContactListAdapter(this, this.contacts);
=======
		this.products = new ArrayList<>();
		this.productListAdapter = new ProductListAdapter(this, this.products);
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/ProductsListingActivity.java

		this.getContactsListView().setAdapter(this.contactListAdapter);

		this.getContactsListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/ContactsListingActivity.java
				//TODO makes each contact in list clickable, when clicked checks box to add to Heroku
=======
				Intent intent = new Intent(getApplicationContext(), ProductViewActivity.class);

				intent.putExtra(
					getString(R.string.intent_extra_product),
					new ProductTransition((Product) getProductsListView().getItemAtPosition(position))
				);
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/ProductsListingActivity.java

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
<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/ContactsListingActivity.java
		protected ApiResponse<List<Contact>> doInBackground(Void... params) {
			ApiResponse<List<Contact>> apiResponse = (new ContactService()).getContacts();
=======
		protected ApiResponse<List<Product>> doInBackground(Void... params) {
			ApiResponse<List<Product>> apiResponse = (new ProductService()).getProducts();
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/ProductsListingActivity.java

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
<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/ContactsListingActivity.java
				new AlertDialog.Builder(ContactsListingActivity.this).
					setMessage(R.string.alert_dialog_contacts_load_failure).
=======
				new AlertDialog.Builder(ProductsListingActivity.this).
					setMessage(R.string.alert_dialog_products_load_failure).
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/ProductsListingActivity.java
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

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/ContactsListingActivity.java
		private RetrieveContactsTask() {
			this.loadingContactsAlert = new AlertDialog.Builder(ContactsListingActivity.this).
				setMessage(R.string.alert_dialog_contacts_loading).
=======
		private RetrieveProductsTask() {
			this.loadingProductsAlert = new AlertDialog.Builder(ProductsListingActivity.this).
				setMessage(R.string.alert_dialog_products_loading).
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/ProductsListingActivity.java
				create();
		}
	}

<<<<<<< HEAD:app/src/main/java/edu/uark/uarkregisterapp/ContactsListingActivity.java
	private List<Contact> contacts;
	private ContactListAdapter contactListAdapter;
=======
	private List<Product> products;
	private ProductListAdapter productListAdapter;
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401:app/src/main/java/edu/uark/uarkregisterapp/ProductsListingActivity.java
}
