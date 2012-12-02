package com.example.studentplanner;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
	Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	bundle = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notebook_view);    
        //Get extras from the parent Intent
        Bundle extras = getIntent().getExtras();
        courseName = extras.getString("CourseName");
        initNotebook();
    }
    
    public void initNotebook(){
    	
    	cName = (TextView) findViewById(R.id.textView1);
    	cName.setText(courseName);
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
    	
    	setListAdapter(new ArrayAdapter<String>(this, R.layout.custom_listview,stringArray));
    	ListView listView = getListView();
    	
    	
    	listView.setOnItemClickListener(new OnItemClickListener() {
    		
   	   		public void onItemClick(AdapterView<?> parent, View view,
   	   			int position, long id) {
   	   				//this string holds the list item clicked
   	   				String note = stringArray[position];
   	   				Intent intent = new Intent(getApplicationContext(), NoteViewActivity.class);
   	   				intent.putExtra("NoteName", note);
					intent.putExtra("CourseName",courseName);
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
    
    public void deleteNoteHandle(View v){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("What note do you wish to remove?");
		builder.setItems(stringArray, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				String temp = stringArray[item];
				SQLiteDatabase db = openOrCreateDatabase("PlannerDB", MODE_PRIVATE, null);
		    	db.execSQL("CREATE TABLE IF NOT EXISTS Notes (CourseName VARCHAR, NoteName VARCHAR,Note VARCHAR)");
		    	
		    	db.execSQL("DELETE from Notes where NoteName = '"+temp+"'  ");
		    	db.close();
		    	finish();
		    	
		    	onCreate(bundle);
		    	
		    	
			}
		});
		
		AlertDialog alert = builder.create();
		
		alert.setButton("Cancel", new DialogInterface.OnClickListener() {

			// Click listener on the neutral button of alert box
			public void onClick(DialogInterface arg0, int arg1) {
				// do nothing
				
			}
		});
		
		alert.show();
    	
    }

}
