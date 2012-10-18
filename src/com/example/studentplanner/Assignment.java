package com.example.studentplanner;

import java.util.Calendar;

public class Assignment {
  String name;
  Calendar dueDate;
  String description;
  int pointsRecieved;
  int maxPoints;
  int grade;
  boolean isComplete;
  
  public Assignment(){
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
