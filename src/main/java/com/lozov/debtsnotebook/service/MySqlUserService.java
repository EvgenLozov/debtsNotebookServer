package com.lozov.debtsnotebook.service;

import com.lozov.debtsnotebook.entity.User;

import java.util.List;

/**
 * Created by Yevhen on 2015-05-25.
 */
public class MySqlUserService implements UserService {
    @Override
    public List<User> getLenders(String userId) {
        return null;
    }

    @Override
    public List<User> getDebtors(String userId) {
        return null;
    }
}
