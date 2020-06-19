/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import Object.Deadline;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class DeadlineNode extends BorderPane{
    private CalendarController controller;
    private TableView<Deadline> deadlineTable;
    private Button add;
    private Button delete;
    private TextField description;
    private HBox hBox;
    private ComboBox hour;
    private ComboBox minute;
    private LocalDate date;
    
    public DeadlineNode(CalendarController controller){
        this.date = LocalDate.now();
        this.controller = controller;
        initTable();
        initComponents();
        this.setMinHeight(200);
        this.setMinWidth(200);
    }
    
    public void update(LocalDate d){
        this.date = d;
        deadlineTable.getItems().clear();
        try{
            deadlineTable.setItems(getDeadlineList(d));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void initComponents(){
        //init components
        hBox = new HBox(); 
        add = new Button("Add");
        delete = new Button ("Delete");
        description = new TextField();
        hour = new ComboBox();
        minute = new ComboBox();
        hour.getItems().addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minute.getItems().addAll("00", "15", "30", "45");
        
        //adding button click function
        
        add.setOnAction(e -> addDeadline(description.getText(), (String) hour.getValue(), (String) minute.getValue()));
        delete.setOnAction(e -> deleteDeadline());
        
        //adding components to pane
        hBox.getChildren().addAll(description,hour, minute, add, delete);
        
        this.setBottom(hBox);
        
        
    }
    
    private void initTable() {
        
        //description column
        TableColumn<Deadline, String> nameColumn = new TableColumn<>("Deadline");
        nameColumn.setMaxWidth(600);
        nameColumn.setMinWidth(275);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nameColumn.setSortable(false);
        nameColumn.setResizable(false);
        
        //Time
        TableColumn<Deadline, Time> startTimeColumn = new TableColumn<>("Time");
        startTimeColumn.setMaxWidth(200);
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        startTimeColumn.setSortable(false);
        startTimeColumn.setResizable(false);
        
        deadlineTable = new TableView<>();
        try {
            deadlineTable.setItems(getDeadlineList(LocalDate.now()));
        } 
        catch (Exception e) {
            System.out.println("there's no deadlines today");
        }
        
        
        deadlineTable.getColumns().addAll(nameColumn, startTimeColumn);
        deadlineTable.prefHeightProperty().bind(Bindings.size(deadlineTable.getItems()).multiply(deadlineTable.getFixedCellSize()).add(25));
        
        
        
        this.setCenter(deadlineTable);
    }
    
    private void addDeadline(String description, String h, String m){
        if(description.isEmpty()|| h.isEmpty()||m.isEmpty()){
            System.out.println("empty deadlines");
        }
        else{
            Deadline d = new Deadline(description, date, combine(h,m));
            if(controller.addDeadline(d)){
                deadlineTable.getItems().add(d);
            }
        }
    }
    
    private void deleteDeadline(){
        ObservableList<Deadline> deadlineSelected, allDeadline;
        allDeadline = deadlineTable.getItems();
        deadlineSelected = deadlineTable.getSelectionModel().getSelectedItems();
        
        if(controller.deleteDeadline(deadlineSelected.get(0))){
            deadlineSelected.forEach(allDeadline::remove);
        }
        else{
            System.out.println("something went wrong when deleting the deadline");
        }
        
    }
    
    private ObservableList<Deadline> getDeadlineList(LocalDate d){
        ObservableList<Deadline> l = FXCollections.observableArrayList();
        ArrayList<Deadline> l2 = controller.getDeadlineList(d);
        
        for (Deadline l3: l2){
            l.add(l3);
        }
        return l;
    }
    
    public Time combine(String hour, String minute) {
        Time time = Time.valueOf(hour + ":" + minute + ":00");
        return time;
    }
}
