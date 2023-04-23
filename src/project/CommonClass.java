package project;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.scene.control.TextField;

public class CommonClass {
	public static ArrayList<Course> courseList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
      
    private static final  File FILE = new File("res\\Registration.dat"); 
    
   
    
	public static final void loadBinaryData() {
        System.out.println("start loading");
  try (
      FileInputStream fos = new FileInputStream(FILE);
      ObjectInputStream oos = new ObjectInputStream(fos);
      ){
	  CommonClass.courseList = (ArrayList<Course> ) oos	.readObject();
	  CommonClass.studentList = (ArrayList<Student> ) oos.readObject();
	  for ( int i = 0 ; i<  CommonClass.courseList.size(); i++)
		  System.out.println(CommonClass.courseList.get(i));
	  
	  System.out.print(CommonClass.studentList.size());
	  for ( int i = 0 ; i<  CommonClass.studentList.size(); i++)
		  System.out.println(CommonClass.studentList.get(i));
      
  }   
  
  
  catch (FileNotFoundException  ex) {
      System.out.println(ex.getMessage());
  }

  catch (IOException |  ClassNotFoundException ex) {
      System.out.println(ex.getMessage());
      }
  
  
	}
	
	public static void save() {
		  try (
				  FileOutputStream fos = new FileOutputStream(FILE);
			      ObjectOutputStream oos = new ObjectOutputStream(fos);
			      ){
				  oos.writeObject(CommonClass.courseList);
				  oos.writeObject(CommonClass.studentList) ;
				  for ( int i = 0 ; i<  CommonClass.courseList.size(); i++)
					  System.out.println(CommonClass.courseList.get(i));
				  
			  }   
			  
			  
			  catch (FileNotFoundException  ex) {
			      System.out.println(ex.getMessage());
			  }

			  catch (IOException ex) {
			      System.out.println(ex.getMessage());
			      
		  
		  
			  }
	}
    
	public static ArrayList<String> getCourseNames(){
		 
		 ArrayList<String> courseNames = new ArrayList<>();
		 	for(int i = 0; i<CommonClass.courseList.size();i++) {
		 		
		 		courseNames.add(CommonClass.courseList.get(i).getCourseID());
		 		
		 	}
		 	return courseNames;
	}
	
	public static ArrayList<Course> getCourses(){
		
	      FileInputStream fos = null;
		try {
			fos = new FileInputStream("res\\Registration.dat");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      ObjectInputStream oos = null;
		try {
			oos = new ObjectInputStream(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  ArrayList<Course> courses = null;
		try {
			courses = (ArrayList<Course> ) oos.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return courses;
}
	
	public static ArrayList<String> getStudentId(){

		 ArrayList<String> courseNames = new ArrayList<>();
		 	for(int i = 0; i<CommonClass.studentList.size();i++) {
		 		
		 		courseNames.add(CommonClass.studentList.get(i).getStudID());
		 		
		 	}
		 	
		 	return courseNames;
	}
	
	public static ArrayList<Student> getStudents(){
		
	      FileInputStream fos = null;
		try {
			fos = new FileInputStream("res\\Registration.dat");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      ObjectInputStream oos = null;
		try {
			oos = new ObjectInputStream(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  ArrayList<Student> courses = null;
		try {
			courses = (ArrayList<Student> ) oos.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return courses;
		
	}
    
	
}
