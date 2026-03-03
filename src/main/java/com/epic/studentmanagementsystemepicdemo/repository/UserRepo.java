/**
 * @author supunmadhuranga
 * @created 2026-03-03
 * @project StudentManagementSystemEpicdemo
 */

package com.epic.studentmanagementsystemepicdemo.repository;

import com.epic.studentmanagementsystemepicdemo.model.User;

public interface UserRepo {
    User findByUsername(String username);

    int saveUser(User user);
}
