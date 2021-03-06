/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import Form.TaskController;
import Object.Deadline;
import Object.Task;
import Object.ToDoList;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public CalendarController(Model model) {
        System.out.println("setting view");
        
        setModel(model);
        this.view = new CalendarView(this);
        getToDoListSuggestion();
        getDeadlineSuggestion();
        

    }

    public CalendarView getView() {
        return this.view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        System.out.println("got the model, setting: " + model);
        this.model = model;
        System.out.println(this.model);
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

    public ArrayList<Task> getTaskList(LocalDate d) {
        System.out.println("im in the controller" + model);
        ArrayList<Task> t = model.getTaskList(d);
        return t;
    }

    public void close() {
        stage.close();
    }

    public boolean deleteTask(Task t) {
        System.out.println("delete task: in calendar controller");
        if (model.deleteTask(t)) {
            return true;
        } else {
            return false;
        }
    }

    ArrayList<ToDoList> getToDoList(LocalDate date) {
        ArrayList<ToDoList> t = model.getToDoList(date);
        return t;
    }

    public void addToDoList(ToDoList l) {
        model.addToDoList(l);
        if (l.getDate().compareTo(LocalDate.now()) == 0) {
            view.updateToDoListSuggestion(model.getToDoListSuggestion());
        }
    }

    public boolean deleteToDoList(ToDoList l) {
        if (model.deleteToDoList(l)) {
            if (l.getDate().compareTo(LocalDate.now()) == 0) {
                view.updateToDoListSuggestion(model.getToDoListSuggestion());
            }
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Deadline> getDeadlineList(LocalDate d) {
        return model.getDeadlineList(d);
    }

    public boolean addDeadline(Deadline d) {
        if (model.addDeadline(d)) {
            if (d.getDate().compareTo(LocalDate.now()) == 0) {
                view.updateDeadlineSuggestion(model.getDeadlineSuggestion());
            }
            return true;
        } 
        else {
            return false;
        }
    }

    public boolean deleteDeadline(Deadline d) {
        if (model.deleteDeadline(d)) {
            if (d.getDate().compareTo(LocalDate.now()) == 0) {
                view.updateDeadlineSuggestion(model.getDeadlineSuggestion());
            }
            return true;
        } 
        else {
            return false;
        }
    }
    
    public void getToDoListSuggestion(){
        System.out.println("HERERERE");
        String m = model.getToDoListSuggestion();
        
        view.updateToDoListSuggestion(m);
        System.out.println("updating");
    }
    
    public void getDeadlineSuggestion(){
        
        view.updateDeadlineSuggestion(model.getDeadlineSuggestion());
    }

}
