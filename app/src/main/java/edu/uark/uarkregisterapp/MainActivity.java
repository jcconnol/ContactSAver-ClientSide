package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import edu.uark.uarkregisterapp.models.transition.UserTransition;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        this.userTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_employee));
    }

    @Override
    protected void onStart() {
        super.onStart();

<<<<<<< HEAD
        this.getEmployeeWelcomeTextView().setText("Welcome " + this.userTransition.getFirstName() + " (" + this.userTransition.getEmployeeId() + ")!");
    }

    public void logOutButtonOnClick(View view) {
        //TODO make alert that you are about to logout

        this.startActivity(new Intent(getApplicationContext(), LandingActivity.class));
=======
        String welcomeText = "Welcome " + this.employeeTransition.getFirstName() + " (" + this.employeeTransition.getEmployeeId() + ")!";
        this.getEmployeeWelcomeTextView().setText(welcomeText);
    }

    public void goToProductViewButtonOnClick(View view) {
        this.startActivity(new Intent(getApplicationContext(), LandingActivity.class));;
    }

    public void goToProductsListingButtonOnClick(View view) {
        this.startActivity(new Intent(getApplicationContext(), ProductsListingActivity.class));;
    }

    public void logOutButtonOnClick(View view) {
        this.startActivity(new Intent(getApplicationContext(), ProductViewActivity.class));
>>>>>>> b46c1c2bc4732966eb4cbca71033467510027401
    }

    private TextView getEmployeeWelcomeTextView() {
        return (TextView)this.findViewById(R.id.text_view_employee_welcome);
    }

    private UserTransition userTransition;
}
