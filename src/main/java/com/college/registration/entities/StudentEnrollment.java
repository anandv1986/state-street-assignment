package com.college.registration.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEnrollment {

    private Integer studentId;

    private String studentName;

    private Set<Course> courses;
}
