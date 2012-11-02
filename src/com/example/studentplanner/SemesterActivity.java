package com.example.studentplanner;

import android.app.Activity;

public class SemesterActivity extends Activity {
	//recieve semester chosen from menu
	//retrieve info from sqlite database
	//show data on view
}

/*
 * intent.putExtra("keyName", "somevalue");

We can add multiple entries here. This is a key,value pair. So to receive this data from the receiving activity we have to write this code

Bundle extras = getIntent().getExtras(); 
if(extras !=null)
{
String value = extras.getString("keyName");
}


Read more: http://getablogger.blogspot.com/2008/01/android-pass-data-to-activity.html#ixzz2B13hEbbh

*/
