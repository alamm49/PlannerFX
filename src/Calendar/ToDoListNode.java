/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import Object.ToDoList;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Alam
 */
public class ToDoListNode extends BorderPane {

    private TableView<ToDoList> toDoList;
    private CalendarController controller;
    private TextField listDesc;
    private HBox hBox;
    private LocalDate date;

    public ToDoListNode(CalendarController c) {
        this.controller = c;
        initToDoListTable();
        initComponents();
        this.setMinHeight(200);
        this.setMinWidth(200);
       
        
    }

    private void initToDoListTable() {

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
         */
        toDoList = new TableView<>();
        try {
            toDoList.setItems(getToDoList(LocalDate.now()));
        } catch (Exception e) {
            System.out.println("nothing in todolist");
        }

        toDoList.getColumns().addAll(descColumn);
        
        toDoList.prefHeightProperty().bind(Bindings.size(toDoList.getItems()).multiply(toDoList.getFixedCellSize()).add(25));

        this.setCenter(toDoList);
        //this.add(toDoListPane, 2, 0);

    }

    private void initComponents() {
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        listDesc = new TextField();

        addButton.setOnAction(e -> addToDoListButtonClicked(listDesc.getText()));
        deleteButton.setOnAction(e -> deleteToDoListButtonClicked());

         hBox = new HBox();
        //hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(listDesc, addButton, deleteButton);

        this.setBottom(hBox);
    }

    private ObservableList<ToDoList> getToDoList(LocalDate date) {
        ObservableList<ToDoList> t = FXCollections.observableArrayList();
        ArrayList<ToDoList> t2 = controller.getToDoList(date);

        for (ToDoList t3 : t2) {
            t.add(t3);
        }
        return t;
    }

    public void updateToDoList(LocalDate d) {
        this.date = d;
        toDoList.getItems().clear();
        try {
            toDoList.setItems(getToDoList(d));
        } catch (Exception e) {
            System.out.println("there were no lists today");
        }
    }

    public void deleteToDoListButtonClicked() {
        ObservableList<ToDoList> taskSelected, allProducts;
        allProducts = toDoList.getItems();
        taskSelected = toDoList.getSelectionModel().getSelectedItems();

        if (controller.deleteToDoList(taskSelected.get(0))) {
            taskSelected.forEach(allProducts::remove);
        } else {
            System.out.println("something went wrong when deleting the task");
        }

    }

    public void addToDoListButtonClicked(String desc) {

        if (desc.isEmpty()) {
            System.out.println("description is empty");
        } else {
            
            ToDoList l = new ToDoList(desc, date);
            
            controller.addToDoList(l);
            
            toDoList.getItems().add(l);
            listDesc.clear();
        }

    }
}
