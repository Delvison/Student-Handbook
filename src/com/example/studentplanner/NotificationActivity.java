package com.example.studentplanner;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class NotificationActivity extends Activity {
	AlarmManager alarm;
	GregorianCalendar cal;
	String title;
	int month;
	int day;
	int year;
	int hour;
	int minute;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);      
        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        month = extras.getInt("month");
        day = extras.getInt("day");
        year = extras.getInt("year");
        hour = extras.getInt("hour");
        minute = extras.getInt("minute");
        setAlarm();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_notification, menu);
        return true;
    }
    
    public void setAlarm(){
    	 Intent intent = new Intent(this, AlarmPopupActivity.class);
    	 PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    	 alarm = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
         alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent);   
    }
    
    
}
