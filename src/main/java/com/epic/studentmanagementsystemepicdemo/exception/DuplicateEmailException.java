/**
 * @author supunmadhuranga
 * @created 2026-03-03
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}