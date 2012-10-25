//Author: Delvison Castillo

package com.example.studentplanner;

import java.util.GregorianCalendar;

public class Assignment {
  String name;
  GregorianCalendar dueDate;
  String description;
  int pointsReceived;
  int maxPoints;
  int grade;
  boolean isComplete;
  
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
  //should we also have get methods?
}
