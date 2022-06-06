package com.college.registration.service;

import com.college.registration.entities.Course;
import com.college.registration.entities.Student;
import com.college.registration.entities.StudentEnrollment;

import java.util.List;

public interface StudentService {
    Student findStudentById(Long studentId);
    void deleteStudent(Long studentId);
    Student addStudent(Student student);

    StudentEnrollment enrollStudent(StudentEnrollment studentEnrollment);

    void updateStudent(Student student);

    List<Student> findAllStudents();

    List<Student> findAllStudentsByCourseName(String courseName);

    Course findCourseByName(String courseName);
}
