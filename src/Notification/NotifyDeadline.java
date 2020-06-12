/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notification;

import Object.Deadline;
import java.awt.TrayIcon;
import java.util.TimerTask;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Alam
 */
public class NotifyDeadline extends TimerTask{
    private Deadline d;
    SysTray tray;
    
    public NotifyDeadline(Deadline d){
        this.d = d;
        tray = SysTray.getInstance();
    }
    
    @Override
    public void run() {
       String message = "You have a deadline due in 15 minutes /n" + 
                            "Deadline: " + d.getDescription();
        tray.getTrayIcon().displayMessage("Task Reminder", message, TrayIcon.MessageType.INFO);
    }
    
}
