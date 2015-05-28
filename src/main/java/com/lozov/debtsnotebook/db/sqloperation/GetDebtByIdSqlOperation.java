package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-27.
 */
public class GetDebtByIdSqlOperation implements SqlOperation {

    private String debtId;

    public GetDebtByIdSqlOperation(String debtId) {
        this.debtId = debtId;
    }

    @Override
    public String getRawSql() {
        return "SELECT * FROM debt where id = ?";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, debtId);
    }
}
