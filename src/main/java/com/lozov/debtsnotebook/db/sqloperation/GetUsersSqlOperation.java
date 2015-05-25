package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-25.
 */
public class GetUsersSqlOperation implements SqlOperation {
    @Override
    public String getRawSql() {
        return "SELECT * FROM user";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {

    }
}
