package com.example.studentplanner;

import java.util.ArrayList;

public class Progress {
	int average;
	boolean hasProgress;
	
	public int calculateAverage(Course c) {
		int accGrade = 0;
		int maxBound = 0;
		for(Assignment a : c.assignments) { //for each assignment in assignments
	        if(a.isComplete) {
	    	    accGrade = accGrade + a.pointsReceived;
	    	    maxBound = maxBound + a.maxPoints;
	      }
		}
	    for(Exam e : c.exams) {  //for each exam in exams
	    	if(e.isComplete) { //need to add isComplete to Exam Class
	    		accGrade = accGrade + e.pointsReceived;
	    		maxBound = maxBound + e.maxPoints;
	    	}
	    }
	    if(maxBound = 0) return c.avg = 100;
	    else{ 
	    	average = (accGrade/maxBound) * 100;
	    	return c.avg = average;
	    }
	}
}

