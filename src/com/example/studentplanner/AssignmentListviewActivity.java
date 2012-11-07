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


public class AssignmentListviewActivity extends ListActivity {
	String[] assignArr;
	String cName;
	
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Bundle extras = getIntent().getExtras();
	        if (extras != null){
	        	cName = extras.getString("key");
	        }
	        
	        try{
	            //open database
	    	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
	    	    //query. receive a cursor
	    	    Cursor cursor = db.rawQuery("select Name from Assignments where Course ='"+cName+"'", null);
	    	    //count how many items in cursor. add 1 to leave space for add semester option
	    	    int i = cursor.getCount() + 1;
	    	    //instantiate array of semesters by size of the cursor + 1
	    	    assignArr = new String[i];
	    	    //set up a count int to keep track of array positions
	    	    int count = 0;
	            //move the cursor to first position
	    	    cursor.moveToFirst();
	    	    
	            //while the cursor position isn't passed the last item in the cursor 
	    	    while(cursor.isAfterLast()==false) {
	    	    		//store the string in "Session" column into the array of semesters
	    	    		assignArr[count] = cursor.getString(cursor.getColumnIndex("Name"));
	    	    		//increment count
	    	    		count++;
	    	    		//move cursor by 1
	    	    		cursor.moveToNext();
	    	    }
	    	    //add an "add semester option
	    	    assignArr[count] = new String("+Add Assignment");
	    	    //close the cursor
	    	    cursor.close();
	    	    //close the database
	       	   	db.close();
	       	   	//set a listadapter, use the semester_menu_view view and the semesterArr array
	       	   	setListAdapter(new ArrayAdapter<String>(this, R.layout.assignment_listview,assignArr));//needs an array
	       	   	//get the list view from the view
	       	   	ListView listView = getListView();
	       	   	//set the listview's textfilter to enabled
	       	   	listView.setTextFilterEnabled(true);
	       	   	//add an onclicklistener
	       	   	listView.setOnItemClickListener(new OnItemClickListener() {
	    		
	       	   		public void onItemClick(AdapterView<?> parent, View view,
	       	   			int position, long id) {
	       	   				//this string holds the list item clicked
	       	   				String rightMeow = assignArr[position];

	       	   				// When clicked, show a toast with the TextView text
	       	   				//Toast.makeText(getApplicationContext(),
	       	   				//((TextView) view).getText(), Toast.LENGTH_SHORT).show();
	       	   				//if item clicked equals add semester
	       	   				
	       	   				if (rightMeow.equals("+Add Assignment")){  
	       	   					Intent intent = new Intent(getApplicationContext(), CreateAssignmentActivity.class);
	       	   					//then go to the CreateSemesterActivity
	       	   					startActivity(intent);
	       	   				} else {
	       	   					Intent intent = new Intent(getApplicationContext(), AssignmentActivity.class);
	       	   					intent.putExtra("key", rightMeow);
	       	   					startActivity(intent);
	       	   				}
	       	   		}
	       	   	}); //close listener
	       	  
	        }catch(SQLiteException e){

	            // Create the alert box
	           AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

	           // Set the message to display
	           alertbox.setMessage("No Assignments Exist for this Course!");

	           // Add a neutral button to the alert box and assign a click listener
	           alertbox.setNeutralButton("Add Assignment", new DialogInterface.OnClickListener() {

	               // Click listener on the neutral button of alert box
	               public void onClick(DialogInterface arg0, int arg1) {

	                   // The neutral button was clicked
	            	   Intent intent = new Intent(getApplicationContext(), CreateAssignmentActivity.class);
	                   startActivity(intent);
	               }
	           });

	            // show the alert box
	           alertbox.show();
	       }
	        
	    }//end of onCreate()
}
