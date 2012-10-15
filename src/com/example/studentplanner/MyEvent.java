package com.example.studentplanner;

import java.util.Calendar;
import java.util.ArrayList;

public abstract class MyEvent {
	String nameOfEvent;
	String descriptionOfEvent;
    String location;
    Calendar dateOfEvent;
	Calendar eventStartTime;
	Calendar eventEndTime;
	boolean hasreocurrence;
	Notebook notebook;
	ArrayList<Calendar> datesOfOccurence;
	
	public void editEvent() {
		
	}
	
	public void addNotebook() {
		//create notebook
		//assign it to notebook var.
	}
	
	public void dateAutomater() {
		//program dates of occurrence into 
		//calendar?
	}

}
