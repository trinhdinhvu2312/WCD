package model;

import java.sql.Timestamp;

public class Scheduler {
    private int id;
    private String timeSlot;
    private String location;
    private String examSitter;
    private Timestamp startAt;
    private Timestamp endAt;

    // Constructors
    public Scheduler() {}

    public Scheduler(int id, String timeSlot, String location, String examSitter,
                    Timestamp startAt, Timestamp endAt) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.location = location;
        this.examSitter = examSitter;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getExamSitter() { return examSitter; }
    public void setExamSitter(String examSitter) { this.examSitter = examSitter; }

    public Timestamp getStartAt() { return startAt; }
    public void setStartAt(Timestamp startAt) { this.startAt = startAt; }

    public Timestamp getEndAt() { return endAt; }
    public void setEndAt(Timestamp endAt) { this.endAt = endAt; }
}