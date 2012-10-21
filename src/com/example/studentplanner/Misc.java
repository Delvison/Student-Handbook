package com.example.studentplanner;

public class Misc extends MyEvent {

	public Misc(String name, String descript, String loc, boolean b) { 
		typeOfEvent = "Misc";
		hasReoccurence = b; 
		nameOfEvent = name; 
		descriptionOfEvent = descript;
		location = loc; 
		//eventStartTime a Calendar object?
		//eventEndTime a Calendar object? (Maybe this should just be duration based?)
		//datesOfOccurence a Calendar object? (Calendar objects aren't repeating, DAY_OF_WEEK maybe?)
	}
}
