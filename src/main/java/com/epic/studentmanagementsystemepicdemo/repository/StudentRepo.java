/**
 * @author supunmadhuranga
 * @created 2026-03-03
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.repository;

import com.epic.studentmanagementsystemepicdemo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StudentRepo {
    int saveStudent(Student s);

    List<Student> getAllStudents();

    Student findById(int id);

    int update(Student s);

    int delete(int id);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, int id);
}
