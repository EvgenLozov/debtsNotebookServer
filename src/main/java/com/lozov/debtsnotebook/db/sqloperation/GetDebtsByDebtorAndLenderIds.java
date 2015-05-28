package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-27.
 */
public class GetDebtsByDebtorAndLenderIds implements SqlOperation{

    private String debtorId;
    private String lenderId;

    public GetDebtsByDebtorAndLenderIds(String debtorId, String lenderId) {
        this.debtorId = debtorId;
        this.lenderId = lenderId;
    }

    @Override
    public String getRawSql() {
        return "SELECT * FROM debt where debtorId = ? and lenderId = ?";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, debtorId);
        statement.setString(2, lenderId);
    }
}
