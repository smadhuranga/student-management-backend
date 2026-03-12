package com.epic.studentmanagementsystemepicdemo.service.Impl;

import com.epic.studentmanagementsystemepicdemo.dto.StudentRequestDTO;
import com.epic.studentmanagementsystemepicdemo.exception.DuplicateEmailException;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.repository.EnrollmentRepo;
import com.epic.studentmanagementsystemepicdemo.repository.StudentRepo;
import com.epic.studentmanagementsystemepicdemo.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepository;
    private final EnrollmentRepo enrollmentRepo;

    public StudentServiceImpl(StudentRepo studentRepository,
                              EnrollmentRepo enrollmentRepo) {
        this.studentRepository = studentRepository;
        this.enrollmentRepo = enrollmentRepo;
    }

    @Override
    @Transactional
    public void createStudent(StudentRequestDTO request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());


        student.setDateOfBirth(request.getDateOfBirth());
        student.setEnrollmentDate(request.getEnrollmentDate());

        int studentId = studentRepository.saveStudent(student);

        if (request.getCourseIds() != null) {
            for (Integer courseId : request.getCourseIds()) {

                if (courseId == null) continue;

                if (!enrollmentRepo.existsEnrollment(studentId, courseId)) {
                    enrollmentRepo.enrollStudent(studentId, courseId);
                }
            }
        }
    }

    @Override
    @Transactional
    public void updateStudent(Integer id, StudentRequestDTO request) {

        if (studentRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new DuplicateEmailException(
                    "Email " + request.getEmail() + " is already in use by another student."
            );
        }

        Student student = new Student();
        student.setId(id);
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());


        student.setDateOfBirth(request.getDateOfBirth());
        student.setEnrollmentDate(request.getEnrollmentDate());

        studentRepository.update(student);

        // remove previous enrollments
        enrollmentRepo.removeEnrollmentsByStudent(id);

        // re-add selected courses
        if (request.getCourseIds() != null) {
            for (Integer courseId : request.getCourseIds()) {

                if (courseId == null) continue;

                if (!enrollmentRepo.existsEnrollment(id, courseId)) {
                    enrollmentRepo.enrollStudent(id, courseId);
                }
            }
        }
    }

    @Override
    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    @Transactional
    public void deleteStudentById(int studentId) {

        // remove enrollments first
        enrollmentRepo.removeEnrollmentsByStudent(studentId);

        // delete student
        studentRepository.delete(studentId);
    }
}