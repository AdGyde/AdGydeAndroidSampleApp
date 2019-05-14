
<img src="https://www.adgyde.com/img/logo.png"  width="75">


# AdGyde - Android Integration

----------
In case you face any issue or need any kind of clarification / understanding, We would request you to Kindly contact our support team at support@adgyde.com

# Overview

AdGyde is a Mobile Application Attribution tool which helps application owners / marketers in better advertisement targeting and in optimizing the ad expenditure. 

Attribution
AdGyde through its attribution SDK can determine which channel / partner the app install was delivered through. Post install events, User Flow and how the application is being used is shown by AdGyde. This data can help advertiser / Application owner to specifically target user segments, find out best channel to improve upon ROI.

To get started contact AdGyde support - support@adgyde.com
---

# Integration Process for AdGyde Android SDK `v4.0.3`
You can also access the complete documentation from belo link as well
https://www.adgyde.com/documents.php

## Table of content

- [Get Your App Key](#getyourkey)
- [Download Android SDK](#downloadsdk)
- [Integrate SDK Into Project](#integratesdk)
   - [3.1 Add library files into your project](#addlibrary)
   - [3.2 Follow below steps to import .aar file into your projects](#importaar)
   - [3.3 Initializing PAgent](#initpagent)
   - [3.4 Embed Google Play Services into Your App](#embedplayservice)
   - [3.5 Add Install receiver code in the androidmanifest.xml](#addinstallreceiver)
   - [3.6 Add permissions to project](#addpermission)
   - [3.7 Add dependency to project](#adddependency)
- [Deep Linking](#deeplinking)
- [Uninstall Tracking](#uninstalltracking)
- [Demography](#demography)
   - [6.1 For Age Profile](#ageprofile)
   - [6.2 For Gender Profile](#genderprofile)

### <a id="getyourkey">
#  Get your App Key
Sign-in to your AdGyde Console, the credentials would have already been provided by AdGyde support team. In case you have not yet received the same, please contact the AdGyde Support Team. 

Please follow the given steps :- 
- Step 1) Visit the AdGyde Website - https://www.adgyde.com
- Step 2) Go to console
- Step 3) Sign-in with your credentials
- Step 4) Go to Setup -> Applications from the menu option
- Step 5) Click on "Create an application" option on Top Right corner
- Step 6) Fill in the Application Name and Package Name
- Step 7) Note down the App Key for integration reference


### <a id="downloadsdk">
#  Download Android SDK
Please download the SDK from "Download SDK" link in top right corner of AdGyde Console.

<img src="https://www.adgyde.com/img/documents/download_sdk.jpg"  width="700">

Integrate the downloaded SDK using the below steps

### <a id="integratesdk">
# Integrate SDK into project

### <a id="addlibrary">
#### 3.1) Add library files into your project
 - Unzip AdGyde Android SDK
 - Add ADGYDE_ANDROID_SDK *.aar file to your android project

### <a id="importaar">
#### 3.2) Follow below steps to import .aar file into your projects 
 - Go to File>New>New Module
 - Select "Import .JAR/.AAR Package" and click next.
 - Enter the path to .aar file and click finish.
 - Go to File>Project Structure (Ctrl + Shift + Alt + S).
 - Under "Modules," in left menu, select "app."
 - Go to "Dependencies tab.
 - Click the green "+" in the upper right corner.
 - Select "Module Dependency"
 - Select the ADGYDE_ANDROID_SDK .AAR file from the list.

### <a id="initpagent">
#### 3.3) Initializing PAgent
Android SDK needs to be initialized in the application. Please check Example project on Android SDK for complete code.

```package com.adgyde.example;

import android.app.Application;
import android.util.Log;
import com.adgyde.android.PAgent; 

public class ExampleApplication extends Application implements
Constants {
@Override 

public void onCreate() {
super.onCreate(); 

Log.d(TAG, "ExampleApplication.onCreate()");
// Default channel is Organic
PAgent.init(this, "Your Appkey", "Organic"); 
PAgent.flush(); 

PAgent.setDebugEnabled(true); 
}
}
```

Please don't initialize the AdGyde SDK on Main Activity, this will result in incorrect session data.

Request you to create a separate application class or use any application class already present in your project. For New application class - create ExampleApplication.java, and add the details under application tag in AndroidManifest.xml. 

```<application android:allowBackup="true" android:icon="@mipmap/ic_launcher"
android:label="@string/app_name" android:supportsRtl="true"
android:theme="@style/AppTheme"
android:name= "com.adgyde.example.ExampleApplication"> 

<!- application configuration items -->

</application>
```

Value of android:name parameter should be full name of the newly created application class as shown in the above code snippet

### <a id="embedplayservice">
#### 3.4) Embed Google Play Services into Your App 
Install the Google Play Services SDK and import it into your project. For download details, <a href="https://developers.google.com/android/guides/setup">Click here</a>.
Add the below code / entry to the AndroidManifest.xml as the last entry in the application tag (Just before </application>)

```<meta-data android:name="com.google.android.gms.version" android:value="@integer/google_play_services_version"/>```

NOTE:
AdGyde recommends to always use the latest version of google play service.

For more details see the link below.
https://developer.android.com/google/play-services/setup.html
Please refer below code

```< application
android:name=".ExampleApplication"
android:allowBackup="true"
android:icon="@mipmap/ic_launcher"
android:label="@string/app_name"
android:roundIcon="@mipmap/ic_launcher_round"
android:supportsRtl="true" 
android:theme="@style/AppTheme">

<meta-data
android:name="com.google.android.gms.version"
android:value="@integer/google_play_services_version" />


<activity android:name=".MainActivity">
<intent-filter>
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" \>
</intent-filter>
</activity>

<service android:name="com.adgyde.android.AppJobService"android:exported="true"
android:permission="android.permission.BIND_JOB_SERVICE">
</service>
```

### <a id="addinstallreceiver">
#### 3.5) Add Install receiver code in the androidmanifest.xml

Note: 
Please make sure that following receiver tag is kept inside application tag.

```<receiver android:name="com.adgyde.android.InstallReceiver" android:exported="true">
<intent-filter>
<action android:name="com.android.vending.INSTALL_REFERRER" />
</intent-filter>
</receiver>
```

If an application uses multiple INSTALL_REFERRER receivers, you should use com.adgyde.android.MultiInstallReceiver instead of com.adgyde.android.InstallReceiver.
MultiInstallReceiver MUST be the first receiver on Top of all the other INSTALL_REFERRER receivers

```<receiver android:name="com.adgyde.android.MultiInstallReceiver" android:exported="true">
<intent-filter>
<action android:name="com.android.vending.INSTALL_REFERRER" />
</intent-filter>
</receiver>
```

If you want to use multiple receivers, then the Manifest.xml file should look like this:

```<!--The AdGyde Install Receiver should be placed first and it will broadcast to all receivers placed below it -->
<receiver android:name="com.adgyde.android.MultiInstallReceiver" android:exported="true">
<intent-filter>
<action android:name="com.android.vending.INSTALL_REFERRER" />
</intent-filter>
</receiver> 

<!--All other receivers should be followed right after -->
<receiver android:name="com.google.android.abc.AbcReceiver" android:exported="true">
<intent-filter>
<action android:name="com.android.vending.INSTALL_REFERRER" />
</intent-filter>
</receiver> 

<receiver android:name="com.adsmobi.android.xyz.InstallReceiver" android:exported="true">
<intent-filter>
<action android:name="com.android.vending.INSTALL_REFERRER" />
</intent-filter>
</receiver>
```

### <a id="addpermission">
#### 3.6) Add permissions to project
Add following permissions to AndroidManifest.xml

```<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"> 
</usespermission>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.INTERNET"> 
</uses-permission>
```

### <a id="adddependency">
#### 3.7) Add dependency to project
Add the following dependency to Android gradle file (Module : android).

```dependencies {
// . . .
compile 'com.android.installreferrer:installreferrer:1.0'
// . . .
}
```

### <a id="deeplinking">
# 4. Deep Linking
Deep linking is the act of launching a mobile app while serving personalized content or sending the users to specific activities within the application. 

<a href="https://www.adgyde.com/documents.php?topic=article14&platform=android" >Deep Linking Detailed Integration Process </a>

### <a id="uninstalltracking">
# 5. Uninstall Tracking
AdGyde's Uninstall Tracking functionality allows you to track the number of uninstalls for a specified application. Uninstalls is an important index which helps you to track the quality of users and hence the campaign. 

<a href="https://www.adgyde.com/documents.php?topic=article11&platform=android" >Un-Install Detailed Integration Process</a>


### <a id="demography">
# 6. Demography
AdGyde demography data provides details of Age and Gender wise segregation of User, this can be used by Advertiser to target the new users and run their campaigns effectively. Campaigns can be run, keeping in mind that the application is say - majorly used by Females and 26-35 Age group, so Advertiser can ask a publisher to target only these groups and get maximum installs and return on the cost spent. 

### <a id="ageprofile">
#### 6.1 For Age Profile
The user age profile demography shows the advertiser the user segregation and user concentration on specific age segments. Having this data assists app marketer to take more accurate measurements and helps in decision making. 

Data can be passed to SDK by following 2 functions
PAgent.setAge(Context context, int years, int month, int day);
PAgent.setAge(Context context, int age);

```// Age to be calculated from Year, Month, Day
PAgent.setAge(context, 1991, 05, 03);

// Age Value
PAgent.setAge(context, 32);
```
<img src="https://www.adgyde.com/img/documents/age_map.jpg" width=700>




### <a id="genderprofile">
#### 6.2 For Gender Profile
The User gender profile demography shows the advertiser the user segregation and user concentration on specific Gender segments. Having this data, assists app marketer to take more accurate measurements and helps in decision making. 

Syntax : PAgent.setGender(Context context , String gender); 

Values which are permitted in Function
1. Male (M)
2. Female (F)
3. Others (O)

```// when you pass string for Male
PAgent.setGender(context,PAgent.M);

// when you pass string for Female
PAgent.setGender(context,PAgent.F);

// when you pass string for Others
PAgent.setGender(context,PAgent.O);
```
<img src="https://www.adgyde.com/img/documents/gender_map.jpg" width=400>
