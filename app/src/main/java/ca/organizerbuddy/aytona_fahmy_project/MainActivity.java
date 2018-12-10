package ca.organizerbuddy.aytona_fahmy_project;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.organizerbuddy.aytona_fahmy_project.db.AccountDao;
import ca.organizerbuddy.aytona_fahmy_project.db.AccountRoomDb;
import ca.organizerbuddy.aytona_fahmy_project.model.Account;

public class MainActivity extends Activity {

    public static final String ACCOUNT_LOGIN = "account_login";

    private static final int CREATE_ACTIVITY = 2;
    private static final int ACCOUNT_ACTIVITY = 3;

    Button btnLogin, btnRegister;
    EditText edtName, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edt_username);
        edtPass = findViewById(R.id.edt_password);



        btnRegister = findViewById(R.id.btn_newUser);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccountActivity();
            }
        });
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckInput()) {
                    Login();
                }
            }
        });
    }

    private boolean CheckInput() {
        boolean passedInput = true;
        if (edtName.getText().length() == 0) {
            passedInput = false;
            edtName.setError("Username Required");
        }
        if (edtPass.getText().length() == 0) {
            passedInput = false;
            edtPass.setError("Password Required");
        }
        return passedInput;
    }

    private void Login() {
        String[] loginInfo = {edtName.getText().toString(), edtPass.getText().toString()};
        RetrieveAccount retrieveAccount = new RetrieveAccount();
        retrieveAccount.execute(loginInfo);
    }

    // Just goes to the create account activity
    private void CreateAccountActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, CREATE_ACTIVITY);
    }

    private void GetAccountInfo(Intent data) {
        Account account = data.getParcelableExtra(ACCOUNT_LOGIN);
        edtName.setText(account.getUsername());
        edtPass.setText(account.getPassword());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            GetAccountInfo(data);
            if (requestCode == ACCOUNT_ACTIVITY) {
                Toast.makeText(getApplicationContext(), "Successfully logged out", Toast.LENGTH_LONG).show();
            }
            if (requestCode == CREATE_ACTIVITY) {
                Toast.makeText(getApplicationContext(), "Account successfully created", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Checks if there is an account that matches username and password
    private class RetrieveAccount extends AsyncTask<String[], Void, Account> {

        @Override
        protected Account doInBackground(String[]... strings) {
            AccountRoomDb accountRoomDb = AccountRoomDb.getDatabase(getApplicationContext());
            AccountDao accountDao = accountRoomDb.accountDao();
            if (!accountDao.getAllAccounts().isEmpty()) {
                for (Account temp : accountDao.getAllAccounts()) {
                    if (temp.getUsername().equals(strings[0][0])
                        && temp.getPassword().equals(strings[0][1])) {
                        return temp;
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Account account) {
            if (account != null) {
                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                intent.putExtra(ACCOUNT_LOGIN, account);
                startActivityForResult(intent, ACCOUNT_ACTIVITY);
            } else {
                Toast.makeText(getApplicationContext(), "User/pass doesn't match", Toast.LENGTH_LONG).show();
            }
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.main, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId())
//        {
//            case R.id.profilePicture:
//               //allow user to select picture from documents
//                break;
//
//            case R.id.settings:
//
//                break;
//
//            case R.id.logout:
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}
