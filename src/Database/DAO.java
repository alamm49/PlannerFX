/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Object.Deadline;
import Object.Task;
import Object.ToDoList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Alam
 */
public class DAO {

    private Connection con;
    private static DAO dao = null;

    public DAO() {
        DBConnector i = DBConnector.getInstance();

        con = i.getConnection();
    }

    public static DAO getInstance() {
        if (dao == null) {
            dao = new DAO();
            return dao;
        } else {
            return dao;
        }
    }

    public boolean addTask(Task task) {

        try {
            String statement = "INSERT INTO Task(Name,description,Date,StartTime,EndTime)"
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement stmnt = con.prepareStatement(statement);
            stmnt.setString(1, task.getName());
            stmnt.setString(2, task.getDescription());
            stmnt.setString(3, convertLocalDate(task.getDate()).toString());
            stmnt.setString(4, task.getStartTime().toString());
            stmnt.setString(5, task.getEndTime().toString());
            stmnt.execute();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Date convertLocalDate(LocalDate date) {
        return Date.valueOf(date);
    }

    public ArrayList<Task> getTask(LocalDate date) {
        ArrayList<Task> tasks = new ArrayList();
        Task t = null;
        System.out.println("im trying to find tasks on " + date);
        try {
            String statement = "Select * From Task Where Date = ?";
            PreparedStatement stmnt = con.prepareStatement(statement);
            stmnt.setString(1, date.toString());
            ResultSet rs = stmnt.executeQuery();

            while (rs.next()) {
                t = new Task();
                t.setName(rs.getString("Name"));
                t.setDescription(rs.getString("description"));
                t.setDate(LocalDate.parse(rs.getString("Date")));
                t.setStartTime(Time.valueOf(rs.getString("StartTime")));
                t.setEndTime(Time.valueOf(rs.getString("EndTime")));
                tasks.add(t);
                System.out.println(t.getName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tasks;

    }

    public boolean deleteTask(Task t) {
        System.out.println("delete task: in dao");

        try {
            System.out.println("preparing statement " + t);

            String statement = "Delete From Task Where Name=? And description=? And Date=? And StartTime=? And EndTime=?";
            PreparedStatement stmnt = con.prepareStatement(statement);
            System.out.println("setting values");
            stmnt.setString(1, t.getName());
            stmnt.setString(2, t.getDescription());
            stmnt.setString(3, convertLocalDate(t.getDate()).toString());
            stmnt.setString(4, t.getStartTime().toString());
            stmnt.setString(5, t.getEndTime().toString());
            stmnt.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<ToDoList> getToDoList(LocalDate date) {
        ArrayList<ToDoList> toDoList = new ArrayList();

        ToDoList t = null;
        System.out.println("im trying to find to do list on " + date);
        try {
            String statement = "Select * From ToDoList Where Date = ?";
            PreparedStatement stmnt = con.prepareStatement(statement);
            stmnt.setString(1, date.toString());
            ResultSet rs = stmnt.executeQuery();

            while (rs.next()) {
                t = new ToDoList();

                t.setDescription(rs.getString("description"));
                t.setDate(LocalDate.parse(rs.getString("Date")));
                t.setComplete(rs.getInt("Complete"));
                t.setImportant(rs.getInt("Important"));
                toDoList.add(t);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return toDoList;
    }

    public void addToDoList(ToDoList d) {
        try {
            String statement = "INSERT INTO ToDoList(Description, Date, Complete, Important)"
                    + "VALUES(?,?,?,?)";
            PreparedStatement stmnt = con.prepareStatement(statement);
            stmnt.setString(1, d.getDescription());
            stmnt.setString(2, convertLocalDate(d.getDate()).toString());
            stmnt.setInt(3, 0);
            stmnt.setInt(4, d.getImportantN());
            stmnt.execute();
            
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteToDoList(ToDoList l) {
        try{
            
            System.out.println("preparing statement " + l);

            String statement = "Delete From ToDoList Where Description=? And Date=? And Complete =? And Important =?";
            PreparedStatement stmnt = con.prepareStatement(statement);
            System.out.println("setting values");
            stmnt.setString(1, l.getDescription());
            stmnt.setString(2, convertLocalDate(l.getDate()).toString());
            stmnt.setInt(3, l.getCompleteN());
            stmnt.setInt(4, l.getImportantN());
            
            stmnt.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Deadline> getDeadlineList(LocalDate d){
        ArrayList<Deadline> list = new ArrayList<>();
        Deadline deadline = null;
        System.out.println("trying to find deadlines");
        try{
            String statement = "Select * From Deadline Where date=?";
            PreparedStatement stmnt = con.prepareStatement(statement);
            
            stmnt.setString(1, convertLocalDate(d).toString());
            
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()){
                deadline = new Deadline();
                deadline.setDescription(rs.getString("Description"));
                deadline.setDate(LocalDate.parse(rs.getString("Date")));
                deadline.setTime(Time.valueOf(rs.getString("Time")));
                
                list.add(deadline);
            }
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public boolean addDeadline(Deadline d){
        try{
            String statement = "INSERT INTO Deadline(Description, Date, Time)"
                    + "VALUES(?,?,?)";
            
            PreparedStatement stmnt = con.prepareStatement(statement);
            stmnt.setString(1, d.getDescription());
            stmnt.setString(2, convertLocalDate(d.getDate()).toString());
            stmnt.setString(3, d.getTime().toString());
            stmnt.execute();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean deleteDeadline(Deadline d){
        try{
            String statement = "Delete From Deadline Where Description =? And Date = ? And Time = ?";
            PreparedStatement stmnt = con.prepareStatement(statement);
            stmnt.setString(1, d.getDescription());
            stmnt.setString(2, convertLocalDate(d.getDate()).toString());
            stmnt.setString(3, d.getTime().toString());
            stmnt.execute();
            
        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
