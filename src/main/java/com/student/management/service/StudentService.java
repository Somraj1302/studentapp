package com.student.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.management.entity.Student;
import com.student.management.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Save Student
    public Student saveStudent(Student student) {

        return studentRepository.save(student);
    }

    public boolean emailExists(String email) {

        if (email == null) {
            return false;
        }

        return studentRepository.existsByEmailIgnoreCase(email.trim());
    }

    // Get All Students
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    // Delete Student
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }
}