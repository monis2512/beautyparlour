package com.myparlour.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "customer_queries")
public class CustomerQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String name;

    @NotBlank
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Please enter a valid 10-digit Indian mobile number.")
    private String mobileNumber;

    @NotBlank private String service;
    @NotBlank private String question;

    @Size(max = 2000)
    private String description;

    private LocalDateTime createdAt;

    @PrePersist
    void created() {
        createdAt = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String v) { name = v; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String v) { mobileNumber = v; }
    public String getService() { return service; }
    public void setService(String v) { service = v; }
    public String getQuestion() { return question; }
    public void setQuestion(String v) { question = v; }
    public String getDescription() { return description; }
    public void setDescription(String v) { description = v; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

