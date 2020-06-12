/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Notification.NotifyDeadline;
import Notification.NotifyTask;
import Object.Deadline;
import Object.Task;
import Object.ToDoList;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

/**
 *
 * @author Alam
 */
public class Model {

    private ArrayList<Task> task;
    private ArrayList<ToDoList> toDoList;
    private ArrayList<Deadline> deadlineList;
    private DAO dao;
    private ArrayList<Task> todayTask;
    private ArrayList<Deadline> todayDeadline;
    private Timer taskTimer;
    private Timer deadlineTimer;

    public Model(DAO dao) {
        this.dao = dao;
        taskTimer = new Timer();
        deadlineTimer = new Timer();
        refreshTimer();

    }

    public void refreshTimer() {

        taskTimer.purge();
        deadlineTimer.purge();

        getToday();
        setNotifications();
    }

    private void getToday() {
        todayTask = getTaskList(LocalDate.now());
        todayDeadline = getDeadlineList(LocalDate.now());
    }

    private void setNotifications() {
        long delay;
        java.util.Date d = new Date();
        for (Task t : todayTask) {

            delay = t.getEpoch() - d.getTime() - 900000;
            System.out.println(delay);
            if (delay > 0) {
                System.out.println("setting timer");
                taskTimer.schedule(new NotifyTask(t), delay);
            }

        }

        for (Deadline x : todayDeadline) {
            delay = x.getEpoch() - d.getTime() - 900000;
            if (delay > 0) {
                System.out.println(delay);
                deadlineTimer.schedule(new NotifyDeadline(x), delay);
            }
        }
    }

    //>0 if start time is after end time (bad)
    //0 if start time is the same as end time (bad)
    //<0 if if start time is before end time (good)
    public boolean addTask(String name, String desc, LocalDate Date, String hour1, String minute1, String hour2, String minute2) {
        System.out.println("im in add task");
        Task t = new Task(name, desc, Date, combine(hour1, minute1), combine(hour2, minute2));

        if (t.compareTime() > 0 || t.compareTime() == 0) {
            System.out.println("start time is either the same or greater than end time");
            return false;
        } else if (t.compareTime() < 0) {
            System.out.println("start time is less than end time, good");
            if (checkDateTime(t)) {
                if (dao.addTask(t)) {
                    System.out.println("task added successfully");
                    task.add(t);
                    refreshTimer();
                    return true;
                } else {
                    System.out.println("seomthing went wrong when adding to database");
                    return false;
                }
            } else {
                System.out.println("there seems to be overlapping in times");
                return false;
            }

        } else {
            System.out.println("something bad happened");
            return false;
        }

    }

    //>0 if time is after start time of ref (bad)
    //0 if  time is the same as ref (bad)
    //<0 if if  time is before time of ref
    public boolean checkDateTime(Task t) {
        //ArrayList<Task> tasks = dao.getTask(t.getDate());

        if (task.isEmpty() || task == null) {
            return true;
        } else {
            for (Task ref : task) {
                if (t.getStartTime().before(ref.getEndTime()) && ref.getStartTime().before(t.getEndTime())) {
                    return false;
                }
            }
        }
        return true;
    }

    //combines two strings of number into a time object
    public Time combine(String hour, String minute) {
        Time time = Time.valueOf(hour + ":" + minute + ":00");
        return time;
    }

    public ArrayList<Task> getTaskList(LocalDate d) {
        this.task = dao.getTask(d);
        System.out.println("im in the model");
        return task;
    }

    public boolean deleteTask(Task t) {
        System.out.println("delete task: in model");

        if (dao.deleteTask(t)) {
            task.remove(t);
            refreshTimer();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<ToDoList> getToDoList(LocalDate date) {
        this.toDoList = dao.getToDoList(date);
        return toDoList;
    }

    public void addToDoList(ToDoList l) {
        if (l.getDescription().isEmpty() || l.getDate() == null) {
            System.out.println("description or date is missing");
            return;
        } else {

            dao.addToDoList(l);
            toDoList.add(l);
        }

    }

    public boolean deleteToDoList(ToDoList l) {
        if (dao.deleteToDoList(l)) {
            toDoList.remove(l);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Deadline> getDeadlineList(LocalDate d) {
        this.deadlineList = dao.getDeadlineList(d);
        return deadlineList;
    }

    public boolean addDeadline(Deadline d) {
        if (dao.addDeadline(d)) {
            deadlineList.add(d);
            refreshTimer();
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteDeadline(Deadline d) {
        if (dao.deleteDeadline(d)) {
            deadlineList.remove(d);
            refreshTimer();
            return true;
        } else {
            return false;
        }
    }
}
