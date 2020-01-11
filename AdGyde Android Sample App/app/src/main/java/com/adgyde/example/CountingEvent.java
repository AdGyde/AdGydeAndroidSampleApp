package com.adgyde.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adgyde.android.AdGyde;

import java.util.HashMap;

public class CountingEvent extends AppCompatActivity implements View.OnClickListener {

    Button local, national, international;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting_event);
        local = (Button) findViewById(R.id.local);
        local.setOnClickListener(this);
        national = (Button) findViewById(R.id.national);
        national.setOnClickListener(this);
        international = (Button) findViewById(R.id.international);
        international.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.local:
                AdgydeCounting("Local");
                Toast.makeText(this, "Local news clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.national:
                AdgydeCounting("national");
                Toast.makeText(this, "National news clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.international:
                AdgydeCounting("international");
                Toast.makeText(this, "International news clicked", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    /* 
	 * Counting Event
	 * =============
     * The below code is the example to pass a Counting event to the AdGyde SDK.
	 * This event is used to get Sub-Category Counting values.
	 * Multiple values Can be passed for getting counted using same parameter.
	 * When user passes multiple values, the console shows the counting of each value seperately
	 * 
	 * NOTE : Creating the Counting Event on Console with Event ID, Parameter is Compulsory
	 *
	 */
	
    public void AdgydeCounting(String value) {
        HashMap<String, String> params = new HashMap<String, String>();

		// Multiple values can be passed through this event and each value will be counted and displayed in panel seperately
		// Under Counting event -> News event there will be 3 Values - "Today", "Local", "International" showing 1 count each. 
		params.put("parameterName", value);//Event name,value

		// Event is triggered with EventId and Parameters prepared above, the same are passed in this function
		AdGyde.onCountingEvent("News", params);//eventid

    }
}
