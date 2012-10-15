package com.example.studentplanner;

import java.util.Calendar;
import java.util.ArrayList;

public abstract class MyEvent {
	Semester currentSemester;
	String nameOfEvent;
	String descriptionOfEvent;
    String location;
    String typeOfEvent; //for distinguishing-purposes
    Calendar dateOfEvent;
	Calendar eventStartTime;
	Calendar eventEndTime;
	boolean hasreocurrence;
	Notebook notebook;
	ArrayList<Calendar> datesOfOccurence;
	
	public void editEvent() {
		//give ability to edit variables here
	}
	
	public void addNotebook() {
		//create notebook
		notebook = new Notebook();
	}
	
	public void dateAutomater() {
		//program dates of occurrence into 
		//calendar?
	}

}
