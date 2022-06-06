package com.college.registration.service.impl;

import com.college.registration.dao.CourseDAO;
import com.college.registration.dao.StudentDAO;
import com.college.registration.dao.StudentEnrolledCourseDAO;
import com.college.registration.entities.*;
import com.college.registration.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *  This Service class invokes the DAO calls to perform the DB operations.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    public static final String FIND_STUDENTS_BY_COURSE = "SELECT * FROM STUDENT s WHERE s.STUDENT_ID IN (SELECT ER.STUDENT_ID FROM STUDENT_ENROLLED_COURSES ER \n" +
            "INNER JOIN COURSE C ON ER.COURSE_ID  = C.COURSE_ID \n" +
            "WHERE C.COURSE_NAME = :courseName) order by student_name";

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudentEnrolledCourseDAO studentEnrolledCourseDAO;

    @PersistenceContext
    EntityManager em;

    @Override
    public Student findStudentById(Long studentId) {
        return studentDAO.findStudentById(studentId);
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentDAO.deleteById(studentId);
    }

    @Override
    public Student addStudent(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public StudentEnrollment enrollStudent(StudentEnrollment studentEnrollment) {
        try {
            StudentEnrolledCourses enrolledCourses = new StudentEnrolledCourses();
            Student student = new Student(studentEnrollment.getStudentName());

            log.info("The student object has been persisted {} ", studentDAO.save(student));
            for (Course course : studentEnrollment.getCourses()) {
                Course courseByName = findCourseByName(course.getCourseName());
                if (courseByName == null)
                    courseByName = courseDAO.save(course);
                log.info("The course object has been persisted {} ", courseByName);
                enrolledCourses.setStudentId(student.getId());
                enrolledCourses.setCourseId(courseByName.getId());
                enrolledCourses.setMarks(course.getMarks());
                studentEnrolledCourseDAO.save(enrolledCourses);
                log.info("The final student object has been persisted {} ", studentDAO.save(student));
            }
        } catch (Exception e) {
            log.error("Error occured while persisting Student Enrollment service ", e);
        }
        return studentEnrollment;
    }

    @Override
    public void updateStudent(Student student) {
        studentDAO.save(student);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDAO.findAll();
    }

    @Override
    public List<Student> findAllStudentsByCourseName(String courseName) {
        List<Student> q = em.createNativeQuery(FIND_STUDENTS_BY_COURSE).setParameter("courseName", courseName).getResultList();
        return q;
    }

    @Override
    public Course findCourseByName(final String courseName) {
        Course course = courseDAO.findAll().stream().filter(c -> c.getCourseName().equalsIgnoreCase(courseName)).findAny().orElse(null);

        if (course != null) {
            return course;
        }
        return null;
    }
}
