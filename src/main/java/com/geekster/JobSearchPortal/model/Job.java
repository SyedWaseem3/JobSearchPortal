package com.geekster.JobSearchPortal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JOBS")
public class Job {
    @Id
    private Long jobId;
    @NotBlank(message = "Job title is required")
    private String jobTitle;
    @NotBlank(message = "Job description is required")
    private String jobDescription;
    @Min(value = 20000, message = "Salary must be at least 20,000")
    private Double jobSalary;
    @NotBlank(message = "Company email is required")
    @Email(message = "Invalid email format")
    private String jobCompanyEmail;
    @NotBlank(message = "Company name is required")
    private String jobCompanyName;
    @NotBlank(message = "Employer name is required")
    private String jobEmployerName;
    @Enumerated(EnumType.STRING)
    private Type jobType;
    @PastOrPresent(message = "Applied date must be in the past or present")
    private LocalDate jobAppliedDate;
}
