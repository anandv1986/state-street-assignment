package com.college.registration.dao;

import com.college.registration.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseDAO extends JpaRepository<Course, Long> {
    Course findCourseById(Long courseId);
}
