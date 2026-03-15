package com.example.studentapp.dto;

public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private String duration;
    private int availableSeats;

    public CourseDTO(Long id,String name,String description,String duration,int availableSeats){
        this.id=id;
        this.name=name;
        this.description=description;
        this.duration=duration;
        this.availableSeats=availableSeats;
    }

    public Long getId(){ return id; }
    public String getName(){ return name; }
    public String getDescription(){ return description; }
    public String getDuration(){ return duration; }
    public int getAvailableSeats(){ return availableSeats; }
}