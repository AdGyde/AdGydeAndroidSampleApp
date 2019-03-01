package com.adgyde.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.adgyde.android.PAgent;
import com.adgyde.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    EditText et_usr_name, et_usr_gender;
    Button usr_submit;
    String age, gender;
    Spinner spinner;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        et_usr_name = (EditText) findViewById(R.id.et_usr_name);
        spinner = (Spinner) findViewById(R.id.et_usr_gender);
        usr_submit = (Button) findViewById(R.id.usr_submit);

        List<String> gen = new ArrayList<String>();
        gen.add("Select Gender");
        gen.add("M");
        gen.add("F");
        gen.add("O");

        ArrayAdapter<String> dataAdapater = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gen);
        spinner.setAdapter(dataAdapater);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* 
		 * AdGyde demography data provides details of Age and Gender wise segregation of Users.
         * This data needs to be passed by Applictaion to show the same in the console
		 */
		 
        usr_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean ageFlag = false;
                boolean genderFlag = false;

				/*
				 * Age data can be passed to SDK by following 2 functions which are shown in below code:-
				 *
				 * Syntax Type 1 :- PAgent.setAge(Context context, int years, int month, int day); 
				 * Syntax Type 2 :- PAgent.setAge(Context context, int age);
				 *
				 */
		 
                age = et_usr_name.getText().toString();
                if (!age.isEmpty()) {
                    int yrs = Integer.parseInt(age);
                    PAgent.setAge(UserProfile.this, yrs, 01, 01); // Syntax Type 1 :- PAgent.setAge(Context context, int years, int month, int day);
                    ageFlag = true;
                } else {
                    Toast.makeText(UserProfile.this, "please enter dob year", Toast.LENGTH_SHORT).show();
                }

				/* Gender value can be passed to the SDK using the below function.
				 * Only the below 3 Values can be passed to the function for Gender
				 * Male (M)
				 * Female (F)
				 * Others (O)
				 *
				 * Syntax :- PAgent.setGender(Context context , String gender);
				 * 
				 */

                if (item.equals("M")) {
                    genderFlag = true;
                    PAgent.setGender(UserProfile.this, PAgent.M); // For Specifing Gender as Male use M Macro
                }
                if (item.equals("F")) {
                    genderFlag = true;
                    PAgent.setGender(UserProfile.this, PAgent.F); // For Specifing Gender as Female use F Macro
                }
                if (item.equals("O")) {
                    genderFlag = true;
                    PAgent.setGender(UserProfile.this, PAgent.O); // For Specifing Gender as "Do not Want to Specify" or "Other" use O Macro

                }
                if (ageFlag && genderFlag) {
                    Toast.makeText(getApplicationContext(), "Age and Gender Submit", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}

