/**
 * @author supunmadhuranga
 * @created 2026-02-19
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.service;

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
        studentRepository.saveStudent(student);
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

    public void updateStudent(Integer id, Student student) {
        student.setId(id);
        studentRepository.update(student);
    }


}
