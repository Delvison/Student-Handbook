package com.example.studentplanner;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    String[] semesterArr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            //open database
    	    SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
    	    //query. receive a cursor
    	    Cursor c= db.rawQuery("SELECT Session FROM Semesters", null);
    	    //count how many items in cursor. add 1 to leave space for add semester option
    	    int i = c.getCount() + 1;
    	    //instantiate array of semesters by size of the cursor + 1
    	    semesterArr = new String[i];
    	    //set up a count int to keep track of array positions
    	    int count = 0;
            //move the cursor to first position
    	    c.moveToFirst();
    	    
            //while the cursor position isn't passed the last item in the cursor 
    	    while(c.isAfterLast()==false) {
    	    		//store the string in "Session" column into the array of semesters
    	    		semesterArr[count] = c.getString(c.getColumnIndex("Session"));
    	    		//increment count
    	    		count++;
    	    		//move cursor by 1
    	    		c.moveToNext();
    	    }
    	    //add an "add semester option
    	    semesterArr[count] = new String("+Add Semester");
    	    //close the cursor
    	    c.close();
    	    //close the database
       	   	db.close();
       	   	//set a listadapter, use the semester_menu_view view and the semesterArr array
       	   	setListAdapter(new ArrayAdapter<String>(this, R.layout.semester_listview,semesterArr));//needs an array
       	   	//get the list view from the view
       	   	ListView listView = getListView();
       	   	//set the listview's textfilter to enabled
       	   	listView.setTextFilterEnabled(true);
       	   	//add an onclicklistener
       	   	listView.setOnItemClickListener(new OnItemClickListener() {
    		
       	   		public void onItemClick(AdapterView<?> parent, View view,
       	   			int position, long id) {
       	   				//this string holds the list item clicked
       	   				String rightMeow = semesterArr[position];

       	   				// When clicked, show a toast with the TextView text
       	   				//Toast.makeText(getApplicationContext(),
       	   				//((TextView) view).getText(), Toast.LENGTH_SHORT).show();
       	   				//if item clicked equals add semester
       	   				
       	   				if (rightMeow.equals("+Add Semester")){  
       	   					Intent intent = new Intent(getApplicationContext(), CreateSemesterActivity.class);
       	   					//then go to the CreateSemesterActivity
       	   					startActivity(intent);
       	   				} else {
       	   					Intent intent = new Intent(getApplicationContext(), SemesterActivity.class);
       	   					intent.putExtra("key", rightMeow);
       	   					startActivity(intent);
       	   				}
       	   		}
       	   	}); //close listener
       	  
        }catch(SQLiteException e){
        	Intent intent = new Intent(getApplicationContext(), CreateSemesterActivity.class);
            startActivity(intent);
        }
    }//end of onCreate()
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
