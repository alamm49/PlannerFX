/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Alam
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private static DBConnector i;
    private Connection conn;
    
    public DBConnector(){
        
        //establishing database connection
        try{
            
            conn = DriverManager.getConnection("jdbc:sqlite:planner.db");
            
            System.out.println("database connected");
        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static DBConnector getInstance(){
        if (i == null){
            i = new DBConnector();
            return i;
        } 
        else {
            return i;
        }
    }
    public Connection getConnection(){
        return conn;
    }
}    
