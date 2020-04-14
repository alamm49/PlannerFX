/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.sql.Time;
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
    
    
        
}
