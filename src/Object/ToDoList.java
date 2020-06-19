/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.time.LocalDate;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Alam
 */
public class ToDoList {

    private String description;
    private LocalDate date;
    private CheckBox complete;
    private boolean boolComplete;
    private boolean important;

    public ToDoList() {

    }

    public ToDoList(String description, LocalDate date, String importance) {
        this.description = description;
        this.date = date;
        if (importance.equals("Yes")) {
            this.important = true;
        } else if (importance.equals("No")) {
            this.important = false;
        } else {
            System.out.println("Trouble with to do list importance");
        }

        this.boolComplete = false;
        this.complete = new CheckBox();
    }

    public ToDoList(String description, LocalDate date) {
        this.description = description;
        this.date = date;
        this.boolComplete = false;
        this.complete = new CheckBox();
    }

    public boolean getImportant() {
        return important;
    }

    public String getDescription() {
        return description;
    }

    public int getCompleteN() {
        if (boolComplete) {
            return 1;
        } else {
            return 0;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public CheckBox getComplete() {
        return complete;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setComplete(int bool) {
        if (bool == 0) {
            this.complete = new CheckBox();
            boolComplete = false;
            complete.setSelected(boolComplete);
        } else if (bool == 1) {
            this.complete = new CheckBox();
            boolComplete = false;
            complete.setSelected(boolComplete);
        }

    }

    public void setImportant(int importance) {

        if (importance == 1) {
            this.important = true;
        }
        else if(importance == 0){
            this.important = false;
        }
    }

    public int getImportantN() {
        if (important) {
            return 1;
        } else {
            return 0;
        }
    }

}