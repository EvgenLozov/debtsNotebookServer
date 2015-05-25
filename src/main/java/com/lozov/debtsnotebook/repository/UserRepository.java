package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Yevhen on 2015-05-24.
 */
public interface UserRepository {
    User getById(String id);
    User get(String username, String password);
    List<User> get(Set<String> userIds);
    User create(User user);
    List<User> list();
}
