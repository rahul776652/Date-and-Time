package com.dob.dob.Service;

import com.dob.dob.Domain.Student;
import com.dob.dob.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Student not found"));
    }

    public Student addStudent(Student student) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = now.format(formatter);
        student.setCreatedAt(LocalDateTime.parse(formattedDateTime));
        return studentRepository.save(student);
    }


    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = getStudentById(id);
        student.setName(updatedStudent.getName());
        student.setDob(updatedStudent.getDob());
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = LocalDateTime.now().format(formatter);
        student.setUpdatedAt(LocalDateTime.parse(formattedDateTime));
        return studentRepository.save(student);
    }


    public List<Student> getStudents(String name, LocalDate fromDate, LocalDate toDate) {
        LocalDateTime startOfDay = LocalDateTime.of(fromDate, LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(toDate, LocalTime.MAX);
        if (name == null && fromDate == null && toDate == null) {
            return studentRepository.findAll();
        } else if (name != null && fromDate == null && toDate == null) {
            return studentRepository.findByName(name);
        } else if (name == null && fromDate != null && toDate != null) {
            return studentRepository.findByCreatedAtBetween(startOfDay, endOfDay);
        } else {
            return studentRepository.findByNameAndCreatedAtBetween(name, startOfDay, endOfDay);
        }
    }




    public List<Student> getStudentsByCreatedDate(String fromDateString, String toDateString) {
        LocalDateTime fromDate = LocalDateTime.parse(fromDateString, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime toDate = LocalDateTime.parse(toDateString, DateTimeFormatter.ISO_DATE_TIME);

        return studentRepository.findByCreatedAtBetween(fromDate, toDate);
    }



}





