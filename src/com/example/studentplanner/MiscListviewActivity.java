package com.example.studentplanner;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MiscListviewActivity extends ListActivity {
	String[] miscArr;
	String sName;
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				sName = extras.getString("key");
			}
	        try{
	            //open database
	    	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
	    	    //query. receive a cursor
	    	    Cursor c= db.rawQuery("SELECT MiscName FROM Miscs where Semester ='"+sName+"'", null);
	    	    //count how many items in cursor. add 1 to leave space for add semester option
	    	    int i = c.getCount() + 1;
	    	    //instantiate array of semesters by size of the cursor + 1
	    	    miscArr = new String[i];
	    	    //set up a count int to keep track of array positions
	    	    int count = 0;
	            //move the cursor to first position
	    	    c.moveToFirst();
	    	    
	            //while the cursor position isn't passed the last item in the cursor 
	    	    while(c.isAfterLast()==false) {
	    	    		//store the string in "Session" column into the array of semesters
	    	    		miscArr[count] = c.getString(c.getColumnIndex("MiscName"));
	    	    		//increment count
	    	    		count++;
	    	    		//move cursor by 1
	    	    		c.moveToNext();
	    	    }
	    	    //add an "add semester option
	    	    miscArr[count] = new String("+Add Misc Event");
	    	    //close the cursor
	    	    c.close();
	    	    //close the database
	       	   	db.close();
	       	   	//set a listadapter, use the semester_menu_view view and the semesterArr array
	       	   	setListAdapter(new ArrayAdapter<String>(this, R.layout.semester_listview,miscArr));//needs an array
	       	   	//get the list view from the view
	       	   	ListView listView = getListView();
	       	   	//set the listview's textfilter to enabled
	       	   	listView.setTextFilterEnabled(true);
	       	   	//add an onclicklistener
	       	   	listView.setOnItemClickListener(new OnItemClickListener() {
	    		
	       	   		public void onItemClick(AdapterView<?> parent, View view,
	       	   			int position, long id) {
	       	   				//this string holds the list item clicked
	       	   				String rightMeow = miscArr[position];

	       	   				// When clicked, show a toast with the TextView text
	       	   				//Toast.makeText(getApplicationContext(),
	       	   				//((TextView) view).getText(), Toast.LENGTH_SHORT).show();
	       	   				//if item clicked equals add semester
	       	   				
	       	   				if (rightMeow.equals("+Add Misc Event")){  
	       	   					Intent intent = new Intent(getApplicationContext(), CreateMiscActivity.class);
	       	   					//then go to the CreateSemesterActivity
	       	   					startActivity(intent);
	       	   				} else {
	       	   					Intent intent = new Intent(getApplicationContext(), CourseActivity.class);
	       	   					intent.putExtra("key", rightMeow);
	       	   					startActivity(intent);
	       	   				}
	       	   		}
	       	   	}); //close listener
	       	  
	        }catch(SQLiteException e){
	            // Create the alert box
	           AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

	           // Set the message to display
	           alertbox.setMessage("Welcome! To get started please click on Add Misc Event!");

	           // Add a neutral button to the alert box and assign a click listener
	           alertbox.setNeutralButton("Add Misc Event", new DialogInterface.OnClickListener() {

	               // Click listener on the neutral button of alert box
	               public void onClick(DialogInterface arg0, int arg1) {

	                   // The neutral button was clicked
	            	   Intent intent = new Intent(getApplicationContext(), CreateMiscActivity.class);
	                   startActivity(intent);
	               }
	           });

	            // show the alert box
	           alertbox.show();
	       }
	        
	    }//end of onCreate()
}
