package com.dob.dob.Repository;

import com.dob.dob.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findByName(String name);
    List<Student> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

     List<Student> findByNameAndCreatedAtBetween(String name, LocalDateTime fromDate, LocalDateTime toDate);

    List<Student> findByCreatedAt(LocalDateTime createdDate);

}
