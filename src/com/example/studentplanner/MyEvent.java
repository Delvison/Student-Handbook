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
	//Calendar eventStartTime; remove date eventStartTime, do not need to create new object for the start time
	Calendar eventEndTime;
	boolean hasReocurrence;
	Notebook notebook;
	ArrayList<Calendar> datesOfOccurence;
	
	public void editEvent() {
		//give ability to edit variables here
		
	}
	
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
	
	public void setEventStartTime(int hour, int minute){
		dateOfEvent.set(Calendar.HOUR, hour);
		dateOfEvent.set(Calendar.MINUTE, minute);
	}
	
	public void setEventEndTime(int hour, int minute){
		if(eventEndTime != null){
			eventEndTime.set(Calendar.HOUR, hour);
			eventEndTime.set(Calendar.MINUTE, minute);
		}
	}
	
	public void addNotebook() {
		//create notebook
		notebook = new Notebook();
	}
	
	public void dateAutomater(String occurence) {
		//program dates of occurrence into 
		//calendar?
		
		if(hasReocurrence){
			Calendar start = dateOfEvent;
			Calendar end = eventEndTime;
		
			if(occurence == "Weekly"){
				//add Calendar Event to dateOfOccurence weekly until end of semester
				while(start.compareTo(end)<0){
					start.roll(Calendar.DATE, 7);
					datesOfOccurence.add(start);
				}
				
			}else if(occurence == "Monthly"){
				//add Calendar Event to dateofOccurence monthly until end of semester
				while(start.compareTo(end)<0){
					start.roll(Calendar.MONTH, true);
					datesOfOccurence.add(start);
				}
				
			}else if(occurence == "MWF"){
				//add Calendar Event to dateofOccurence Monday Wednesday Friday until end of semester
			}else if(occurence == "TR"){
				//add Calendar Event to dateofOccurence Tuesday Thursday until end of semester
			}else if(occurence =="MW"){
				//add Calendar Event to dateofOccurence Monday Wednesday until end of semester
				
			}
			
			//event of type "Course" might have labs that account for extra class slots
		}
		
	}

	public abstract void setTypeOfEvent(String type);

}
