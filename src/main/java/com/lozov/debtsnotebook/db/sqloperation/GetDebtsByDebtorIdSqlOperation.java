package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-27.
 */
public class GetDebtsByDebtorIdSqlOperation implements SqlOperation {

    private String debtorId;

    public GetDebtsByDebtorIdSqlOperation(String debtorId) {
        this.debtorId = debtorId;
    }

    @Override
    public String getRawSql() {
        return "SELECT * FROM debt where debtorId = ?";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, debtorId);
    }
}
