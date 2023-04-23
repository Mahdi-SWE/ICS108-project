package project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class one extends Application {
	Text text;
	TextField fieldtext;
	int chance = 2;
	
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10,10,10,10));
		
		int one = (int) (Math.random() *10);
		int two = (int) (Math.random() *10);
		HBox boxnumber = new HBox(10);
		VBox boxanswer= new VBox(10);
		Button button = new Button("Calculate");
		TextField number1 = new TextField();
		TextField number2 = new TextField();
		TextField answer = new TextField();
		
		fieldtext = new TextField();
		
		text = new Text("what is the answer of the product of these two number?");
		boxnumber.getChildren().addAll(number1, number2);
		boxanswer.getChildren().addAll(text,answer,fieldtext);

		number1.setText(String.valueOf(one));
		number2.setText(String.valueOf(two));

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(chance > 0) {
					if(Integer.valueOf(answer.getText()) == one * two) {
						fieldtext.setText("correct");
					}else{
						fieldtext.setText(String.valueOf("you have "+ chance + " chance"));
						chance--;
					}
					
				}else {
					fieldtext.setText("You have used all you chances");
				}
			}
		});
		pane.setTop(boxnumber);
		pane.setCenter(boxanswer);
		pane.setBottom(button);
		BorderPane.setAlignment(button, Pos.CENTER);
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Example");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}