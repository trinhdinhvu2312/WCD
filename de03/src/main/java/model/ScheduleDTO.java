package model;

import java.sql.Timestamp;

public class ScheduleDTO {
    private String examName;
    private int duration;
    private String timeSlot;
    private String examSitter;
    private Timestamp startAt;
    private Timestamp endAt;

    // Constructors
    public ScheduleDTO() {}

    public ScheduleDTO(String examName, int duration, String timeSlot,
                      String examSitter, Timestamp startAt, Timestamp endAt) {
        this.examName = examName;
        this.duration = duration;
        this.timeSlot = timeSlot;
        this.examSitter = examSitter;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    // Getters and Setters
    public String getExamName() { return examName; }
    public void setExamName(String examName) { this.examName = examName; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getExamSitter() { return examSitter; }
    public void setExamSitter(String examSitter) { this.examSitter = examSitter; }

    public Timestamp getStartAt() { return startAt; }
    public void setStartAt(Timestamp startAt) { this.startAt = startAt; }

    public Timestamp getEndAt() { return endAt; }
    public void setEndAt(Timestamp endAt) { this.endAt = endAt; }
}