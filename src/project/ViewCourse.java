package project;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewCourse {
	static int idx = 0 ;
	public static BorderPane screen() {
		
		BorderPane pane = new BorderPane();						//create the pane for view cource layout
		
        Button back = new Button("Back");
        Button previous = new Button("< previous") ;			//create buttons will be used in the pane
        Button next = new Button("Next >");
        Button search = new Button("Search");
        
        
        ComboBox<String> combobox = new ComboBox<String>(); 	// this combobox for the status of a cource

        
        ListView<String> leftshow = new ListView<>(FXCollections.observableArrayList(CommonClass.getCourseNames()));
        ListView<String> rightshow =  new ListView<>(FXCollections.observableArrayList()) ;		// this two listview to display the cources and students 
        
        pane.setStyle("-fx-alignment: center;" + "-fx-font: 20 ARIAL" );		// this method is to change the stile of the layout 
        pane.setPadding(new Insets(20,20,20,20));

        Text hi = new Text("There are students ");
        Text ID = new Text("ID");
        Text Name = new Text("Name");
        Text Days = new Text("Days");							// these texts to to show what will be appear in textflields
        Text Location = new Text("Location");
        Text Time = new Text("Time");
        Text Statue = new Text("Statue");

        GridPane gridpane = new GridPane();
        gridpane.setHgap(10);								   // the gripane will be used to arrange nods
        gridpane.setVgap(10);
        
        TextField IdField = new TextField();
        TextField nameField = new TextField();
        TextField daysField = new TextField(); 				   // these textfields will show the information
        TextField locationField = new TextField();
        TextField timeField = new TextField();
                
        gridpane.add(ID, 0, 0);
        gridpane.add(Name, 0, 1);
        gridpane.add(Days, 0, 2);
        gridpane.add(Location, 0, 3);
        gridpane.add(Time, 0, 4);
        gridpane.add(Statue, 0, 5);							  // adding nods to the gridpane
        gridpane.add(IdField, 1, 0);						
        gridpane.add(nameField, 1, 1);
        gridpane.add(daysField, 1, 2);
        gridpane.add(locationField, 1, 3);
        gridpane.add(timeField, 1, 4);
        gridpane.add(combobox, 1, 5);

        gridpane.setAlignment(Pos.CENTER);


        HBox hbox = new HBox(8);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50);								// creating hbox and adding the buttons to it
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.getChildren().addAll(back,previous,next,search);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.getChildren().addAll(hi,rightshow);
        
        ArrayList<String> list = new ArrayList<>();
        
        leftshow.getSelectionModel().select(idx);
    	for(int i = 0 ; i < CommonClass.studentList.size(); i++) {				// after choose view cource the first cource will be sellected as defult
    		Student b =  CommonClass.studentList.get(i);
    		if(String.valueOf(b).contains(leftshow.getSelectionModel().getSelectedItem())) {
    			list.add(CommonClass.getStudentId().get(i));
    		}
    	}
    	rightshow.getItems().addAll(list);
    	IdField.setText(leftshow.getSelectionModel().getSelectedItem());  		// add students to be show in the other listview
    	
    	
    	for(int i = 0 ; i < CommonClass.courseList.size(); i++) {
    		if(String.valueOf(CommonClass.courseList.get(i)).contains(leftshow.getSelectionModel().getSelectedItem())) {
    			nameField.setText(CommonClass.courseList.get(i).getCourseName());
    			daysField.setText(CommonClass.courseList.get(i).getCourseDays());		// display the information of the fisrt cource
    			locationField.setText(CommonClass.courseList.get(i).getCourseLocation());
    			timeField.setText(CommonClass.courseList.get(i).getCourseTime());
    			if(CommonClass.courseList.get(i).getAvailableSeats()>0) {
    				combobox.setPromptText("Open");
    			}else {
    				combobox.setPromptText("Closed");
    			}
    		}
    	}
        
        back.setOnAction(new EventHandler<ActionEvent>() { 				// this button will return to the main wendow

            @Override
            public void handle(ActionEvent g) {
            	back.getScene().setRoot(Main.mainScreen());             
            }
        });
        
        leftshow.setOnMouseClicked(new EventHandler<MouseEvent>() {		// if a cource sellected from the listview all the information and 
            public void handle(MouseEvent event) {						// sdudents will display on the window
            	ArrayList<String> list = new ArrayList<>();
            	rightshow.getItems().clear();
            	for(int i = 0 ; i < CommonClass.studentList.size(); i++) {
            		Student b =  CommonClass.studentList.get(i);
            		if(String.valueOf(b).contains(leftshow.getSelectionModel().getSelectedItem())) {
            			list.add(CommonClass.getStudentId().get(i));
            		}
            	}
            	rightshow.getItems().addAll(list);
            	IdField.setText(leftshow.getSelectionModel().getSelectedItem());
            	
            	for(int i = 0 ; i < CommonClass.courseList.size(); i++) {
            		if(String.valueOf(CommonClass.courseList.get(i)).contains(leftshow.getSelectionModel().getSelectedItem())) {
            			nameField.setText(CommonClass.courseList.get(i).getCourseName());
            			daysField.setText(CommonClass.courseList.get(i).getCourseDays());
            			locationField.setText(CommonClass.courseList.get(i).getCourseLocation());
            			timeField.setText(CommonClass.courseList.get(i).getCourseTime());
            			if(CommonClass.courseList.get(i).getAvailableSeats()>0) {
            				combobox.setPromptText("Open");
            			}else {
            				combobox.setPromptText("Closed");
            			}
            		}
            	}
            };
            
        });
        
        previous.setOnAction(new EventHandler<ActionEvent>() {  // this button will move to the previous cource on the listview
        														// and display all the information
            @Override
            public void handle(ActionEvent g) {
            	ArrayList<String> list = new ArrayList<>();
            	rightshow.getItems().clear();
            	if(leftshow.getSelectionModel().getSelectedIndex()>=0) {
            		int ind = leftshow.getSelectionModel().getSelectedIndex();
            		ind--;
            		leftshow.getSelectionModel().select(ind);
            		for(int i = 0 ; i < CommonClass.studentList.size(); i++) {
                		Student b =  CommonClass.studentList.get(i);
                		if(String.valueOf(b).contains(leftshow.getSelectionModel().getSelectedItem())) {
                			list.add(CommonClass.getStudentId().get(i));
                		}
                	}
            		
            	}
            	rightshow.getItems().addAll(list);
            	IdField.setText(leftshow.getSelectionModel().getSelectedItem());
            	for(int i = 0 ; i < CommonClass.courseList.size(); i++) {
            		if(String.valueOf(CommonClass.courseList.get(i)).contains(IdField.getText().toUpperCase())) {
            			nameField.setText(CommonClass.courseList.get(i).getCourseName());
            			daysField.setText(CommonClass.courseList.get(i).getCourseDays());
            			locationField.setText(CommonClass.courseList.get(i).getCourseLocation());
            			timeField.setText(CommonClass.courseList.get(i).getCourseTime());
            			if(CommonClass.courseList.get(i).getAvailableSeats()>0) {
            				combobox.setPromptText("Open");
            			}else {
            				combobox.setPromptText("Closed");
            			}
            		}
            	}
            }
        });
        
        next.setOnAction(new EventHandler<ActionEvent>() {		// this button will move to the previous cource on the listview
        														// and display all the information
            @Override
            public void handle(ActionEvent g) {
            	ArrayList<String> list = new ArrayList<>();
            	rightshow.getItems().clear();
            	if(leftshow.getSelectionModel().getSelectedIndex()<29) {
            		int ind = leftshow.getSelectionModel().getSelectedIndex();
            		ind++;
            		leftshow.getSelectionModel().select(ind);
            		for(int i = 0 ; i < CommonClass.studentList.size(); i++) {
                		Student b =  CommonClass.studentList.get(i);
                		if(String.valueOf(b).contains(leftshow.getSelectionModel().getSelectedItem())) {
                			list.add(CommonClass.getStudentId().get(i));
                		}
                	}
            		
            	}
            	rightshow.getItems().addAll(list);
            	IdField.setText(leftshow.getSelectionModel().getSelectedItem());
            	for(int i = 0 ; i < CommonClass.courseList.size(); i++) {
            		if(String.valueOf(CommonClass.courseList.get(i)).contains(IdField.getText().toUpperCase())) {
            			nameField.setText(CommonClass.courseList.get(i).getCourseName());
            			daysField.setText(CommonClass.courseList.get(i).getCourseDays());
            			locationField.setText(CommonClass.courseList.get(i).getCourseLocation());
            			timeField.setText(CommonClass.courseList.get(i).getCourseTime());
            			if(CommonClass.courseList.get(i).getAvailableSeats()>0) {
            				combobox.setPromptText("Open");
            			}else {
            				combobox.setPromptText("Closed");
            			}
            		}            		
            	}
            }
        });
        
        
        search.setOnAction(new EventHandler<ActionEvent>() {	// this button will search about a cource on the listview
																// and display all the information
            public void handle(ActionEvent event) {
            	ArrayList<String> list = new ArrayList<>();
            	rightshow.getItems().clear();
            	
            	
            	for(int i = 0 ; i < CommonClass.studentList.size(); i++) {
            		Student b =  CommonClass.studentList.get(i);
            		if(String.valueOf(b).contains(IdField.getText().toUpperCase())) {
            			list.add(CommonClass.getStudentId().get(i));
            		}
            		
            	}
            	rightshow.getItems().addAll(list);
            	for(int i = 0 ; i < CommonClass.courseList.size(); i++) {
            		if(String.valueOf(CommonClass.courseList.get(i)).contains(IdField.getText().toUpperCase())) {
            			nameField.setText(CommonClass.courseList.get(i).getCourseName());
            			daysField.setText(CommonClass.courseList.get(i).getCourseDays());
            			locationField.setText(CommonClass.courseList.get(i).getCourseLocation());
            			timeField.setText(CommonClass.courseList.get(i).getCourseTime());   
            			if(CommonClass.courseList.get(i).getAvailableSeats()>0) {
            				combobox.setPromptText("Open");
            			}else {
            				combobox.setPromptText("Closed");
            			}
            		}
            	}
            };
        });
        
        

        
        pane.setBottom(hbox);
        pane.setLeft(leftshow);
        pane.setRight(vbox);
        pane.setCenter(gridpane);
        
        return pane;
	}
	
}
