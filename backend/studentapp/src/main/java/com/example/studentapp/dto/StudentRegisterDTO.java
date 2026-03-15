package com.example.studentapp.dto;

import jakarta.validation.constraints.*;

public class StudentRegisterDTO {

    @NotBlank(message="Name is required")
    @Pattern(regexp="^[A-Za-z ]+$", message="Name must contain only alphabets")
    private String name;

    @NotBlank(message="Email is required")
    @Email(message="Invalid email format")
    private String email;

    @NotBlank(message="Password is required")
    @Size(min=6, message="Password must be at least 6 characters")
    @Pattern(regexp="^[A-Za-z0-9]+$", message="Password must contain only letters and numbers")
    private String password;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}