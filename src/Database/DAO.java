/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Database.DBConnector;
import Object.Task;
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
    private DAO dao = null;

    public DAO() {
        DBConnector i = new DBConnector();

        con = i.getConnection();
    }

    public DAO getInstance() {
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
        
        try {
            String statement = "Select * From Task Where Date = ?";
            PreparedStatement stmnt = con.prepareStatement(statement);
            stmnt.setString(1, date.toString());
            ResultSet rs = stmnt.executeQuery();
            
            while(rs.next()){
                t = new Task();
                t.setName(rs.getString("Name"));
                t.setDescription(rs.getString("description"));
                t.setDate(LocalDate.parse(rs.getString("Date")));
                t.setStartTime(Time.valueOf(rs.getString("StartTime")));
                t.setEndTime(Time.valueOf(rs.getString("EndTime")));
                tasks.add(t);
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tasks;

    }
}
