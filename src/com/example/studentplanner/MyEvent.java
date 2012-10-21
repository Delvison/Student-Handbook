package com.example.studentplanner;

import java.util.GregorianCalendar;
import java.util.ArrayList;

public class MyEvent {
	Semester currentSemester;
	String nameOfEvent;
	String descriptionOfEvent;
    String location;
    static String typeOfEvent; //for distinguishing-purposes
    GregorianCalendar dateOfEvent;
	GregorianCalendar eventEndTime;
	boolean hasReoccurence;
	Notebook notebook;
	ArrayList<GregorianCalendar> datesOfOccurence;
	
	public void setName(String name){
		this.nameOfEvent = name;
	}
	
	public void setDescription(String description){
		this.descriptionOfEvent = description;		
	}
	
	public void setLocation(String loc){
		this.location = loc;
	}

	public void setDateOfEvent(int month, int day, int year){
		if(dateOfEvent != null){ 
			dateOfEvent.set(year,month,day);
		}
	}
	
	public void setDateOfEvent(int month, int day, int year, int startHour, int startMinute){
		if(dateOfEvent != null){
			dateOfEvent.set(year, month, day, startHour, startMinute);
		}
	}
	
	//in case we want to change the time
	public void setEventStartTime(int hour, int minute){
		dateOfEvent.set(GregorianCalendar.HOUR, hour);
		dateOfEvent.set(GregorianCalendar.MINUTE, minute);
	}
	
	//in case we want to change the time
	public void setEventEndTime(int hour, int minute){
		if(eventEndTime != null){
			eventEndTime.set(GregorianCalendar.HOUR, hour);
			eventEndTime.set(GregorianCalendar.MINUTE, minute);
		}
	}
	
	public void addNotebook() {
		//create notebook
		notebook = new Notebook();
	}
	
	public void dateAutomater(String occurence) {
		//program dates of occurrence into 
		//GregorianCalendar?
		
		if(hasReoccurence){
			GregorianCalendar start = dateOfEvent;
			GregorianCalendar end = eventEndTime;
		
			if(occurence == "Weekly"){
				//add GregorianCalendar Event to dateOfOccurence weekly until end of semester
				while(start.compareTo(end)<0){
					start.roll(GregorianCalendar.DATE, 7);
					datesOfOccurence.add(start);
				}
				
			}else if(occurence == "Monthly"){
				//add GregorianCalendar Event to dateofOccurence monthly until end of semester
				while(start.compareTo(end)<0){
					start.roll(GregorianCalendar.MONTH, true);
					datesOfOccurence.add(start);
				}
			}else if(occurence == "MWF"){
				//add GregorianCalendar Event to dateofOccurence Monday Wednesday Friday until end of semester
			}else if(occurence == "TR"){
				//add GregorianCalendar Event to dateofOccurence Tuesday Thursday until end of semester
			}else if(occurence =="MW"){
				//add GregorianCalendar Event to dateofOccurence Monday Wednesday until end of semester
			}
			
			//event of type "Course" might have labs that account for extra class slots
		}
	}
}
