/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Alam
 */
public class TaskController {

    @FXML
    private ComboBox hour1;
    @FXML
    private ComboBox minute1;

    @FXML
    private ComboBox hour2;
    @FXML
    private ComboBox minute2;

    @FXML
    private TextField name;
    @FXML
    private TextArea desc;
    @FXML
    private TextField date;
    
    public TaskController(){
        
    }
    
    @FXML
    public void initialize() {
        hour1.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minute1.getItems().addAll("00", "15", "30", "45", "60");

        hour2.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minute2.getItems().addAll("00", "15", "30", "45", "60", "cake");
    }

    public void submitButtonClicked() {
        System.out.println(hour1.getValue());
    }

    public void cancelButtonClicked() {

    }
}
