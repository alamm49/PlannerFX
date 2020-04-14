/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Calendar.CalendarController;
import Calendar.CalendarView;
import Database.DAO;
import Database.Model;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Alam
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model(new DAO());
        //BorderPane root = new BorderPane();
        
        
        CalendarController controller = new CalendarController(model);
        //controller.setModel(model);
        CalendarView root = controller.getView();
        
        Scene scene = new Scene(root, 1170, 250);
     
        
        //root.setCenter(cv);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
