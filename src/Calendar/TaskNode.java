/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import Object.Task;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Alam
 */
public class TaskNode extends BorderPane{
    private TableView<Task> taskTable;
    private CalendarController controller;
    
    public TaskNode(CalendarController c){
        this.controller = c;
        initTaskTable();
    }
    
    private void initTaskTable() {
        Button deleteButton = new Button("Delete Task");
        
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
        
        this.setCenter(taskTable);
        this.setBottom(deleteButton);
        //this.add(taskTable, 1, 0);
        
        //this.add(taskTablePane, 0, 0);
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
}
