package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;
import com.lozov.debtsnotebook.entity.Debt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-27.
 */
public class CreateDebtSqlOperation implements SqlOperation{

    private Debt debt;

    public CreateDebtSqlOperation(Debt debt) {
        this.debt = debt;
    }

    @Override
    public String getRawSql() {
        return "INSERT INTO `debts`.`debt` (`id`, `debtorId`, `lenderId`, `status`, `amountOfMoney`, `desc`, `date`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        statement.setString(1, debt.getId());
        statement.setString(2, debt.getDebtorId());
        statement.setString(3, debt.getLenderId());
        statement.setString(4, debt.getStatus().name());
        statement.setInt(5, debt.getAmountOfMoney());
        statement.setString(5, debt.getDesc());
        statement.setDate(7, debt.getDate());
    }
}
