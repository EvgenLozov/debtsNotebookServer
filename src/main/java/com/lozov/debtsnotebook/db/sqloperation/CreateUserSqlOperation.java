package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;
import com.lozov.debtsnotebook.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-25.
 */
public class CreateUserSqlOperation implements SqlOperation {

    private User user;

    public CreateUserSqlOperation(User user) {
        this.user = user;
    }

    @Override
    public String getRawSql() {
        return "INSERT INTO user (`id`, `username`, `password`, `email`) VALUES (?, ?, ?, ?)";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getId());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getEmail());
    }
}
