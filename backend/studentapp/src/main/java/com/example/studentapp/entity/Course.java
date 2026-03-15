package com.example.studentapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String description;

    private String duration;

    @Column(name="TOTAL_SEATS")
    private int totalSeats;

    @Column(name="AVAILABLE_SEATS")
    private int availableSeats;

    public Course(){}

    public Long getId(){ return id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description = description; }

    public String getDuration(){ return duration; }
    public void setDuration(String duration){ this.duration = duration; }

    public int getTotalSeats(){ return totalSeats; }
    public void setTotalSeats(int totalSeats){ this.totalSeats = totalSeats; }

    public int getAvailableSeats(){ return availableSeats; }
    public void setAvailableSeats(int availableSeats){ this.availableSeats = availableSeats; }
}