package com.college.registration.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@Table(name = "STUDENT_ENROLLED_COURSES")
@IdClass(StudentEnrolledCourses.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEnrolledCourses implements Serializable {
    private static final long serialVersionUID = 1322L;

    @Id
    @Column(name = "STUDENT_ID", nullable = false)
    private Long studentId;

    @Id
    @Column(name = "COURSE_ID", nullable = false)
    private Long courseId;

    @Column(name = "MARKS")
    private BigDecimal marks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentEnrolledCourses)) return false;
        StudentEnrolledCourses that = (StudentEnrolledCourses) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
