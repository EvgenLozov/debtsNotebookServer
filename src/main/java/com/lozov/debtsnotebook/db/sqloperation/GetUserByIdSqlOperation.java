package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-25.
 */
public class GetUserByIdSqlOperation implements SqlOperation {

    private String userId;

    public GetUserByIdSqlOperation(String userId) {
        this.userId = userId;
    }

    @Override
    public String getRawSql() {
        return "SELECT * FROM user where id = ?";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, userId);
    }
}
