package com.example.studentplanner;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Calendar;

public class Semester {
  String term; //fall, spring, winter or summer
  GregorianCalendar startOfSemester;
  GregorianCalendar endOfSemester;
  ArrayList<MyEvent> events;
  ArrayList<GregorianCalendar> occurences;
  long daysTowardsEnd; 
  
  public Semester(String session, GregorianCalendar start, GregorianCalendar end) {
    startOfSemester = start;
	endOfSemester = end;
	term = session;
  }
  
  /*the creation of an event MUST be in two parts!
   * first ask for the variable that are parameters in this constructor
   * then edit the other variables in the MyEvent class
   */
  public void addEvent(String type, GregorianCalendar date, String name, String desc, String loc, boolean b) {

	 if (events.size() == 0 || this.safeToAdd(date) ) {
		 //need an exception //create event. distinguish between course and misc.
	     if (type.equals("Course")) {
	         Course event = new Course(name, desc, loc, b);
                 events.add(event); //--add onto arraylist
	     }
	     else if (type.equals("Misc")){
	          Misc event = new Misc(name, desc, loc, b);
                  this.events.add(event); //--add onto arraylist
	     }
	 occurences.add(date);
	 }

   }
  
   public void deleteEvent(String eventName) {
   //--find event in arraylist; store in variable 
	   MyEvent event = this.searchForEvent(eventName);
   //--remove from arraylist
	   events.remove(event); 
   }
   
   public void calculateDaysToEnd() {
	   GregorianCalendar t=  new GregorianCalendar();
	   Date currentDate = t.getTime();
	   Date endDate = endOfSemester.getTime();
	   daysTowardsEnd = countDaysBetween(currentDate, endDate);
   }
   
   public boolean safeToAdd(GregorianCalendar dateOfEvent) {
		boolean availible = true; //if no event exist at the given time then set to true
		  for (GregorianCalendar c : occurences) //for each event in <events>
			 if (c.compareTo(dateOfEvent) == 0) {
				   availible = false;
			 }
		return availible;
	  }
   
   public MyEvent searchForEvent(String eventName) { 
	   MyEvent event = null;
	   for (MyEvent e : events) //for each event in <events>
	     if (e.nameOfEvent.equals(eventName))
	        event = e;
	   return event;
   }

   public static long countDaysBetween(Date start, Date end) {
	   int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24; //for conversion
	   if (end.before(start)) { // Date end should be after Date start
		   throw new IllegalArgumentException("ERROR!");
	   }
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
