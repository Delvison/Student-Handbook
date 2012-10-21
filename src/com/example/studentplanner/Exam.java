package com.example.studentplanner;

import java.util.GregorianCalendar;

public class Exam {
  String name;
  GregorianCalendar Date;
  int pointsReceived;
  int maxPoints;
  int grade;
  boolean isComplete; // if checked, it wont show in masterview
  
  public Exam(String name, GregorianCalendar dueDate, int maxPoints) {
	  //constructor
  }
}

/*
 * maybe have just name and date in the constructor
 * addGrade() method that would take in pointsRecieved and maxPoints and calculate grade
      ---> grade = (pointsRecieved / maxPoints)
 * editExam() method in case things change
*/

