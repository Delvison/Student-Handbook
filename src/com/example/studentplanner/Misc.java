package com.example.studentplanner;

import java.util.GregorianCalendar;

public class Misc extends MyEvent {

	public Misc(String name, String descript, String loc, boolean b) { 
		typeOfEvent = "Misc";
		hasReoccurence = b; 
		nameOfEvent = name; 
		descriptionOfEvent = descript;
		location = loc; 
		//eventStartTime a Calendar object?
		//eventEndTime a Calendar object? (Maybe this should just be duration based?)
		//datesOfOccurence a Calendar object? (Calendar objects aren't repeating, DAY_OF_WEEK maybe?)
	}
	   public void addAssignment(String name, GregorianCalendar dueDate, String description, int maxPoints) {

       }
public Exam searchForExam(String eName) {
	return null;
}
        public void deleteExam(String examName){}
        public void addExam(String name, GregorianCalendar dueDate, int maxPoints){}
        public  Assignment searchForAssignment(String aName){
        return null;
        }
        public void deleteAssignment(String assignmentName){}
}
