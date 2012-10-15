package com.example.studentplanner;

import java.util.Calendar;
import java.util.ArrayList;

public class Semester {
  Calendar startOfSemester;
  Calendar endOfSemester;
  ArrayList<MyEvent> events;
  int daysTowardsEnd; 
  
  Semester(Calendar start, Calendar end) {
	  startOfSemester = start;
	  endOfSemester = end;
  }
  
   public void addEvent() {
	  //create event
	   //add onto arraylist
   }
  
   public void deleteEvent() {
	 //remove from arraylist
	//destroy event
   }
   
   public void calculateDaysToEnd() {
	   //calculate days until end
	   //assign it to the int var.
   }
}
