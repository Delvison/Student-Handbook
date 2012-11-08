//package com.example.studentplanner;
//
//import android.app.ListActivity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//public class Menu extends ListActivity {
//  String options[] = {"Create an Event","View Events"}; //string array that holds menu options
//  
//  protected void onCreate(Bundle savedInstanceState) {
//     super.onCreate(savedInstanceState);
//     //setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, options));
//     String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//       "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//       "Linux", "OS/2" };
//     this.setListAdapter(new ArrayAdapter<String>(this, R.layout.semester_menu_view, R.id.label, values));
//     
//  }
//}
