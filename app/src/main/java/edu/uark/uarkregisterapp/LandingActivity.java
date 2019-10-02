package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.User;
import edu.uark.uarkregisterapp.models.api.UserLogin;
import edu.uark.uarkregisterapp.models.api.services.UserService;
import edu.uark.uarkregisterapp.models.transition.UserTransition;

public class LandingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	public void createUserButtonOnClick(View view){
		startActivity(new Intent(LandingActivity.this, CreateUserActivity.class));
	}

	public void signInButtonOnClick(View view) {
		if (StringUtils.isBlank(this.getUserIdEditText().getText().toString())) {
			new AlertDialog.Builder(this)
				.setMessage(R.string.alert_dialog_user_id_empty)
				.create()
				.show();
			this.getUserIdEditText().requestFocus();

			return;
		}

		if (StringUtils.isBlank(this.getPasswordEditText().getText().toString())) {
			new AlertDialog.Builder(this)
				.setMessage(R.string.alert_dialog_user_password_empty)
				.create()
				.show();
			this.getPasswordEditText().requestFocus();

			return;
		}

		(new SignInTask()).execute(
			(new UserLogin())
				.setUserId(this.getUserIdEditText().getText().toString())
				.setPassword(this.getPasswordEditText().getText().toString())
		);
	}

	private EditText getUserIdEditText() {
		return (EditText) this.findViewById(R.id.edit_text_user_id);
	}

	private EditText getPasswordEditText() {
		return (EditText) this.findViewById(R.id.edit_text_password);
	}

	private class SignInTask extends AsyncTask<UserLogin, Void, ApiResponse<User>> {
		@Override
		protected void onPreExecute() {
			this.signInAlert = new AlertDialog.Builder(LandingActivity.this)
				.setMessage(R.string.alert_dialog_signing_in)
				.create();
			this.signInAlert.show();
		}

		@Override
		protected ApiResponse<User> doInBackground(UserLogin... userLogins) {
			if (userLogins.length > 0) {
				return (new UserService()).logIn(userLogins[0]);
			} else {
				return (new ApiResponse<User>())
					.setValidResponse(false);
			}
		}

		@Override
		protected void onPostExecute(ApiResponse<User> apiResponse) {
			this.signInAlert.dismiss();

			if (!apiResponse.isValidResponse()) {
				new AlertDialog.Builder(LandingActivity.this)
					.setMessage(R.string.alert_dialog_user_sign_in_failed)
					.create()
					.show();
				return;
			}

			Intent intent = new Intent(getApplicationContext(), MainActivity.class);

			intent.putExtra(
				getString(R.string.intent_extra_user)
				, new UserTransition(apiResponse.getData())
			);

			startActivity(intent);
		}

		private AlertDialog signInAlert;
	}
}
