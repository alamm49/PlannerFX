/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Calendar.CalendarController;
import Calendar.CalendarView;
import Database.DAO;
import Calendar.Model;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alam
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model(new DAO());       
        
        CalendarController controller = new CalendarController(model);

        CalendarView root = controller.getView();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Main/tableCSS.css");
   

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
