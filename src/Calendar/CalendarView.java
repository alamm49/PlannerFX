/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alam
 */
public class CalendarView extends BorderPane {

    private VBox box1;
    private VBox box2;

    private String pattern = "dd/MM/yyyy";
    private String date;
    private Button button;
    private CalendarController controller;
    private DatePicker dp;
    private TextField listDesc;
    private SuggestionNode suggestionNode;
    private DeadlineNode dlNode;
    private TaskNode taskNode;
    private ToDoListNode toDoListNode;
    private BorderPane calendarPane;
    private LocalDate selectedDate;

    public CalendarView(CalendarController controller) {

        this.getStyleClass().add("pane");
        this.controller = controller;
        box1 = new VBox();
        box2 = new VBox();
        initCalendar();
        initTaskTable();
        initToDoList();
        initDeadlineList();
        initSuggestionNode();
        this.setPadding(new Insets(5));
        box1.getChildren().addAll(toDoListNode, taskNode, dlNode);
        box2.getChildren().addAll(calendarPane, suggestionNode);

        this.setLeft(box2);
        this.setRight(box1);

    }

    private void initCalendar() {
        dp = new DatePicker();
        button = new Button("Add Task");
        calendarPane = new BorderPane();
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
                    controller.addTask(dp.getValue());

                } catch (Exception ee) {
                    System.out.println(ee.getMessage());
                }
            }
        });

        dp.valueProperty().addListener((observable, oldDate, newDate) -> {
            System.out.println(newDate.format(DateTimeFormatter.ofPattern(pattern)));
            taskNode.updateTaskTable(newDate);

            toDoListNode.updateToDoList(newDate);
            dlNode.update(newDate);
        });

        DatePickerSkin datePickerSkin = new DatePickerSkin(dp);

        Node popupContent = datePickerSkin.getPopupContent();

        calendarPane.setCenter(popupContent);
        calendarPane.setBottom(button);
    }

    private void initDeadlineList() {
        dlNode = new DeadlineNode(controller);

    }

    private void initTaskTable() {
        taskNode = new TaskNode(controller);

    }

    private void initToDoList() {
        toDoListNode = new ToDoListNode(controller);

    }

    private void initSuggestionNode() {
        suggestionNode = new SuggestionNode(controller);

    }

    public String getDate() {
        return date;
    }

    public void updateToDoListSuggestion(String suggestion) {
        System.out.println("updating");
        suggestionNode.updateToDoList(suggestion);
    }

    public void updateDeadlineSuggestion(String deadlineSuggestion) {
        suggestionNode.updateDeadline(deadlineSuggestion);
    }
}
