package com.example.studentplanner;

import java.util.Calendar;

public class Note {
	String title;
	Calendar date;
	String text;
	int charLimit = 300; //character limit
	Notebook notebook;
	
	public Note(){
		Calendar cal = Calendar.getInstance();
		setTitle(cal.toString());
		date = cal;
	}
	
	public Note(String title) {
		// charLimit somewhere here
		setTitle(title);
		date = Calendar.getInstance();
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setText(String txt){
		this.text = txt;
	}
	
	public Note getNote(){
		return this;
	}
	
	public void setNotebook(Notebook notebook){
		this.notebook = notebook;
	}
	
	public Notebook getNotebook(){
		return notebook;
	}
}
