package com.example.studentplanner;

import java.util.Calendar;

public class Note {
	String title;
	Calendar date;
	String text;
	int charLimit; //character limit
	
	public Note(String title, String txt) {
		// charLimit somewhere here
		this.title = title;
		this.text = txt;
	}
	
	public void editNote(String title, String txt) {
		this.title = title;
		this.text = txt;
	}

}
