package com.epic.studentmanagementsystemepicdemo.service;

import com.epic.studentmanagementsystemepicdemo.dto.StudentRequestDTO;
import com.epic.studentmanagementsystemepicdemo.exception.DuplicateEmailException;
import com.epic.studentmanagementsystemepicdemo.model.Student;
import com.epic.studentmanagementsystemepicdemo.repository.EnrollmentRepo;
import com.epic.studentmanagementsystemepicdemo.repository.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepository;
    private final EnrollmentRepo enrollmentRepo;

    public StudentService(StudentRepo studentRepository, EnrollmentRepo enrollmentRepo) {
        this.studentRepository = studentRepository;
        this.enrollmentRepo = enrollmentRepo;
    }

    private Date toDate(LocalDate localDate) {
        if (localDate == null) return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public void createStudent(StudentRequestDTO request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());

        // ✅ convert LocalDate -> java.util.Date (Student model uses Date)
        student.setDateOfBirth(toDate(request.getDateOfBirth()));
        student.setEnrollmentDate(toDate(request.getEnrollmentDate()));

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

    @Transactional
    public void updateStudent(Integer id, StudentRequestDTO request) {

        // ✅ Email duplicate check (same logic as create)
        if (studentRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new DuplicateEmailException(
                    "Email " + request.getEmail() + " is already in use by another student."
            );
        }

        // ✅ update Student table
        Student student = new Student();
        student.setId(id);
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setDateOfBirth(toDate(request.getDateOfBirth()));
        student.setEnrollmentDate(toDate(request.getEnrollmentDate()));

        studentRepository.update(student);

        // ✅ update join table Student_Course
        enrollmentRepo.removeEnrollmentsByStudent(id);

        if (request.getCourseIds() != null) {
            for (Integer courseId : request.getCourseIds()) {
                if (courseId == null) continue;
                enrollmentRepo.enrollStudent(id, courseId);
            }
        }
    }

    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    public List<Student> findAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void deleteStudentById(int studentId) {
        // ✅ remove all enrollments first (new repo method)
        enrollmentRepo.removeEnrollmentsByStudent(studentId);
        studentRepository.delete(studentId);
    }
}