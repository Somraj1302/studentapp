package com.student.management.controller;

import com.student.management.entity.Student;
import com.student.management.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Display Registration Form
    @GetMapping("/")
    public String showForm(Model model) {

        model.addAttribute("student", new Student());

        model.addAttribute(
            "students",
            studentService.getAllStudents()
        );

        return "students";
    }

    // Save Student Details
    @PostMapping("/save")
    public String saveStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult result,
            Model model) {

        if (student.getEmail() != null) {
            student.setEmail(student.getEmail().trim().toLowerCase());

            if (studentService.emailExists(student.getEmail())) {
                result.rejectValue("email", "duplicate.email", "This email is already registered");
            }
        }

        if (result.hasErrors()) {

            model.addAttribute(
                "students",
                studentService.getAllStudents()
            );

            return "students";
        }

        studentService.saveStudent(student);

        return "redirect:/";
    }

    // Delete Student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/";
    }
}
