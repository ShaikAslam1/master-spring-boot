package com.aslam.masterspringboot.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Collection<Student> findByFirstnameContaining(String firstname);
}
