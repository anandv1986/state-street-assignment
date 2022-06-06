package com.college.registration.service;

import com.college.registration.entities.Course;
import com.college.registration.entities.StudentEnrollment;
import com.college.registration.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@Import(StudentServiceImpl.class)
public class StudentServiceImplTest {

    @Autowired
    private StudentServiceImpl studentService;

    @Test
    public void enrollStudentTest() {

        Course course = Course
                .builder()
                .courseName("J2EE")
                .marks(new BigDecimal(99.22))
                .build();
        Set<Course> courses = new HashSet<>();
        courses.add(course);
        StudentEnrollment studentEnrollment = StudentEnrollment.builder()
                .studentName("Andy")
                .courses(courses).build();

        StudentEnrollment result = studentService.enrollStudent(studentEnrollment);

        Assertions.assertTrue(result.getCourses().size() > 0);

        Assertions.assertNotNull(result.getCourses().stream().findAny().get().getId());
    }

}
