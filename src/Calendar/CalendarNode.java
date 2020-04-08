/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;


/**
 *
 * @author Alam
 */
public class CalendarNode extends BorderPane {
    String pattern = "dd/MM/yyyy";
    String date;
    public CalendarNode() {
        
        DatePicker dp = new DatePicker();
        
        //disables past dates
        dp.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });

        dp.valueProperty().addListener((observable, oldDate, newDate) -> {
            System.out.println(newDate.format(DateTimeFormatter.ofPattern(pattern)));
            date = newDate.format(DateTimeFormatter.ofPattern(pattern));
        });

        DatePickerSkin datePickerSkin = new DatePickerSkin(dp);
        
        Node popupContent = datePickerSkin.getPopupContent();

        this.setCenter(popupContent);
    }

    public String getDate(){
        return date;
    }
}
