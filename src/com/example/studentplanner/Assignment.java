//Author: Delvison Castillo

package com.example.studentplanner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Assignment {
  String name;
  GregorianCalendar dueDate;
  String description;
  int pointsReceived;
  int maxPoints;
  int grade;
  boolean isComplete;
  long daysTilDue; 

  
  //have 2 constructors..one with or without maxpoints
  public Assignment(String name, GregorianCalendar dueDate, String description, int maxPoints){
	  this.name = name;
	  this.dueDate = dueDate;
	  this.description = description;
	  this.maxPoints = maxPoints;
  }
  
  public Assignment(String name, GregorianCalendar dueDate, String description){
	  this.name = name;
	  this.dueDate = dueDate;
	  this.description = description;
  }
  
  public void setName(String n) {
	  this.name = n;
  }
  
  public void setDueDate(GregorianCalendar c) {
	  this.dueDate = c;
  }
  
  public void setDescription(String d) {
	  this.description = d;
  }
  
  public void setPointsRecieved(int p) {
	  this.pointsReceived = p;
  }
  
  public void setMaxPoints(int m) {
	  this.maxPoints = m;
  }
   
  public void setIsComplete(boolean c) {
	  this.isComplete = c;
  }
  
  public void calculateGrade() {
	  grade = ((pointsReceived)/(maxPoints));
  }

  public String getName() {
	  return this.name;
  }
  
  public GregorianCalendar getDueDate() {
	  return this.dueDate;
  }
  
  public String getDescription() {
	  return this.description;
  }
  
  public int getPointsRecieved() {
	  return this.pointsReceived;
  }
  
  public int getMaxPoints() {
	  return this.maxPoints;
  }
   
  public boolean getIsComplete() {
	  return this.isComplete;
  }
  
  public void calculateDaysDue() {
	   GregorianCalendar t=  new GregorianCalendar();
	   Date currentDate = t.getTime();
	   Date due = dueDate.getTime();
	   daysTilDue = countDaysBetween(currentDate, due);
  }
  
  public static long countDaysBetween(Date start, Date end) {
	   int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24; //for conversion
	   if (end.before(start)) { // Date end should be after Date start
		   return 0;
	   } else {
	   //reset all hours mins and secs to zero on start date
	   Calendar startCal = GregorianCalendar.getInstance();
	   startCal.setTime(start);
	   startCal.set(Calendar.HOUR_OF_DAY, 0);
	   startCal.set(Calendar.MINUTE, 0);
	   startCal.set(Calendar.SECOND, 0);
	   long startTime = startCal.getTimeInMillis();

	   //reset all hours mins and secs to zero on end date
	   Calendar endCal = GregorianCalendar.getInstance();
	   endCal.setTime(end);
	   endCal.set(Calendar.HOUR_OF_DAY, 0);
	   endCal.set(Calendar.MINUTE, 0);
	   endCal.set(Calendar.SECOND, 0);
	   long endTime = endCal.getTimeInMillis();

	   return (endTime - startTime) / MILLISECONDS_IN_DAY;
	   }
  }
}
