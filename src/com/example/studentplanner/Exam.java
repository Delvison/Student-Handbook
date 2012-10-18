package com.example.studentplanner;

import java.util.Calendar;

public class Exam {
  String name;
  Calendar Date;
  int pointsRecieved;
  int maxPoints;
  int grade;
  
  public Exam() {
	  //constructor
  }
}

/*
 * maybe have just name and date in the constructor
 * addGrade() method that would take in pointsRecieved and maxPoints and calculate grade
      ---> grade = (pointsRecieved / maxPoints)
 * editExam() method in case things change
*/

