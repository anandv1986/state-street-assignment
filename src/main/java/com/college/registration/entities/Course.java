package com.college.registration.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 *  This is a Course Entity class contains the course details to be persisted into DB.
 *
 */
@Entity
@Data
@Table(name = "COURSE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course implements Serializable {

    private static final long serialVersionUID = 1322L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long id;

    @NaturalId
    @Column(name = "COURSE_NAME")
    private String courseName;

    private transient BigDecimal marks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        if (id == null && courseName != null)
            return Objects.equals(courseName, course.courseName);

        return Objects.equals(id, course.id) && Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        if (null == id && courseName != null)
            return Objects.hash(courseName);
        return Objects.hash(id);
    }
}
