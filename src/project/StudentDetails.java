package project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StudentDetails {
	
	static int currentStudent = 0;
	
	public static BorderPane screen() {
		currentStudent = 0;
		BorderPane pane = new BorderPane();
        HBox box = new HBox(20);
        box.setAlignment(Pos.CENTER);
        
        Button back = new Button("Back");
        Button previous = new Button("<Previous");
        Button next = new Button("Next>");		// creating buttons
        Button register = new Button("Register");
        Button drop = new Button("Drop");
        Button search = new Button("Search");
        
        box.getChildren().addAll(back,previous,next,register,drop,search);
        
        VBox vbox = new VBox(50);
        vbox.setAlignment(Pos.CENTER_LEFT);
        
        Text idtext = new Text("Student ID");
        Text registeredtext = new Text("Registered Courses");
        Text notregisteredtext = new Text("Not Registered Courses");
        
        vbox.getChildren().addAll(idtext,registeredtext,notregisteredtext);
        
        VBox vbox1 = new VBox(10);
        vbox1.setAlignment(Pos.CENTER_LEFT); 
        
        pane.setPadding(new Insets(30, 200, 30, 200));
        pane.setBottom(box);
        pane.setLeft(vbox);
        
        
        TextField textfield = new TextField();
        
        ListView<Object> listview = new ListView<>();
        
        textfield.setMinWidth(350);
        
        listview.setPrefSize(10, 200);
        
        ComboBox<Object> combobox = new ComboBox<Object>();
        
        combobox.setPrefWidth(350);
        
        vbox1.getChildren().addAll(textfield, listview, combobox);
  
        pane.setRight(vbox1);
        pane.setStyle("-fx-alignment: center;" + "-fx-font: 20 ARIAL" );
        
        
        textfield.setText(CommonClass.studentList.get(0).getStudID());
        for(Course x : CommonClass.studentList.get(0).getCourses()) {
        	listview.getItems().add(x);
        }
        
        
        for(int i = 0 ; i < CommonClass.courseList.size(); i++) {
        	if(CommonClass.courseList.get(i).getAvailableSeats()>0) {
                combobox.getItems().add(CommonClass.courseList.get(i).getCourseID());
        	}
        }
        
        
        back.setOnAction(new EventHandler<ActionEvent>() {				    //this button go back to the main screen
        	public void handle(ActionEvent g) {
        		back.getScene().setRoot(Main.mainScreen());             
        	}
        });
        
        next.setOnAction(new EventHandler<ActionEvent>() {					// this button transfer between student
        	public void handle(ActionEvent g) {
        		if(currentStudent < 199) {
        			currentStudent++;
        			listview.getItems().clear();
        			textfield.setText(CommonClass.studentList.get(currentStudent).getStudID());
        			for(Course x : CommonClass.studentList.get(currentStudent).getCourses()) {
        				listview.getItems().add(x);
        			}
        			
        		}
        	}	
        });
        
        previous.setOnAction(new EventHandler<ActionEvent>() {				// this button transfer between student
        	public void handle(ActionEvent g) {
        		if(currentStudent > 0) {
        			currentStudent--;
        			listview.getItems().clear();
        			textfield.setText(CommonClass.studentList.get(currentStudent).getStudID());
        			for(Course x : CommonClass.studentList.get(currentStudent).getCourses()) {
        				listview.getItems().add(x);
        			}
        			
        		}
        	}
        	
        });
        
        
        search.setOnAction(new EventHandler<ActionEvent>() {		// this button searching for student
            @Override
            public void handle(ActionEvent g) {
            	listview.getItems().clear();
            	String ID = textfield.getText();
        		for(int i = 0 ; i < CommonClass.studentList.size(); i++) {
        			if(CommonClass.studentList.get(i).getStudID().equals(ID)) {
        				currentStudent = i;
        				textfield.setText(CommonClass.studentList.get(i).getStudID());
    					for(Course x : CommonClass.studentList.get(i).getCourses()) {
    						listview.getItems().add(x);
    					}
        			}
        		}
            }	
        });
        
        
        register.setOnAction((ActionEvent e) -> { 		// this button register a cource
        	int i =  combobox.getSelectionModel().getSelectedIndex();
        	listview.getItems().add(combobox.getSelectionModel().getSelectedItem());
        	CommonClass.studentList.get(currentStudent).getCourses().add(CommonClass.courseList.get(i));
        	CommonClass.courseList.get(i).register();
        });
        
        drop.setOnAction((ActionEvent e) -> {		// this button drop a cource
        	int i = listview.getSelectionModel().getSelectedIndex();
        	listview.getItems().remove(listview.getSelectionModel().getSelectedItem());
            CommonClass.studentList.get(currentStudent).getCourses().remove(i);
            CommonClass.courseList.get(i).drop();
        });
        
    
        
        return pane;
	}
}