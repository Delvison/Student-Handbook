package com.example.studentplanner;


import java.util.ArrayList;

public class Notebook {
  String title;
  ArrayList<Note> notes;
  MyEvent event;
  
  public Notebook(MyEvent e) {
	  notes = new ArrayList<Note>();
	  setEvent(e);
	  setTitle(event.nameOfEvent + " Notebook");
  }
  
  public Notebook(String title){
	  notes = new ArrayList<Note>();
	  setTitle(title);
  }
  
  public void setEvent(MyEvent event){
	  this.event = event;
  }
  
  //Create a Note for the Notebook when there are no parameters. 
  public void addNote(){
	  Note n = new Note();
	  n.notebook = this;
	  notes.add(n);
  }
  
  public void setTitle(String title){
	  this.title = title;
  }
  
  public Note getNote(String noteName){
	  Note note = null;
	  for(Note n: notes){
		  if(n.title == noteName){
			   note = n;
		  }
	  }
	  return note;
  }
  
  public void addNote(String title) {
	  //create note
	  //add note to arraylist
	  Note n = new Note(title);
	  n.notebook = this;
	  notes.add(n);
  }
  
  
  public void deleteNote(String title) {
	  //remove note from ArrayList
	  //destroy note
	  Note n = getNote(title);
	  notes.remove(n);
	  
  }
}
