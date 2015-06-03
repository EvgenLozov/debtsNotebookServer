package com.lozov.debtsnotebook.db.resultsetextractor;

import com.lozov.debtsnotebook.db.ResultSetExtractor;
import com.lozov.debtsnotebook.entity.Debt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 2015-05-27.
 */
public class DebtsResultSetExtractor implements ResultSetExtractor<List<Debt>> {
    @Override
    public List<Debt> read(ResultSet resultSet) throws SQLException {
        List<Debt> debts = new ArrayList<>();
        while (resultSet.next()){
            Debt debt = new Debt();
            debt.setId(resultSet.getString(1));
            debt.setDebtorId(resultSet.getString(2));
            debt.setLenderId(resultSet.getString(3));
            debt.setStatus(Debt.Status.valueOf(resultSet.getString(4)));
            debt.setAmountOfMoney(resultSet.getInt(5));
            debt.setDesc(resultSet.getString(6));
            debt.setDate(resultSet.getTimestamp(7));
            debts.add(debt);
        }
        return debts;
    }
}
