package com.example.studentplanner;

import java.util.Calendar;

public class Assignment {
  String name;
  Calendar dueDate;
  String description;
  int pointsReceived;
  int maxPoints;
  int grade;
  boolean isComplete;
  //have 2 constructors..one with or without maxpoints
  public Assignment(String name, Calendar dueDate, String description, int maxPoints){
	  //constructor
  }
  
  public void edit() {
	  //ability to edit stuff here
	  
  }
  
}

/*
 * maybe have just name and date in the constructor
 * inputGrade() method that would take in pointsRecieved and maxPoints and calculate grade
      ---> grade = (pointsRecieved / maxPoints)
 * edit() method in case things change
*/
