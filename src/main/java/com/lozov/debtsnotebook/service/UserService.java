package com.lozov.debtsnotebook.service;

import com.lozov.debtsnotebook.entity.User;

import java.util.List;

/**
 * Created by Yevhen on 2015-05-25.
 */
public interface UserService {
    List<User> getLenders(String userId);
    List<User> getDebtors(String userId);
}
