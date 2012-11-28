package com.example.studentplanner;

import java.util.GregorianCalendar;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MasterListActivity extends ListActivity {

	String[] evArr;
	GregorianCalendar[] dateArr;
	String sName; //actually need to get this bizz.
	int counter = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.master_listview);
		evArr = new String[countAllEvents()];
		populateEvents();
		bubbleSort();
		setListAdapter(new ArrayAdapter<String>(this, R.layout.semester_listview,evArr));//needs an array
   	   	//get the list view from the view
   	   	ListView listView = getListView();
   	   	//set the listview's textfilter to enabled
   	   	listView.setTextFilterEnabled(true);
   	   	//add an onclicklistener
   	   	listView.setOnItemClickListener(new OnItemClickListener() {

   	   		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
   	   			//this string holds the list item clicked
   	   			String rightMeow = evArr[position];

   	   			Intent intent = new Intent(getApplicationContext(), CourseActivity.class);
  				intent.putExtra("key", rightMeow);
  				startActivity(intent);
   	   		}
   	   	}); //close listener
	}
	
	public int countAllEvents() {
        int eventCounter = 0; //keeps count of all events
        String[] courseArr; //holds all courses pertaining to the semester
        //open database
        SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE,
                null);

        try{
        Cursor c = db.rawQuery("SELECT CourseName FROM Courses WHERE Semester ='"+sName+"'", null);
        int i = c.getCount();
        courseArr = new String[i];
        c.moveToFirst();
        int count = 0;
        // while the cursor position isn't passed the last item in the cursor
        while (c.isAfterLast() == false) {
            // store the string in "Session" column into the array of
            // semesters
            courseArr[count] = c.getString(c.getColumnIndex("CourseName"));
            // increment count
            count++;
            // move cursor by 1
            c.moveToNext();
        }
        // close the cursor
        c.close();
        //loop through each assignment. check date to see if its in the future.
        //if it is, add it to the main array that will be used in the listview
        }catch(SQLiteException e){
            courseArr = new String[0]; //avoids nullpointer
            e.printStackTrace();
        }
        //now we have all of our courses

        //next count up assignments belonging to these courses, this wont execute if 0 courses exist
        for (int a=0; a<courseArr.length; a++){
            String currentCourse = courseArr[a];
            Cursor b = db.rawQuery("SELECT Name FROM Assignments WHERE Course ='"+currentCourse+"'", null);
            eventCounter = eventCounter + b.getCount();
        }

        //next count up exams belonging to these courses, this wont execute if 0 courses exist
        for (int a=0; a<courseArr.length; a++){
            String currentCourse = courseArr[a];
            Cursor b = db.rawQuery("SELECT Name FROM Exams WHERE Course ='"+currentCourse+"'", null);
            eventCounter = eventCounter + b.getCount();
        }

        //next count up misc. events belonging to semester
        try {
            Cursor d = db.rawQuery("SELECT Name FROM Misc WHERE Semester ='"+sName+"'", null);
            eventCounter = eventCounter + d.getCount();
        }catch(SQLiteException e){
            e.printStackTrace();
        }

        return eventCounter;

    }
	
	public void populateEvents() {
        String[] courseArr; //holds all courses pertaining to the semester
        //open database
        SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);

        try{
	        Cursor c = db.rawQuery("SELECT CourseName FROM Courses WHERE Semester ='"+sName+"'", null);
	        int i = c.getCount();
	        courseArr = new String[i];
	        c.moveToFirst();
	        int count = 0;
	        // while the cursor position isn't passed the last item in the cursor
	        while (c.isAfterLast() == false) {
	            // store the string in "Session" column into the array of
	            // semesters
	            courseArr[count] = c.getString(c.getColumnIndex("CourseName"));
	            // increment count
	            count++;
	            // move cursor by 1
	            c.moveToNext();
	        }
	        // close the cursor
	        c.close();
	        //loop through each assignment. check date to see if its in the future.
	        //if it is, add it to the main array that will be used in the listview
        }catch(SQLiteException e){
            courseArr = new String[0]; //avoids nullpointer
            e.printStackTrace();
        }
        //now we have all of our courses

        //next count up assignments belonging to these courses, this wont execute if 0 courses exist
        for (int a=0; a<courseArr.length; a++){
            String currentCourse = courseArr[a];
			Cursor assignCursor = db.rawQuery("select * from Assignments where Course ='" + currentCourse + "' and Complete = O", null);
			assignCursor.moveToFirst();
			while (assignCursor.isAfterLast() == false) {
				evArr[counter] = assignCursor.getString(assignCursor.getColumnIndex("Name"));
				dateArr[counter] = GregorianCalendar(assignCursor.getInt(assignCursor.getColumnIndex("DueYear")), 
						assignCursor.getInt(assignCursor.getColumnIndex("DueMonth")),
						assignCursor.getInt(assignCursor.getColumnIndex("DueDay")));
				counter++;
				assignCursor.moveToNext();
			}
        }

        //next count up exams belonging to these courses, this wont execute if 0 courses exist
        for (int a=0; a<courseArr.length; a++){
            String currentCourse = courseArr[a];
            Cursor examCursor = db.rawQuery("select * from Exams where Course ='" + currentCourse + "' and Complete = O", null);
			examCursor.moveToFirst();
			while (examCursor.isAfterLast() == false) {
				evArr[counter] = examCursor.getString(examCursor.getColumnIndex("Name"));
				dateArr[counter] = GregorianCalendar(examCursor.getInt(examCursor.getColumnIndex("DueYear")), 
						examCursor.getInt(examCursor.getColumnIndex("DueMonth")),
						examCursor.getInt(examCursor.getColumnIndex("DueDay")));
				counter++;
				examCursor.moveToNext();
			}
        }

        //next count up misc. events belonging to semester
        try {
        	Cursor miscCursor = db.rawQuery("select * from Miscs where Semester ='" + sName +"'", null);
			miscCursor.moveToFirst();
			while (miscCursor.isAfterLast() == false) {
				evArr[counter] = miscCursor.getString(miscCursor.getColumnIndex("MiscName"));
				dateArr[counter] = GregorianCalendar(miscCursor.getInt(miscCursor.getColumnIndex("YearStart")), 
						miscCursor.getInt(miscCursor.getColumnIndex("MonthStart")),
						miscCursor.getInt(miscCursor.getColumnIndex("DayStart")));
				counter++;
				miscCursor.moveToNext();
				//but this puts in all of the miscs in, including past ones, right?
			}
        }catch(SQLiteException e){
            e.printStackTrace();
        }
	}
	
	public void bubbleSort() {
		for(int i = 0; i < dateArr.length; i++){
			for(int j = 1; j < (dateArr.length-i); j++){
				if(dateArr[j-1].after(dateArr[j])){
					GregorianCalendar t = dateArr[j-1];
					String temp = evArr[j-1];
				  	dateArr[j-1]=dateArr[j];
				  	evArr[j-1]=evArr[j];
				  	dateArr[j]=t;
				  	evArr[j]=temp;
				}
			}	
		}
	}
	
}
