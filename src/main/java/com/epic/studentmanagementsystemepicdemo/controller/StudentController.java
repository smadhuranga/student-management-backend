/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.controller;

import com.epic.studentmanagementsystemepicdemo.dto.StudentRequestDTO;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // create student
    @PostMapping
    public String createStudent(@RequestBody StudentRequestDTO request) {

        studentService.createStudent(request);

        return "Student created and enrolled successfully";

    }

    // get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    // get student by id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.findStudentById(id);
    }

    // update student by id
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody StudentRequestDTO request) {
        studentService.updateStudent(id, request);
        return "Student updated successfully";
    }

    // delete student by id
    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable int id) {
        studentService.deleteStudentById(id);
        return "Student Delete Successfully";
    }
}
