package com.example.studentplanner;

import java.util.ArrayList;

public class Course extends MyEvent {
	String type = "Course";
	ArrayList<Assignment> assignments;
	ArrayList<Exam> exams;

  public void addAssignment() {
	Assignment a = new Assignment();
	assignments.add(a);
  }

  public void deleteAssignment(String assignmentName) {
	 //--search for assignment obj.
	   Assignment a = this.searchForAssignment(assignmentName);
	 //--remove assignment obj.
       assignments.remove(a);
  }
  
  public Assignment searchForAssignment(String aName) {
	   Assignment assign = null;
	   for (Assignment a : assignments) { //for each assignment in assignments
	     if (a.name.equals(aName)) {
	        assign = a;
	     }
	   }
	   return assign;   
  }
  
  public void addExam() {
	//Exam e = new Exam();
	//exams.add(e);
  }

  public void deleteExam(/*String examName*/) {
	/*
	 --search for exam obj.
	   --Exam e = this.searchForExam(examName);
	 --remove exam obj.
       --exams.remove(a)
	 */
  }
  
  public Exam searchForExam(String eName) {
	   Exam exam = null;
	   for (Exam e : exams) { //for each exam in exams
	     if (e.name.equals(eName)) {
	        exam = e;
	     }
	   }
	   return exam;
  }

}
