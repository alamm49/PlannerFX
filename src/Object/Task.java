/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author Alam
 */
public class Task {

    private String name;
    private String description;
    private LocalDate date;
    private Time startTime;
    private Time endTime;

    public Task() {

    }

    public Task(String name, String description, LocalDate date, Time startTime, Time endTime) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public int compareTime() {
        return startTime.compareTo(endTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getSQLDate() {
        return Date.valueOf(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public long getEpoch() {
        long epoch = 0;
        java.util.Date d;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + " " + startTime.toString();
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
