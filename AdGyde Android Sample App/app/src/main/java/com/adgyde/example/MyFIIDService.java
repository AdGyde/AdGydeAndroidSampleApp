package com.adgyde.example;

import android.util.Log;

import com.adgyde.android.AdGyde;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFIIDService extends FirebaseMessagingService implements Constants  {

     /* 
	 * AdGyde's Uninstall Tracking functionality allows you to track the number of uninstalls for your application.
     * For the uninstall functionality to work AdGyde requires the FCM token.
	 * 
	 * Application can pass the FCM token directly to AdGyde by calling AdGyde's "com.adgyde.android.FIIDService" Service in Manifest file or else
	 *
	 * If application has multiple InstanceIDListener services, then the same can be passed using application's pre-existing Listener 
	 * Just pass the Token to AdGyde's API through the application's pre-existing Listener
     *
	 * Syntax :- AdGyde.onTokenRefresh(token);
     */

	@Override
	public void onNewToken(String token) {
		super.onNewToken(token);
		AdGyde.onTokenRefresh(token); // This Function passed the FCm Token to AdGyde SDK for uninstall tracking
	}
}