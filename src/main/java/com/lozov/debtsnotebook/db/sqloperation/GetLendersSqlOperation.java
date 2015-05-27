package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-26.
 */
public class GetLendersSqlOperation implements SqlOperation {

    private String debtorId;

    public GetLendersSqlOperation(String debtorId) {
        this.debtorId = debtorId;
    }

    @Override
    public String getRawSql() {
        return "SELECT * FROM user where id in (select lenderId from debt where debtorId = ?)";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, debtorId);
    }
}
