package com.college.registration.controller;

import com.college.registration.entities.Student;
import com.college.registration.entities.StudentEnrollment;
import com.college.registration.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/school-registeration")
public class SchoolRegistrationProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolRegistrationProcess.class);

    @Autowired
    StudentService studentService;

    // This API used to Enroll a student with the Course Details into the DB
    @PostMapping(path = "/add-student", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public StudentEnrollment createNewStudent(@RequestBody StudentEnrollment student) {
        LOGGER.info("The Student to be added {} ", student);
        return studentService.enrollStudent(student);
    }

    // This API to be invoked to delete the student info from the DB.
    @DeleteMapping(path = "/delete-student" )
    public void deleteStudent(@RequestBody Long id) {
        LOGGER.info("Deleting the student {}", id);
        studentService.deleteStudent(id);
    }

    // This API used to fetch the Students enrolled for the course.
    @GetMapping(path = "/students-from-coursename/{courseName}" )
    public List<Student> getStudentsbyName(@PathVariable("courseName") String courseName) {
        LOGGER.info("Fetching all the students {}", courseName);
        return studentService.findAllStudentsByCourseName(courseName);
    }
}
