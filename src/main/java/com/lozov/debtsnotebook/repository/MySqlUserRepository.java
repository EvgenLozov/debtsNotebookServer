package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.db.JdbcService;
import com.lozov.debtsnotebook.db.resultsetextractor.UserResultSetExtractor;
import com.lozov.debtsnotebook.db.resultsetextractor.UsersResultSetExtractor;
import com.lozov.debtsnotebook.db.sqloperation.*;
import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.service.UserService;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Yevhen on 2015-05-25.
 */
public class MySqlUserRepository implements UserRepository, UserService {

    private JdbcService jdbcService;

    public MySqlUserRepository(JdbcService jdbcService) {
        this.jdbcService = jdbcService;
    }

    @Override
    public User getById(String id) {
        User user = null;
        try {
            user = jdbcService.executeQuery(new GetUserByIdSqlOperation(id), new UserResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User get(String username, String password) {
        try {
            return jdbcService.executeQuery(new LoginSqlOperation(username, password), new UserResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> get(Set<String> userIds) {
        try {
            return jdbcService.executeQuery(new GetUsersByIdsSqlOperation(userIds), new UsersResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public User create(User user) {
        user.setId(UUID.randomUUID().toString());
        int successFlag = 0;
        try {
            successFlag = jdbcService.executeUpdate(new CreateUserSqlOperation(user));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (successFlag > 0)
            return user;

        return null;
    }

    @Override
    public List<User> list() {
        try {
            return jdbcService.executeQuery(new GetUsersSqlOperation(), new UsersResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<User> getLenders(String debtorId) {
        try {
            return jdbcService.executeQuery(new GetLendersSqlOperation(debtorId), new UsersResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<User> getDebtors(String lenderId) {
        try {
            return jdbcService.executeQuery(new GetDebtorsSqlOperation(lenderId), new UsersResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
