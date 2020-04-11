/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import Database.Model;
import Form.TaskController;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alam
 */
public class CalendarController {

    private CalendarView view;
    private Model model;
    private Stage stage;
    
    public CalendarController() {
        System.out.println("im here");
        this.view = new CalendarView(this);

    }

    public CalendarView getView() {
        return this.view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void addTask(LocalDate date) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Form/TaskFXML.fxml"));
            Parent root = loader.load();
            TaskController TC = loader.getController();
            TC.setModel(model);
            TC.setDate(date);
            TC.setCC(this);
            Stage stage = new Stage();
            this.stage = stage;
            stage.setScene(new Scene(root));
            
            stage.show();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void close(){
        stage.close();
    }

}
