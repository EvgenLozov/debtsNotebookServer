package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-27.
 */
public class GetDebtsByLenderIdSqlOperation implements SqlOperation {

    private String lenderId;

    public GetDebtsByLenderIdSqlOperation(String lenderId) {
        this.lenderId = lenderId;
    }

    @Override
    public String getRawSql() {
        return "SELECT * FROM debt where lenderId = ?";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, lenderId);
    }
}
