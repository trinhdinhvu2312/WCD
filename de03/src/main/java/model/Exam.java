package model;

import java.sql.Timestamp;

public class Exam {
    private int id;
    private String name;
    private int duration;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructors
    public Exam() {}

    public Exam(int id, String name, int duration, String description,
                Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}