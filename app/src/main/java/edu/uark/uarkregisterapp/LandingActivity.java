package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
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

	public void createUserButtonOnClick(View view) {
		startActivity(new Intent(LandingActivity.this, CreateUserActivity.class));
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	public void signInButtonOnClick(View view) {
		if (StringUtils.isBlank(this.getEmployeeUsernameEditText().getText().toString())) {
			new AlertDialog.Builder(this)
				.setMessage(R.string.alert_dialog_employee_username_empty)
				.create()
				.show();
			this.getEmployeeUsernameEditText().requestFocus();

			return;
		}

		if (StringUtils.isBlank(this.getPasswordEditText().getText().toString())) {
			new AlertDialog.Builder(this)
				.setMessage(R.string.alert_dialog_employee_password_empty)
				.create()
				.show();
			this.getPasswordEditText().requestFocus();

			return;
		}

		(new SignInTask()).execute(
<<<<<<< HEAD
			(new UserLogin())
				.setEmployeeId(this.getEmployeeIdEditText().getText().toString())
=======
			(new EmployeeLogin())
				.setEmployeeId(this.getEmployeeUsernameEditText().getText().toString())
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401
				.setPassword(this.getPasswordEditText().getText().toString())
		);
	}

	public void createEmployeeButtonOnClickFromLanding(View view){
		Intent i = new Intent(LandingActivity.this, CreateEmployeeActivity.class);
		startActivity(i);
	}

	private EditText getEmployeeUsernameEditText() {
		return (EditText) this.findViewById(R.id.edit_text_employee_username);
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
					.setMessage(R.string.alert_dialog_employee_sign_in_failed)
					.create()
					.show();
				return;
			}

			Intent intent = new Intent(getApplicationContext(), MainActivity.class);

			intent.putExtra(
				getString(R.string.intent_extra_employee)
				, new UserTransition(apiResponse.getData())
			);

			startActivity(intent);
		}

		private AlertDialog signInAlert;
	}
}
