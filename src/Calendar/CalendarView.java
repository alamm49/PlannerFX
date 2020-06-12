/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import Form.TaskController;
import Object.Task;
import Object.ToDoList;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.sql.Time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class CalendarView extends BorderPane{
    VBox box1;
    VBox box2;
    
    String pattern = "dd/MM/yyyy";
    String date;
    Button button;
    CalendarController controller;
    DatePicker dp;
    TextField listDesc;
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
        this.setPadding(new Insets(5));
        box1.getChildren().addAll(toDoListNode,taskNode, dlNode);
        box2.getChildren().add(calendarPane);
        //this.setCenter(taskNode);
        //this.setBottom(dlNode);
        this.setLeft(box2);
        this.setRight(box1);
        
        
        //this.setHgap(10);
        //this.setVgap(10);
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
        
        
        //this.add(pane, 0, 0);
    }
    
    private void initDeadlineList(){
        dlNode = new DeadlineNode(controller);
        //this.add(dlNode,0,1);
    }
    
     private void initTaskTable(){
        taskNode = new TaskNode(controller);
        //this.add(taskNode, 1, 0);
        
    }
    
     private void initToDoList(){
         toDoListNode = new ToDoListNode(controller);
         //this.add(toDoListNode, 1, 1);
     }
    /*
    private void initToDoListTable(){
        toDoListPane = new BorderPane();
      
        
        //description column
        TableColumn<ToDoList, String> descColumn = new TableColumn<>("To-do List");
        descColumn.setMaxWidth(400);
        descColumn.setMinWidth((270));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descColumn.setSortable(false);
        descColumn.setResizable(false);
        
        /*
        //checkbox column
        TableColumn<ToDoList, CheckBox> checkBoxColumn =  new TableColumn<>("✔");
        checkBoxColumn.setMaxWidth(30);
        checkBoxColumn.setCellValueFactory(new PropertyValueFactory<>("complete"));
        checkBoxColumn.setSortable(false);
        checkBoxColumn.setResizable(false);
        
        toDoList = new TableView<>();
        try {
            toDoList.setItems(getToDoList(LocalDate.now()));
        } 
        catch (Exception e) {
            System.out.println("nothing in todolist");
        }
        
        
        
        toDoList.getColumns().addAll(descColumn);
        toDoList.getStylesheets().add(getClass().getResource("/Main/tableCSS.css").toExternalForm());
        toDoList.prefHeightProperty().bind(Bindings.size(toDoList.getItems()).multiply(toDoList.getFixedCellSize()).add(25));
        
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        listDesc = new TextField();
        addButton.setOnAction(e -> addToDoListButtonClicked(listDesc.getText(),dp.getValue()));
        deleteButton.setOnAction(e -> deleteToDoListButtonClicked());
        
        HBox hBox = new HBox();
        //hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(listDesc, addButton, deleteButton);
        
        
        toDoListPane.setBottom(hBox);
        toDoListPane.setCenter(toDoList);
        this.add(toDoListPane, 2, 0);
        
    }
    
    private ObservableList<ToDoList> getToDoList(LocalDate date){
        ObservableList<ToDoList> t = FXCollections.observableArrayList();
        ArrayList<ToDoList> t2 = controller.getToDoList(date);

        for (ToDoList t3 : t2) {
            t.add(t3);
        }
        return t;
    }
    
    private void updateToDoList(LocalDate d){
        toDoList.getItems().clear();
        try {
            toDoList.setItems(getToDoList(d));
        } 
        catch (Exception e) {
            System.out.println("there were no lists today");
        }
    }
    
    public void deleteToDoListButtonClicked(){
        ObservableList<ToDoList> taskSelected, allProducts;
        allProducts = toDoList.getItems();
        taskSelected = toDoList.getSelectionModel().getSelectedItems();
        
        if(controller.deleteToDoList(taskSelected.get(0))){
             taskSelected.forEach(allProducts::remove);
        }
        else{
            System.out.println("something went wrong when deleting the task");
        }
       
    }
    public void addToDoListButtonClicked(String desc, LocalDate d){
        ToDoList l = new ToDoList(desc,d);
        controller.addToDoList(l);
        toDoList.getItems().add(l);
        listDesc.clear();
    }
    */
   
    /*
    private void initTaskTable() {
        Button deleteButton = new Button("Delete Task");
        taskTablePane = new BorderPane();
        //name column
        TableColumn<Task, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setSortable(false);
        nameColumn.setResizable(false);
        
        //StartTime
        TableColumn<Task, Time> startTimeColumn = new TableColumn<>("Start Time");
        startTimeColumn.setMaxWidth(200);
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        startTimeColumn.setSortable(false);
        startTimeColumn.setResizable(false);
        
        //EndTime
        TableColumn<Task, Time> endTimeColumn = new TableColumn<>("End Time");
        endTimeColumn.setMaxWidth(200);
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        endTimeColumn.setSortable(false);
        endTimeColumn.setResizable(false);
        
        
        taskTable = new TableView<>();
        try {
            taskTable.setItems(getTask(LocalDate.now()));
        } 
        catch (Exception e) {
            System.out.println("there were no tasks today");
        }
        
        
        taskTable.getColumns().addAll(nameColumn, startTimeColumn, endTimeColumn);
        taskTable.getStylesheets().add(getClass().getResource("/Main/tableCSS.css").toExternalForm());
        taskTable.prefHeightProperty().bind(Bindings.size(taskTable.getItems()).multiply(taskTable.getFixedCellSize()).add(25));
        
        //delete button
        deleteButton.setOnAction(e -> deleteButtonClicked());
        
        taskTablePane.setCenter(taskTable);
        taskTablePane.setBottom(deleteButton);
        //this.add(taskTable, 1, 0);
        
        this.add(taskTablePane, 0, 0);
    }
    
    public void updateTaskTable(LocalDate d){
        taskTable.getItems().clear();
        try {
            taskTable.setItems(getTask(d));
        } 
        catch (Exception e) {
            System.out.println("there were no tasks today");
        }
    }

    private void deleteButtonClicked(){
        ObservableList<Task> taskSelected, allProducts;
        allProducts = taskTable.getItems();
        taskSelected = taskTable.getSelectionModel().getSelectedItems();
        
        if(controller.deleteTask(taskSelected.get(0))){
             taskSelected.forEach(allProducts::remove);
        }
        else{
            System.out.println("something went wrong when deleting the task");
        }
       
    }
    
    private ObservableList<Task> getTask(LocalDate date) {
        ObservableList<Task> t = FXCollections.observableArrayList();
        ArrayList<Task> t2 = controller.getTaskList(date);

        for (Task t3 : t2) {
            t.add(t3);
        }
        return t;
    }
    */
    
    public String getDate() {
        return date;
    }
}
