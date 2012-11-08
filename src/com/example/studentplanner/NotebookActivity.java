package com.example.studentplanner;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class NotebookActivity extends ListActivity {
	String courseName;
	String noteName;
	String[] stringArray;
	TextView cName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notebook_view);    
        //Get extras from the parent Intent
        Bundle extras = getIntent().getExtras();
        courseName = extras.getString("CourseName");
        initNotebook();
    }
    
    public void initNotebook(){
    	
    	cName = (TextView) findViewById(R.id.textView1);
    	cName.setText(courseName + " NoteBook");
    	//creates a new database. Needs to be connected to a single database and added as a table
    	SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
    	db.execSQL("CREATE TABLE IF NOT EXISTS Notes (CourseName VARCHAR, NoteName VARCHAR,Note VARCHAR)");
    	
    	Cursor cursor = db.rawQuery("select NoteName from Notes where CourseName = '"+courseName+"'  ", null);
    	
    	int i = cursor.getCount(); 
    	cursor.moveToFirst();
    	stringArray = new String[i];
    	int count = 0;
    	
    	
    	while(!cursor.isAfterLast()){
    		
    		String tempString = cursor.getString(cursor.getColumnIndex("NoteName"));
    		stringArray[count] = tempString;	
    		count++;
    		
    		cursor.moveToNext();
    	}
    	
    	cursor.close();
    	db.close();
    	
    	setListAdapter(new ArrayAdapter<String>(this, R.layout.semester_listview,stringArray));
    	ListView listView = getListView();
    	
    	listView.setOnItemClickListener(new OnItemClickListener() {
    		
   	   		public void onItemClick(AdapterView<?> parent, View view,
   	   			int position, long id) {
   	   				//this string holds the list item clicked
   	   				String note = stringArray[position];
   	   				Intent intent = new Intent(getApplicationContext(), NoteViewActivity.class);
   	   				intent.putExtra("NoteName", note);
   	   				startActivity(intent);
   	   				
   	   		}
   	   	}); //close listener
   	  
    	
    }
    
    public void onClickHandle(View v){
    	//Intent to NoteViewActivity which will display the note_view layout
    	 Intent intent = new Intent(getApplicationContext(), NoteViewActivity.class);
		 intent.putExtra("CourseName", courseName);
		 intent.putExtra("NoteName",noteName);
		 
		 startActivity(intent);
    }
    
    public void addNoteHandle(View v){
    	Intent intent = new Intent(getApplicationContext(), CreateNoteActivity.class);
    	intent.putExtra("CourseName", courseName);
    	startActivity(intent);
    }

}
