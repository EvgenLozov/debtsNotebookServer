package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-25.
 */
public class LoginSqlOperation implements SqlOperation {

    private String username;
    private String password;

    public LoginSqlOperation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getRawSql() {
        return "SELECT * FROM user where username = ? and password = ?;";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, username);
        statement.setString(2, password);
    }
}
