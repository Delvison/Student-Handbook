package com.example.studentplanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAssignmentActivity extends Activity {
	Button createActivity;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assignment_view);
        createActivity = (Button) findViewById(R.id.createAssignment);
    }
	public void handleClick(View v){
	
	}

}
