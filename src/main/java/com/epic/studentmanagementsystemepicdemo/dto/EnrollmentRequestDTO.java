package com.epic.studentmanagementsystemepicdemo.dto;



public class EnrollmentRequestDTO {
    private int studentId;
    private int courseId;

    public EnrollmentRequestDTO() {
    }

    public EnrollmentRequestDTO(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}