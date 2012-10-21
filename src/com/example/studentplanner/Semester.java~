package com.example.studentplanner;

import java.util.Calendar;
import java.util.ArrayList;

public class Semester {
  String session; //fall, spring, winter or summer
  Calendar startOfSemester;
  Calendar endOfSemester;
  ArrayList<MyEvent> events;
  int daysTowardsEnd; 
  
  public Semester(Calendar start, Calendar end) {
	  startOfSemester = start;
	  endOfSemester = end;
  }
  
  public void addEvent(String name, Calendar date, Calendar time) {
	   /*MyEvent event; --the new event
       -->check if date and time is availible.
	   --if (this.safeToAdd())
	     --create event...(need to distinguish between course and misc though)
	      --if (type.equals("Course")
	          --event = new Course();
	      --if (type.equals("Misc")
	          --event = new Misc();
	   --add onto arraylist
	      --events.add(event);
	    */
   }
  
   public void deleteEvent(String eventName) {
	   /*
	  --find event in arraylist; store in variable 
	     --MyEvent event =this.searchForEvent(eventName);
	 --remove from arraylist
	   --events.remove(event);
	  */
   }
   
   public void calculateDaysToEnd() {
	   //calculate days until end
	   //assign it to the int var.
   }
   
   public boolean safeToAdd(Calendar date, Calendar time) {
		boolean availible = false;
        /*--if no event exist on that time/date
           (check through arraylist to make sure)
        --then set availible = true
		*/
		  for (MyEvent e : events) //for each event in <events>
			 if (e.dateOfEvent != date) {
			   if (e.eventStartTime != time) {
				   availible = true;
			   }
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
   
   public void calculateDaysLeft() {
	   //daysTowardsEnd = (endOfSemester) - (Today's date)
	   //** ^int obj                ^Calendar obj...so some sort of conversion needed maybe?
	   
	   //we should call this method everytime the app is opened up
   }
}