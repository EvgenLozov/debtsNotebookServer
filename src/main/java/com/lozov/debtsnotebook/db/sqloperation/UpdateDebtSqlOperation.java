package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;
import com.lozov.debtsnotebook.entity.Debt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-29.
 */
public class UpdateDebtSqlOperation implements SqlOperation {

    private Debt debt;

    public UpdateDebtSqlOperation(Debt debt) {
        this.debt = debt;
    }

    @Override
    public String getRawSql() {
        return "UPDATE debt SET `status`= ?, `amountOfMoney`= ?, `desc`= ? WHERE `id`= ?";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, debt.getStatus().name());
        statement.setInt(2, debt.getAmountOfMoney());
        statement.setString(3, debt.getDesc());
        statement.setString(4, debt.getId());
    }
}
