/**
 * @author supunmadhuranga
 * @created 2026-02-20
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.exception;

public class ResourceNotFoundException extends RuntimeException {
    // this class extends RuntimeException it means it is a checked exception
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

}
