package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;


import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.User;
import edu.uark.uarkregisterapp.models.api.services.UserService;
import edu.uark.uarkregisterapp.models.transition.UserTransition;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  // Respond to the action bar's Up/Home button
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveButtonOnClick(View view) {
        if (!this.validateInput()) {
            return;
        }

        User making = new User();
        making.setId(new UUID(0,0));
        making.setFirstName(this.getFirstNameEditText().getText().toString());
        making.setLastName(this.getLastNameEditText().getText().toString());
        making.setPassword(this.getPasswordEditText().getText().toString());

        (new CreateUserTask()).execute(making);
    }

    private EditText getFirstNameEditText() {
        return (EditText) this.findViewById(R.id.edit_text_employee_create_first_name);
    }

    private EditText getLastNameEditText() {
        return (EditText) this.findViewById(R.id.edit_text_employee_create_last_name);
    }

    private EditText getPasswordEditText() {
        return (EditText) this.findViewById(R.id.edit_text_employee_create_password);
    }

    private EditText getPasswordConfirmEditText() {
        return (EditText) this.findViewById(R.id.edit_text_employee_create_password_confirm);
    }

    private boolean validateInput() {
        boolean validInput = true;

        if (StringUtils.isBlank(this.getFirstNameEditText().getText().toString())) {
            this.displayValidationAlert(R.string.alert_dialog_employee_create_validation_first_name);
            this.getFirstNameEditText().requestFocus();
            validInput = false;
        }
        if (validInput && StringUtils.isBlank(this.getLastNameEditText().getText().toString())) {
            this.displayValidationAlert(R.string.alert_dialog_employee_create_validation_last_name);
            this.getLastNameEditText().requestFocus();
            validInput = false;
        }
        if (validInput && StringUtils.isBlank(this.getPasswordEditText().getText().toString())) {
            this.displayValidationAlert(R.string.alert_dialog_employee_create_validation_password);
            this.getLastNameEditText().requestFocus();
            validInput = false;
        }
        if (validInput && !this.getPasswordEditText().getText().toString().equals(this.getPasswordConfirmEditText().getText().toString())) {
            this.displayValidationAlert(R.string.alert_dialog_employee_create_validation_password_invalid);
            this.getLastNameEditText().requestFocus();
            validInput = false;
        }

        return validInput;
    }

    private void displayValidationAlert(int stringId) {
        new AlertDialog.Builder(this)
                .setMessage(stringId)
                .create()
                .show();
    }

    private class CreateUserTask extends AsyncTask<User, Void, ApiResponse<User>> {
        @Override
        protected void onPreExecute() {
            this.createEmployeeAlert = new AlertDialog.Builder(CreateUserActivity.this)
                    .setMessage(R.string.alert_dialog_employee_create)
                    .create();
            this.createEmployeeAlert.show();
        }

        @Override
        protected ApiResponse<User> doInBackground(User... users) {
            if (users.length > 0) {
                Log.d("USERDATA", "doInBackground: "+users[0].getId());
                Log.d("USERDATA", "doInBackground: "+users[0].getUserName());
                Log.d("USERDATA", "doInBackground: "+users[0].getLastName());
                Log.d("USERDATA", "doInBackground: "+users[0].getFirstName());

                return (new UserService()).createUser(users[0]);
            } else {
                return (new ApiResponse<User>())
                        .setValidResponse(false);
            }
        }

        @Override
        protected void onPostExecute(ApiResponse<User> apiResponse) {
            this.createEmployeeAlert.dismiss();
            Log.d("USERPOST", "onPostExecute: GOT HERE");
            if (!apiResponse.isValidResponse()) {
                new AlertDialog.Builder(CreateUserActivity.this)
                        .setMessage(R.string.alert_dialog_employee_create_failed)
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

        private AlertDialog createEmployeeAlert;
    }
}