/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import Form.TaskController;
import com.sun.javafx.scene.control.skin.DatePickerSkin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 *
 * @author Alam
 */
public class CalendarView extends BorderPane {
    String pattern = "dd/MM/yyyy";
    String date;
    Button button;
    CalendarController controller;
    
    public CalendarView(CalendarController controller) {
        this.controller = controller;
        
        DatePicker dp = new DatePicker();
        button = new Button("Add Task");
        //disables past dates
        dp.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });
        
        
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    
                    try {
                        //System.out.println(dp.getValue());
                        
                        
                        controller.addTask(dp.getValue());
                        
                        /*
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Form/TaskFXML.fxml"));
                        Parent root = loader.load();
                        
                        
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));

                        stage.show();
                        */
                    } catch (Exception ee) {
                        System.out.println(ee.getMessage());
                    }
                }
            });
        
       
        dp.valueProperty().addListener((observable, oldDate, newDate) -> {
            System.out.println(newDate.format(DateTimeFormatter.ofPattern(pattern)));
            date = newDate.format(DateTimeFormatter.ofPattern(pattern));
        });

        DatePickerSkin datePickerSkin = new DatePickerSkin(dp);
        
        Node popupContent = datePickerSkin.getPopupContent();

        this.setCenter(popupContent);
        this.setBottom(button);
    }
    
    public String getDate(){
        return date;
    }
}
