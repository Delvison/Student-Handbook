
package com.example.studentplanner;

import java.util.GregorianCalendar;

public class Exam {
  String name;
  GregorianCalendar dueDate;
  int pointsReceived;
  int maxPoints;
  int grade;
  boolean isComplete; // if checked, it wont show in masterview
  
  public Exam(String name, GregorianCalendar dueDate, int maxPoints) {
	  this.name = name;
	  this.dueDate = dueDate;
	  this.maxPoints = maxPoints;
  }
  
  public Exam(String name, GregorianCalendar dueDate) {
	  this.name = name;
	  this.dueDate = dueDate;
  }
  
  public void setName(String n) {
	  this.name = n;
  }
  
  public void setdueDate(GregorianCalendar d) {
	  this.dueDate = d;
  }
  
  public void setPointsReceived(int p) {
	  this.pointsReceived = p;
  }
  
  public void setMaxPoints(int m) {
	  this.maxPoints = m;
  }
  
  public void setGrade(int g) {
	  this.grade = g;
  }
  
  public void setIsComplete(boolean c) {
	  this.isComplete = c;
  }
  
  public void calculateGrade() {
	  this.grade = ((pointsReceived)/(maxPoints));
  }
}
