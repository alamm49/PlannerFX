/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

/**
 *
 * @author Alam
 */
public class SuggestionNode extends HBox{
    //this is where suggestions will be displayed
    private TextArea toDoList;
    private TextArea deadline;
    private CalendarController controller;
    
    
    public SuggestionNode(CalendarController controller){
        double hieght = 150;
        double width = 200;
        
        this.controller = controller;
        toDoList = new TextArea();
        deadline = new TextArea();
        
        toDoList.setPrefSize(width, hieght);
        deadline.setPrefSize(width, hieght);
        toDoList.setWrapText(true);
        deadline.setWrapText(true);
        toDoList.setEditable(false);
        deadline.setEditable(false);
        this.getChildren().addAll(toDoList, deadline);
        
    }
    
   

    public void updateToDoList(String suggestion) {
        toDoList.setText(suggestion);
    }

    public void updateDeadline(String deadlineSuggestion) {
        deadline.setText(deadlineSuggestion);
    }
}
