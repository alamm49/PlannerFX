/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Calendar.CalendarController;
import Database.Model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private Model m;
    private String pattern = "dd/MM/yyyy";
    private LocalDate rDate;
    private CalendarController CC;
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

    public TaskController() {

    }

    @FXML
    public void initialize() {
        hour1.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minute1.getItems().addAll("00", "15", "30", "45");

        hour2.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minute2.getItems().addAll("00", "15", "30", "45");
    }

    public void submitButtonClicked() {
        String n = name.getText();
        String d = desc.getText();
        LocalDate da = rDate;
        String h1 = (String) hour1.getValue();
        String m1 = (String) minute1.getValue();
        String h2 = (String) hour2.getValue();
        String m2 = (String) minute2.getValue();
        System.out.println(n + "\n" + d + "\n" + da + "\n" + h1 + "\n" + m1 + "\n" + h2 + "\n" + m2);
        if (h1 == null || m1== null  || h2== null  || m2== null || n.isEmpty()) {
            System.out.println("Times are empty");
        } 
        else {
            if (m.addTask(n, d, da, h1, m1, h2, m2)) {
                System.out.println("task controller: task was added...");
                CC.close();
            }

        }

    }

    public void cancelButtonClicked() {
        CC.close();
    }

    public void setDate(LocalDate d) {
        date.setText(d.format(DateTimeFormatter.ofPattern(pattern)));
        this.rDate = d;
        System.out.println("trying to do something");
    }

    public void setModel(Model m) {
        System.out.println("setting model " + m);
        this.m = m;
    }

    public void setCC(CalendarController CC) {
        this.CC = CC;
    }
}
