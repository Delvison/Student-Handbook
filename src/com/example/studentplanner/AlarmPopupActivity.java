package com.example.studentplanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;;

public class AlarmPopupActivity extends Activity {
	TextView textView;
	String name = "Assignment";	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_popup_view);
        Bundle extras = getIntent().getExtras();
        textView = (TextView) findViewById(R.id.textView1);
        textView.setText(name +"blank is due in" );
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alarm_popup_activity, menu);
        return true;
    }
    
    public void onHandle(){
    	finish();
    }
}
