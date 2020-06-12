/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notification;
import Object.Deadline;
import Object.Task;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.TimerTask;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
/**
 *
 * @author Alam
 */
public class NotifyTask extends TimerTask{
    private Task t;
    SysTray tray;
    
    public NotifyTask(Task t){
        this.t = t;
        tray = SysTray.getInstance();
        
    }
    
    @Override
    public void run(){
        
        String message = "You have a task scheduled in 15 minutes \n" +
                            "Task: " + t.getName();
        System.out.println(message);
        tray.getTrayIcon().displayMessage("Task Reminder", message, TrayIcon.MessageType.INFO);
        
    }
}
