package com.example.studentplanner;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ProgressActivity extends ListActivity {
	String cName;
	String[] stringArr;
	Spinner courses;
	SQLiteDatabase db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_view);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			cName = extras.getString("key");
		}
     	initProgress();
		//popAssignment();

		setListAdapter(new ArrayAdapter<String>(this,
				R.layout.semester_listview, stringArr));
		ListView listView = getListView();

	}

	public void initProgress() {
		Cursor cursor1 = db.rawQuery("select * from Courses where CourseName ='" + cName
						+ "'", null);
		cursor1.moveToFirst();
		String name = cursor1.getString(cursor1.getColumnIndex("CourseName"));
		String desc = cursor1.getString(cursor1.getColumnIndex("Description"));
		String loc = cursor1.getString(cursor1.getColumnIndex("Location"));
		int startHr = cursor1.getInt(cursor1.getColumnIndex("HourStart"));
		int startMin = cursor1.getInt(cursor1.getColumnIndex("MinuteStart"));
		int startYr = cursor1.getInt(cursor1.getColumnIndex("YearStart"));
		int startMth = cursor1.getInt(cursor1.getColumnIndex("MonthStart"));
		int startDay = cursor1.getInt(cursor1.getColumnIndex("DayStart"));
		String occur = cursor1.getString(cursor1.getColumnIndex("Occurences"));

		// String course = (String) courses.getSelectedItem().toString();
		//String date = Integer.startYr + "," + startMth + "," + startDay;
		ContentValues values = new ContentValues();
		Course c = new Course(name, desc, loc, false);
		Progress p = new Progress();
		//int grade = p.calculateAverage(c);

		//values.put("Grade", grade);

		//db.insert("Courses", null, values);

		/*
		 * Intent intent = new Intent(getApplicationContext(),
		 * CourseActivity.class); intent.putExtra("key", course);
		 * startActivity(intent);
		 */
	}

	/*
	 * public void populateCourses() { try{ //open database SQLiteDatabase db =
	 * openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null); //query. receive a
	 * cursor Cursor c= db.rawQuery("SELECT CourseName FROM Courses", null);
	 * //count how many items in cursor. add 1 to leave space for add semester
	 * option int i = c.getCount(); //instantiate array of semesters by size of
	 * the cursor + 1 coursesArr = new String[i]; //set up a count int to keep
	 * track of array positions int count = 0; //move the cursor to first
	 * position c.moveToFirst();
	 * 
	 * //while the cursor position isn't passed the last item in the cursor
	 * while(c.isAfterLast()==false) { //store the string in "Session" column
	 * into the array of semesters coursesArr[count] =
	 * c.getString(c.getColumnIndex("CourseName")); //increment count count++;
	 * //move cursor by 1 c.moveToNext(); } //close the cursor c.close();
	 * //close the database db.close(); }catch(SQLiteException e){ coursesArr =
	 * new String[1]; coursesArr[0] = "No Courses Exist!"; } }
	 */

	public void backHandler(View v) {
		finish();
	}

	private void popAssignment() {
		Cursor cursor2 = db.rawQuery(
				"select * from Assignments where CourseName ='" + cName
						+ "' and Complete = 1", null); // get those values

		Cursor cursor3 = db.rawQuery("select * from Exams where CourseName ='"
				+ cName + "' and Complete = 1", null); // get those values

		int i = cursor2.getCount() + cursor3.getCount();
		// instantiate array of semesters by size of the cursor + 1
		stringArr = new String[i];
		// set up a count int to keep track of array positions
		int count = 0;
		// move the cursor to first position
		cursor2.moveToFirst();
		// while the cursor position isn't passed the last item in the cursor
		while (cursor2.isAfterLast() == false) {
			String temp = cursor2.getString(cursor2.getColumnIndex("Name"));
			int temp1 = cursor2.getInt(cursor2.getColumnIndex("PointsRecieved"));
			int temp2 = cursor2.getInt(cursor2.getColumnIndex("MaxPoints"));
			int grade = temp1/temp2 * 100;
			// store the string in "Session" column into the array of semesters
			stringArr[count] =  temp +" "+Integer.toString(grade);
			// increment count
			count++;
			// move cursor by 1
			cursor2.moveToNext();
		}
				cursor3.moveToFirst();

		while (cursor3.isAfterLast() == false) {
			String temp = cursor3.getString(cursor3.getColumnIndex("Name"));
			int temp1 = cursor3.getInt(cursor3.getColumnIndex("PointsRecieved"));
			int temp2 = cursor3.getInt(cursor3.getColumnIndex("MaxPoints"));
			int grade = temp1/temp2 * 100;
			// store the string in "Session" column into the array of semesters
			stringArr[count] =  temp +" "+Integer.toString(grade);
			// increment count
			count++;
			// move cursor by 1
			cursor3.moveToNext();
		}
		
		// add an "add semester option
		// close the cursor
		cursor2.close();		
		cursor3.close();

		// close the database
		db.close();
	}

}
