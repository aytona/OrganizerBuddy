package ca.organizerbuddy.aytona_fahmy_project;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.organizerbuddy.aytona_fahmy_project.db.AccountDao;
import ca.organizerbuddy.aytona_fahmy_project.db.AccountRoomDb;
import ca.organizerbuddy.aytona_fahmy_project.model.Account;

public class SecondActivity extends Activity {

    Button btnCreateAccount;
    EditText edtMakeUser, edtMakePass;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edtMakeUser = findViewById(R.id.edt_makeUsername);
        edtMakePass = findViewById(R.id.edt_makePassword);

        intent = getIntent();

        btnCreateAccount = findViewById(R.id.btn_createAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInput()) {
                    AddAccount();
                }
            }
        });
    }

    private boolean CheckInput() {
        boolean passedInput = true;
        if (edtMakeUser.getText().length() == 0) {
            passedInput = false;
            edtMakeUser.setError("Username Required");
        }
        if (edtMakePass.getText().length() == 0) {
            passedInput = false;
            edtMakePass.setError("Password Required");
        }
        return passedInput;
    }

    // Add the account to database
    private void AddAccount() {
        String[] accountInfo = {edtMakeUser.getText().toString(), edtMakePass.getText().toString()};
        AddAccountClass addAccountClass = new AddAccountClass();
        addAccountClass.execute(accountInfo);
    }

    // Starts the third activity, passing in the account that was just created
    private void AccountCreated(Account account) {
        intent.putExtra(MainActivity.ACCOUNT_LOGIN, account);
        setResult(RESULT_OK, intent);
        finish();
    }

    private class AddAccountClass extends AsyncTask<String[], Void, Account> {

        @Override
        protected Account doInBackground(String[]... strings) {
            AccountRoomDb accountRoomDb = AccountRoomDb.getDatabase(getApplicationContext());
            AccountDao accountDao = accountRoomDb.accountDao();
            Boolean accountExists = false;
            if (accountDao.getAllAccounts().size() > 0) {
                for (Account temp : accountDao.getAllAccounts()) {
                    if (temp.getUsername().equals(strings[0][0])
                            && temp.getPassword().equals(strings[0][1])) {
                        accountExists = true;
                    }
                }
            }
            if (!accountExists) {
                Account account = new Account(edtMakeUser.getText().toString(), edtMakePass.getText().toString());
                accountDao.insert(account);
                return account;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Account account) {
            if (account != null) {
                AccountCreated(account);
            } else {
                Toast.makeText(getApplicationContext(), "Account already exists", Toast.LENGTH_LONG).show();
            }
        }
    }
}
