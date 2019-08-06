package com.adgyde.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adgyde.android.PAgent;

import java.util.HashMap;

public class UniqueEvent extends AppCompatActivity {
    Button dailyunique, Permanentunique, Customunique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_event);

        dailyunique = (Button) findViewById(R.id.dailyunique);
        Permanentunique = (Button) findViewById(R.id.Permanentunique);
        Customunique = (Button) findViewById(R.id.Customunique);

        dailyunique.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailyUniqueArticle();
            }
        });
        Permanentunique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permanentUniqueArticle();
            }
        });
        Customunique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customUniqueArticle();
            }
        });
    }

    /* 
	 * Unique Event
	 * =============
     * Unique Event is useful to track event which needs to be tracked once in a time period.
	 * AdGyde SDK provides Unique Events in three types:- 
	 *        onDailyUnique.
	 *		  onPermanentUnique.
	 *		  onCustomUnique.
	 * You can implement these unique events as per your need.
	 * This event is useful to track event which needs to be tracked once / Uniquely in a Day.
	 * Multiple values Can be passed in the Event using multiple Parameters, but Uniqueness will be as per Event ID only
	 * 
	 * 
	 * NOTE : Creating the Unique Event on Console with Event ID, Parameter is Compulsory
	 *
	 */

	 
    /*
	* Daily Unique event allows you to keep a event unique for current date. 
	* In case you want to find out how many Unique users clicked on Daily Article page on 1st Jan 2019, then you can use this event
	*/
    public void dailyUniqueArticle() {
        HashMap<String, String> params = new HashMap<String, String>();
        // The paramter being passed in unique event are in combination of ParamterName and Value same as shown below
		// param.put( paramName, valueName );
        params.put("dailyunique", "dailyunique_value");
		
        // Event is triggered with EventId and Parameters prepared above, the same are passed in this function
        PAgent.onDailyUnique("dailyunique_id", params);
        Toast.makeText(this, "DailyUnique event clicked", Toast.LENGTH_SHORT).show();
    }

    /*
    * Permanent Unique event allows you to keep a event unique for user lifetime. 
	* In case you want to find out how many Unique users clicked on Article page in app lifetime, then you can use this event
	*/

    public void permanentUniqueArticle() {
        HashMap<String, String> params = new HashMap<String, String>();
        // The paramter being passed in unique event are in combination of ParamterName and Value same as shown below
		// param.put( paramName, valueName );
        params.put("permanentunique", "permanentunique_value");
		
		// Event is triggered with EventId and Parameters prepared above, the same are passed in this function
        PAgent.onPermanentUnique("permanentunique_id", params);
        Toast.makeText(this, "PermanentUnique event clicked", Toast.LENGTH_SHORT).show();
    }

    /*
    * Custom Unique event allows you to keep a event unique for custom time you require. 
	* In case you want to find out how many Unique users clicked on Article page during last 72 Hours, then you can use this event
	*/

    public void customUniqueArticle() {
         HashMap<String, String> params = new HashMap<String, String>();
        // The paramter being passed in unique event are in combination of ParamterName and Value same as shown below
		// param.put( paramName, valueName );
        params.put("customunique", "customunique_value");
		
        // Event is triggered with EventId and Parameters prepared above, the same are passed in this function
		// The third parameter is time in hours where you need to put the hour.
		// Track this Custom Unique events counts in hourly basis. 
        PAgent.onCustomUnique("customunique_id", params, 1);
        Toast.makeText(this,"CustomUnique event clicked", Toast.LENGTH_SHORT).show();
    }
}
