/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.exception.DuplicateEmailException;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.repository.impl.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


//this class is using for student service (CRUD operations)
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        // Check if email already exists
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new DuplicateEmailException("Email " + student.getEmail() + " is already in use.");
        }
        studentRepository.saveStudent(student);
    }

    public void updateStudent(Integer id, Student student) {
        // Check if email already exists for another student
        if (studentRepository.existsByEmailAndIdNot(student.getEmail(), id)) {
            throw new DuplicateEmailException("Email " + student.getEmail() + " is already in use by another student.");
        }
        student.setId(id);
        studentRepository.update(student);
    }

    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    public List<Student> findAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void deleteStudentById(int studentId) {
        studentRepository.delete(studentId);
    }




}
