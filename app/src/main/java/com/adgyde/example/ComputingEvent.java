package com.adgyde.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.adgyde.android.PAgent;

import java.util.HashMap;
import java.util.Map;

public class ComputingEvent extends AppCompatActivity implements View.OnClickListener {

    Button flat1, flat2, flat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computing_event);

        flat1 = (Button) findViewById(R.id.flat1);
        flat1.setOnClickListener(this);
        flat2 = (Button) findViewById(R.id.flat2);
        flat2.setOnClickListener(this);
        flat3 = (Button) findViewById(R.id.flat3);
        flat3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flat1:
                onAdvertismentClicked1();
                break;
            case R.id.flat2:
                onAdvertismentClicked2();
                break;
            case R.id.flat3:
                onAdvertismentClicked3();
                break;
        }
    }


   /* 
	 * Computing Event
	 * =============
     * The below code is the example to pass a Computing event to the AdGyde SDK.
	 * This event is used to get Sub-Category counting as per weightage of the Sub-Category
	 * Multiple values Can be passed for getting the computed values
	 * When user passes multiple values, the console shows the computed values of each value relatively
	 * 
	 * NOTE : Creating the Computing Event on Console with Event ID, Parameter is Compulsory
	 *
	 */
	
    public void onAdvertismentClicked1() {
        HashMap<String, String> params = new HashMap<String, String>();
		
        // Passing a computing event is a little complex
		// First the Sub-Category needs to be specified in a Parameter + Value combination
		// Then the Weightage of the Value needs to be specified in a Value + Weightage Combination
		// In below Example 30%off is a Sub-Category and 1 is the Weightage of the same
        params.put("ParameterName", "30%off");
        params.put("30%off", "1");
		
		// Event is triggered with EventId and Parameters prepared above, the same are passed in this function
        PAgent.onComputingEvent("Sale", params);
        Toast.makeText(this, "Computing event Ad1 clicked", Toast.LENGTH_SHORT).show();
    }

    public void onAdvertismentClicked2() {
        HashMap<String, String> params = new HashMap<String, String>();
		
        // In below Example 50%off is a Sub-Category and 2 is the Weightage of the same
        params.put("Sale", "50%off");
        params.put("50%off", "2");
       
        PAgent.onComputingEvent("Sale", params);
        Toast.makeText(this, "Computing event Ad2 clicked", Toast.LENGTH_SHORT).show();
    }

    public void onAdvertismentClicked3() {
        HashMap<String, String> params = new HashMap<String, String>();
      
		// In below Example 70%off is a Sub-Category and 3 is the Weightage of the same
        params.put("Sale", "70%off");
        params.put("70%off", "3");
		
        PAgent.onComputingEvent("Sale", params);
		Toast.makeText(this, "Computing event Ad3 clicked", Toast.LENGTH_SHORT).show();
    }
}
