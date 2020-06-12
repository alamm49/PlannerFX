/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author Alam
 */
public class Deadline {
    
    private String description;
    private LocalDate date;
    private Time time;

    public Deadline(String description, LocalDate date, Time time) {
        this.description = description;
        this.date = date;
        this.time = time;
    }
    
    public Deadline(){
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    public long getEpoch(){
        long epoch = 0;
        java.util.Date d;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + " " + time.toString();
        try {
            d = df.parse(dateTime);
            epoch = d.getTime();
        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        return epoch;
    }
        
}
