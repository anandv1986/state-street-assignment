package com.college.registration.dao;

import com.college.registration.entities.StudentEnrolledCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StudentEnrolledCourseDAO extends JpaRepository<StudentEnrolledCourses, Long> {
}