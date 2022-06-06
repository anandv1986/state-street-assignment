package com.college.registration.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class StudentCourseCompositeKey implements Serializable {

    private static final long serialVersionUID = 1322L;

    @Column(name = "STUDENT_ID", nullable = false)
    private Long studentId;

    @Column(name = "COURSE_ID", nullable = false)
    private Long courseId;

    public StudentCourseCompositeKey(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCourseCompositeKey)) return false;
        StudentCourseCompositeKey that = (StudentCourseCompositeKey) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
