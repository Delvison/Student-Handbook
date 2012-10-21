package com.example.studentplanner;

import java.util.ArrayList;

public class Course extends MyEvent {
	typeOfEvent = "Course";
	ArrayList<Assignment> assignments;
	ArrayList<Exam> exams;
	int avg;
	
	public Course(String name, String descript, String loc){ 
		hasreoccurence = true; 
		nameOfEvent = name; 
		descriptionOfEvent = descript;
		location = loc; 
		//eventStartTime a Calendar object?
		//eventEndTime a Calendar object? (Maybe this should just be duration based?)
		//datesOfOccurence a Calendar object? (Calendar objects aren't repeating, DAY_OF_WEEK maybe?)
	}

  public void addAssignment(String name, Calendar dueDate, String description, int maxPoints) {
	Assignment a = new Assignment(name, dueDate, description, 0, maxPoints, 0, false); //Check with Assignment Class
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
  
  public void addExam(String name, Calendar dueDate, int maxPoints) {
	Exam e = new Exam(name, dueDate, 0, maxPoints, 0, false); //Check with Exam Class
	exams.add(e);
  }

  public void deleteExam(String examName) {
	 //--search for exam obj.
	   Exam e = this.searchForExam(examName);
	 //--remove exam obj.
       exams.remove(e)
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
