package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;
import com.lozov.debtsnotebook.entity.Debt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-26.
 */
public class GetDebtorsSqlOperation implements SqlOperation {

    private String lenderId;

    public GetDebtorsSqlOperation(String lenderId) {
        this.lenderId = lenderId;
    }

    @Override
    public String getRawSql() {
        return "SELECT * FROM user where id in " +
                "(select debtorId from debt where lenderId = ? and status = ?)";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, lenderId);
        statement.setString(2, Debt.Status.OPEN.name());
    }
}
