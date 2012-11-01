package com.example.studentplanner;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    //ArrayList<String> semesters = new ArrayList<String>();
    String[] semesterArr;
    static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	SQLiteDatabase db = openOrCreateDatabase("SemesterDB", MODE_PRIVATE, null);
        Cursor c= db.rawQuery("SELECT Session FROM Semesters", null);
        int i = c.getCount();
        semesterArr = new String[i];
    	int count = 0;
    	
        if (i != 0 ){

           c.moveToFirst();
    	   while(c.isAfterLast()==false) {
             // semesters.add(c.getString(c.getColumnIndex("Session"))); // do the same for other columns
              semesterArr[count] = c.getString(c.getColumnIndex("Session"));
              count++;
              c.moveToNext();
           }
    	   c.close();
       	   db.close();
       	   
       	setListAdapter(new ArrayAdapter<String>(this, R.layout.semester_menu_view,semesterArr));//needs an array

    	ListView listView = getListView();
    	listView.setTextFilterEnabled(true);

    	listView.setOnItemClickListener(new OnItemClickListener() {
    		public void onItemClick(AdapterView<?> parent, View view,
    				int position, long id) {
    		    // When clicked, show a toast with the TextView text
    		    Toast.makeText(getApplicationContext(),
    			((TextView) view).getText(), Toast.LENGTH_SHORT).show();
    		}
    	});
       	   
        } else {
            Intent intent = new Intent(getApplicationContext(), CreateSemesterActivity.class);
            startActivity(intent);
            db.close();
        }
 
    }
    

    public void handleClick(View v){
        Intent intent = new Intent(getApplicationContext(), CreateSemesterActivity.class);
        startActivity(intent);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
