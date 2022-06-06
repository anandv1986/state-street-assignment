package com.college.registration.dao;

import com.college.registration.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StudentDAO extends JpaRepository<Student, Long> {
    Student findStudentById(Long studentId);
}
