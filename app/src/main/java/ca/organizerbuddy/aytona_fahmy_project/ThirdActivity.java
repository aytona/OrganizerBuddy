package ca.organizerbuddy.aytona_fahmy_project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;;

import java.text.DateFormat;
import java.util.Calendar;

public class ThirdActivity extends Activity {

    TextView textViewDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        textViewDate = findViewById(R.id.txt_viewDate);
        textViewDate.setText(currentDate);


    }



    public void find_weather()
    {
        String url ="https://www.theweathernetwork.com";



    }
}
