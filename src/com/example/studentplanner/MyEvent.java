package com.example.studentplanner;



import java.util.GregorianCalendar;
import java.util.ArrayList;

public abstract class MyEvent {
	Semester currentSemester;
	String nameOfEvent;
	String descriptionOfEvent;
    String location;
    static String typeOfEvent; //for distinguishing-purposes
    GregorianCalendar dateOfEvent;
	GregorianCalendar eventEndTime;
	boolean hasReoccurence;
	Notebook notebook;
	ArrayList<GregorianCalendar> eventDatesOfOccurence;
	
	public void setName(String name){
		this.nameOfEvent = name;
	}
	
    public void setSemester(Semester currentSemester){
    	this.currentSemester = currentSemester;
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
		dateOfEvent.set(GregorianCalendar.HOUR_OF_DAY, hour);
		dateOfEvent.set(GregorianCalendar.MINUTE, minute);
	}
	
	//in case we want to change the time
	public void setEventEndTime(int hour, int minute){
		if(eventEndTime != null){
			eventEndTime.set(GregorianCalendar.HOUR_OF_DAY, hour);
			eventEndTime.set(GregorianCalendar.MINUTE, minute);
		}
	}
	
	public void addNotebook() {
		//create notebook
		notebook = new Notebook(this);
	}
	
	public void addNotebook(String notebookTitle){
		notebook = new Notebook(notebookTitle);
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
					if(start.compareTo(end)<0)
					currentSemester.occurences.add(start);
					eventDatesOfOccurence.add(start);
				}
				
			}else if(occurence == "Monthly"){
				//add GregorianCalendar Event to dateofOccurence monthly until end of semester
				while(start.compareTo(end)<0){
					start.roll(GregorianCalendar.MONTH, true);
					if(start.compareTo(end)<0)
					currentSemester.occurences.add(start);
					eventDatesOfOccurence.add(start);
				}
			}else if(occurence == "MWF"){
				//add GregorianCalendar Event to dateofOccurence Monday Wednesday Friday until end of semester
				while(start.compareTo(end)<0){
					if(start.get(GregorianCalendar.DAY_OF_WEEK) == 2){
						start.roll(GregorianCalendar.DAY_OF_WEEK, 2);
						if(start.compareTo(end)<0)
						currentSemester.occurences.add(start);
						eventDatesOfOccurence.add(start);
					}
					if(start.get(GregorianCalendar.DAY_OF_WEEK) == 4){
						start.roll(GregorianCalendar.DAY_OF_WEEK, 2);
						if(start.compareTo(end)<0)
						currentSemester.occurences.add(start);
						eventDatesOfOccurence.add(start);
					}
					if(start.get(GregorianCalendar.DAY_OF_WEEK) == 6){
						start.roll(GregorianCalendar.DAY_OF_WEEK, 3);
						if(start.compareTo(end)<0)
						currentSemester.occurences.add(start);
						eventDatesOfOccurence.add(start);
					}
					
				}
				
			}else if(occurence == "TR"){
				//add GregorianCalendar Event to dateofOccurence Tuesday Thursday until end of semester
				while(start.compareTo(end)<0){
					if(start.get(GregorianCalendar.DAY_OF_WEEK) == 3){
						start.roll(GregorianCalendar.DAY_OF_WEEK, 2);
						if(start.compareTo(end)<0)
						currentSemester.occurences.add(start);
						eventDatesOfOccurence.add(start);
					}
					if(start.get(GregorianCalendar.DAY_OF_WEEK) == 5){
						start.roll(GregorianCalendar.DAY_OF_WEEK, 5);
						if(start.compareTo(end)<0)
						currentSemester.occurences.add(start);
						eventDatesOfOccurence.add(start);
					}
				}
			}else if(occurence =="MW"){
				//add GregorianCalendar Event to dateofOccurence Monday Wednesday until end of semester
				while(start.compareTo(end)<0){
					if(start.get(GregorianCalendar.DAY_OF_WEEK) == 2){
						start.roll(GregorianCalendar.DAY_OF_WEEK, 2);
						if(start.compareTo(end)<0)
						currentSemester.occurences.add(start);
						eventDatesOfOccurence.add(start);
					}
					if(start.get(GregorianCalendar.DAY_OF_WEEK) == 4){
						start.roll(GregorianCalendar.DAY_OF_WEEK, 5);
						if(start.compareTo(end)<0)
						currentSemester.occurences.add(start);
						eventDatesOfOccurence.add(start);
					}
			     }
			}
			//event of type "Course" might have labs that account for extra class slots
		}
	}
	
	public void printEvent(){
		System.out.println(typeOfEvent + ": " + nameOfEvent + ", " + currentSemester);
		System.out.println("Description: " + descriptionOfEvent);
		System.out.println("Location: " + location);
		System.out.println("Start date: " + dateOfEvent.toString());
		System.out.println("End date: " + eventEndTime.toString());
		System.out.println("Dates of occurence: " );
	
	}
	 public abstract void addAssignment(String name, GregorianCalendar dueDate, String description, int maxPoints);
     public abstract Exam searchForExam(String eName);
     public abstract void deleteExam(String examName);
     public abstract void addExam(String name, GregorianCalendar dueDate, int maxPoints);
     public abstract Assignment searchForAssignment(String aName);
     public abstract void deleteAssignment(String assignmentName);
}
