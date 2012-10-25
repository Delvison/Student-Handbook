package com.example.studentplanner;

import java.util.GregorianCalendar;

public class MyEventTest {

	public static void main(String[] args){
		GregorianCalendar start = new GregorianCalendar(2012, 8, 27);
		GregorianCalendar end = new GregorianCalendar(2012, 12, 14);
		
		Semester semester = new Semester("Fall 2012", start, end);
		
		Course cr1 = new Course("CSC 322i", "Systems Programmingw", "Snygg 106r", true);
		cr1.setSemester(semester);
		cr1.setDateOfEvent(8, 27, 2012, 7, 30);
		cr1.setEventEndTime(8, 00);
		GregorianCalendar dueDate1 = new GregorianCalendar(2012, 9, 25, 8, 00);
		cr1.addAssignment("Text based game", dueDate1, "Create a text based game in C", 20);
		cr1.addNotebook();
		cr1.notebook.addNote("8/29/2012");
		cr1.notebook.getNote("8/29/2012").setText("This is the first day of notes");
		
		
		Course cr2 = new Course("CSC 365", "Data Structures", "Snygg 106", true);
		cr2.setSemester(semester);
		cr1.setDateOfEvent(8, 27, 2012, 9, 35);
		GregorianCalendar dueDate2 = new GregorianCalendar(2012, 9, 27, 9, 35);
		cr1.addAssignment("BTree Project", dueDate2, "Implement a Binary Search Tree", 20);
		GregorianCalendar dueDate3 = new GregorianCalendar(2012, 10, 8, 9, 35);
		cr1.addAssignment("Red Black Tree Project", dueDate3, "Implement a Red Black Tree", 20);
		
		
		
		Misc ms1 = new Misc("CSC 322 Meeting", "Project meeting", "Snygg main hall", false);
		ms1.setSemester(semester);
		ms1.setDateOfEvent(9, 3, 2012, 18, 00);
		
		//print original date before upcoming change
		
		cr1.setName("CSC 322");
		cr1.setDescription("Systems Programming");
		cr1.setLocation("Snygg 206");
		cr1.setEventStartTime(8, 00);
		cr1.setEventEndTime(8, 55);
		
		
		
		
		
		
		
		
	}
	
	
}
