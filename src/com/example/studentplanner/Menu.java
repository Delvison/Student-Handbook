package com.example.studentplanner;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {
  String options[] = {"Create an Event","View Events"}; //string array that holds menu options
  
  protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, options));

  }
  
  protected void onListItemClick(ListView l, View v, int position, long id) {
	  super.onListItemClick(l, v, position, id);
	  String rightMeow = options[position];
	  try{
		  Class chosenClass = Class.forName("com.example.studentplanner." + rightMeow );
	      Intent goTo = new Intent(Menu.this, chosenClass);
		  startActivity(goTo);
	  }catch (ClassNotFoundException e){
		  e.printStackTrace();
	  }
  }
}
