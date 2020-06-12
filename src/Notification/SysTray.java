/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notification;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

/**
 *
 * @author Alam
 */
public class SysTray {

    private SystemTray tray;
    private static SysTray i;
    private TrayIcon trayIcon;

    public SysTray() {
        if (SystemTray.isSupported()) {
            Image image = Toolkit.getDefaultToolkit().createImage("Icon.png");
            tray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon(image, "Planner");

            trayIcon.setImageAutoSize(true);
            try {
                tray.add(trayIcon);
                System.out.println("trying to display message");
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static SysTray getInstance() {
        if (i == null) {
            i = new SysTray();
            return i;
        } else {
            return i;
        }
    }

    public TrayIcon getTrayIcon() {
        return trayIcon;
    }
}
