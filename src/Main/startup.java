/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Calendar.CalendarNode;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Alam
 */
public class startup extends Application {

    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);

            CalendarNode cc = new CalendarNode();
            button = new Button("Add Task");

            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    
                    try {
                       
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Form/TaskFXML.fxml"));
                        Parent root = loader.load();
                        
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));

                        stage.show();
                    } catch (Exception ee) {
                        System.out.println(ee.getMessage());
                    }
                }
            });

            root.setCenter(cc);
            root.setBottom(button);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showForm() {

    }

}
