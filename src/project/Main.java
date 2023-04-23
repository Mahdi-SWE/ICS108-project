//--module-path "D:\JavaFX\javafx-sdk-17.0.1\lib" --add-modules javafx.controls,javafx.fxml

package project;
import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
        launch(args);
    }
	
	public void start(Stage primaryStage) {	                // here creating the main screen
		CommonClass.loadBinaryData();
		Scene scene = new Scene(mainScreen(),1200,600);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
		
	public static BorderPane mainScreen() {		//creating the button for mainscreen and creating a borderpane
		
		BorderPane borderpane = new BorderPane();
        Button viewCourse = new Button("View courses");
        Button viewStudents = new Button("View Student Details");
        Button save = new Button("Save");

        Text text = new Text(800,800, "Regestration System");		
        text.setStyle( "-fx-alignment: center;" + "-fx-font: 50 ARIAL");
        
        HBox hbox = new HBox(8) ;
        hbox.setAlignment(Pos.CENTER);		// Horizontal box which arranging the button
        hbox.setSpacing(50);
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.getChildren().addAll(viewCourse,viewStudents,save);
        borderpane.setStyle("-fx-alignment: center;" + "-fx-font: 20 ARIAL" );
        borderpane.setPadding(new Insets(20,20,20,20)); 
        borderpane.setCenter(text);
        borderpane.setBottom(hbox);
        
        
        viewCourse.setOnAction(new EventHandler<ActionEvent>() {		// this button link you to viewCourse screen

            @Override
            public void handle(ActionEvent g) {
            	viewCourse.getScene().setRoot(ViewCourse.screen());             
            }
        });
        
        viewStudents.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent g) {
            	viewStudents.getScene().setRoot(StudentDetails.screen());             
            }
        });
        
        save.setOnAction(new EventHandler<ActionEvent>() {		// the action for save button which saving the change

            @Override
            public void handle(ActionEvent g) {
            	CommonClass.save() ;
            }
        });
        
		return borderpane;
		
    }
}
