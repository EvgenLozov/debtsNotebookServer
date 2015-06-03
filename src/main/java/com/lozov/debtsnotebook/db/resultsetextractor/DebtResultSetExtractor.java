package com.lozov.debtsnotebook.db.resultsetextractor;

import com.lozov.debtsnotebook.db.ResultSetExtractor;
import com.lozov.debtsnotebook.entity.Debt;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-27.
 */
public class DebtResultSetExtractor implements ResultSetExtractor<Debt> {
    @Override
    public Debt read(ResultSet resultSet) throws SQLException {
        Debt debt = null;
        if (resultSet.next()){
            debt = new Debt();
            debt.setId(resultSet.getString(1));
            debt.setDebtorId(resultSet.getString(2));
            debt.setLenderId(resultSet.getString(3));
            debt.setStatus(Debt.Status.valueOf(resultSet.getString(4)));
            debt.setAmountOfMoney(resultSet.getInt(5));
            debt.setDesc(resultSet.getString(6));
            debt.setDate(resultSet.getTimestamp(7));
        }
        return debt;
    }
}
