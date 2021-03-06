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


public class ExamListviewActivity extends ListActivity {
	String[] examArr;
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
	    	    Cursor cursor = db.rawQuery("select Name from Exams where Course ='"+cName+"'", null);
	    	    //count how many items in cursor. add 1 to leave space for add semester option
	    	    int i = cursor.getCount() + 1;
	    	    //instantiate array of semesters by size of the cursor + 1
	    	    examArr = new String[i];
	    	    //set up a count int to keep track of array positions
	    	    int count = 0;
	            //move the cursor to first position
	    	    cursor.moveToFirst();
	    	    
	            //while the cursor position isn't passed the last item in the cursor 
	    	    while(cursor.isAfterLast()==false) {
	    	    		//store the string in "Session" column into the array of semesters
	    	    		examArr[count] = cursor.getString(cursor.getColumnIndex("Name"));
	    	    		//increment count
	    	    		count++;
	    	    		//move cursor by 1
	    	    		cursor.moveToNext();
	    	    }
	    	    //add an "add semester option
	    	    examArr[count] = new String("+Add Exam");
	    	    //close the cursor
	    	    cursor.close();
	    	    //close the database
	       	   	db.close();
	       	   	//set a listadapter, use the semester_menu_view view and the semesterArr array
	       	   	setListAdapter(new ArrayAdapter<String>(this, R.layout.assignment_listview,examArr));//needs an array
	       	   	//get the list view from the view
	       	   	ListView listView = getListView();
	       	   	//set the listview's textfilter to enabled
	       	   	listView.setTextFilterEnabled(true);
	       	   	//add an onclicklistener
	       	   	listView.setOnItemClickListener(new OnItemClickListener() {
	    		
	       	   		public void onItemClick(AdapterView<?> parent, View view,
	       	   			int position, long id) {
	       	   				//this string holds the list item clicked
	       	   				String rightMeow = examArr[position];

	       	   				// When clicked, show a toast with the TextView text
	       	   				//Toast.makeText(getApplicationContext(),
	       	   				//((TextView) view).getText(), Toast.LENGTH_SHORT).show();
	       	   				//if item clicked equals add semester
	       	   				
	       	   				if (rightMeow.equals("+Add Exam")){  
	       	   					Intent intent = new Intent(getApplicationContext(), CreateExamActivity.class);
	       	   					//then go to the CreateSemesterActivity
	       	   					startActivity(intent);
	       	   					finish();
	       	   				} else {
	       	   					Intent intent = new Intent(getApplicationContext(), ExamActivity.class);
	       	   					intent.putExtra("key", rightMeow);
	       	   					startActivity(intent);
	       	   					finish();
	       	   				}
	       	   		}
	       	   	}); //close listener
	       	  
	        }catch(SQLiteException e){

	            // Create the alert box
	           AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
				alertbox.setCancelable(false);

	           // Set the message to display
	           alertbox.setMessage("No Exams Exist for this Course!");

	           // Add a neutral button to the alert box and assign a click listener
	           alertbox.setNeutralButton("Add Exam", new DialogInterface.OnClickListener() {

	               // Click listener on the neutral button of alert box
	               public void onClick(DialogInterface arg0, int arg1) {

	                   // The neutral button was clicked
	            	   Intent intent = new Intent(getApplicationContext(), CreateExamActivity.class);
	                   startActivity(intent);
	                   finish();
	               }
	           });
				alertbox.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							// Click listener on the neutral button of alert box
							public void onClick(DialogInterface arg0, int arg1) {
								finish();
							}
						});

	            // show the alert box
	           alertbox.show();
	       }
	        
	    }//end of onCreate()
}
