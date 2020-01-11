package com.adgyde.example;


import android.content.Context;
import android.os.Bundle;
import android.print.PrintJob;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adgyde.android.AdGyde;

public class UserSignin extends AppCompatActivity {

    EditText email_txt, phone_txt;
    Button button_submit;
    String  Phone_Number,Email_id;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_in);
        context = this;
        email_txt = (EditText) findViewById(R.id.email_txt);
        phone_txt = (EditText) findViewById(R.id.phone_txt);

        button_submit =(Button) findViewById(R.id.button_submit);


        button_submit.setOnClickListener(new View.OnClickListener() 
		{
            @Override
            public void onClick(View view) 
			{
                /* 
				 * Email Id
				 * =============
				 * In case Advertiser needs to store and relate Application analytics data with the users email id then advertiser 
				 * can pass the same explicitly using AdGyde.setEmail(context, "support@adgyde.com") function. 
				 * Analytical data then can be shared along with email id
				 */
				
				Email_id = email_txt.getText().toString();
                AdGyde.setEmail(context, Email_id);

				/* 
				 * Phone Number
				 * =============
				 * In case Advertiser needs to store and relate Application analytics data with the users phone number then advertiser
				 * can pass the same explicitly using AdGyde.setPhoneno(context, "919876543210") function. 
				 * Analytical data then can be shared along with phone number
				 */

				Phone_Number=phone_txt.getText().toString();
                AdGyde.setPhoneno(context, Phone_Number);
            }
        });
    }






}