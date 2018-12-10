package ca.organizerbuddy.aytona_fahmy_project;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;;

import java.text.DateFormat;
import java.util.Calendar;

public class ThirdActivity extends Activity {

    TextView textViewDate, txtClass;
    ImageButton btnAddClass, btnAddClassDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        textViewDate = findViewById(R.id.txt_viewDate);
        textViewDate.setText(currentDate);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Organizer Buddy");
        //actionBar.setSubtitle(“Welcome to our app!“);
        actionBar.setIcon(R.drawable.profile);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        btnAddClass = findViewById(R.id.btn_addClass);

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddItemDialog(ThirdActivity.this);
            }
        });
    }

    private void showAddItemDialog(final Context context) {
        final EditText taskEditText = new EditText(context);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Class Name")
                .setMessage("Enter the class name:")
                .setView(taskEditText)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO add into recycler and update

                    }
                })
                .setNegativeButton("Reset", null)
                .create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.alertTimer:
                break;

            case R.id.warningTimer:
                break;

            case R.id.logout:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void find_weather()
    {
        String url ="https://www.theweathernetwork.com";



    }
}
