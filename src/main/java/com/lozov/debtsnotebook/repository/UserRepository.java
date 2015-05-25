package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Yevhen on 2015-05-24.
 */
public interface UserRepository<T> {
    User getById(T id);
    User get(String username, String password);
    List<User> get(Set<T> userIds);
    User create(User user);
    List<User> list();
}
