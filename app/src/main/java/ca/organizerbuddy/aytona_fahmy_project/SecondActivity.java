package ca.organizerbuddy.aytona_fahmy_project;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.organizerbuddy.aytona_fahmy_project.model.Account;

public class SecondActivity extends Activity {

    Button btnCreateAccount;
    EditText edtUser, edtPass;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edtUser = findViewById(R.id.edt_makeUsername);
        edtPass = findViewById(R.id.edt_makePassword);

        intent = getIntent();

        btnCreateAccount.findViewById(R.id.btn_createAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidateAccount()) {
                    AddAcount();
                    AccountCreated();
                }
            }
        });
    }

    // Validates if the account doesn't already exists in the database
    private boolean ValidateAccount() {
        Boolean validation = false;

        return validation;
    }

    // Add the account to database
    private void AddAcount() {
        // TODO create database of accounts/users
    }

    // Starts the third activity, passing in the account that was just created
    private void AccountCreated(/*Account*/) {
        // TODO package account
        Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
        startActivity(intent);
    }

    private class ValidateClass extends AsyncTask<String[], Void, Account> {

        @Override
        protected Account doInBackground(String[]... strings) {

            return null;
        }

        @Override
        protected void onPostExecute(Account account) {
            if (account != null) {
                Toast.makeText(getApplicationContext(), "Account already exists", Toast.LENGTH_LONG).show();
                edtUser.setText(null);
                edtPass.setText(null);
            } else {

            }
        }
    }
}
