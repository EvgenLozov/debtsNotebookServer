package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.db.JdbcService;
import com.lozov.debtsnotebook.db.resultsetextractor.DebtResultSetExtractor;
import com.lozov.debtsnotebook.db.resultsetextractor.DebtsResultSetExtractor;
import com.lozov.debtsnotebook.db.sqloperation.*;
import com.lozov.debtsnotebook.entity.Debt;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Yevhen on 2015-05-27.
 */
public class MySqlDebtRepository implements DebtRepository {

    private JdbcService jdbcService;

    public MySqlDebtRepository(JdbcService jdbcService) {
        this.jdbcService = jdbcService;
    }

    @Override
    public Debt create(Debt debt) {
        String id = UUID.randomUUID().toString();
        debt.setId(id);
        if (debt.getCreatedAt() == null)
            debt.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        int successFlag = 0;
        try {
            successFlag = jdbcService.executeUpdate(new CreateDebtSqlOperation(debt));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (successFlag > 0)
            return debt;

        return null;
    }

    @Override
    public Debt getById(String id) {
        try {
            return jdbcService.executeQuery(new GetDebtByIdSqlOperation(id),
                                            new DebtResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Debt update(Debt debt) {
        int successFlag = 0;
        try {
            successFlag = jdbcService.executeUpdate(new UpdateDebtSqlOperation(debt));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (successFlag > 0)
            return debt;

        return null;
    }

    @Override
    public List<Debt> getDebtsByDebtor(String debtorId) {
        try {
            return jdbcService.executeQuery(new GetDebtsByDebtorIdSqlOperation(debtorId),
                                            new DebtsResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Debt> getDebtsByLender(String lenderId) {
        try {
            return jdbcService.executeQuery(new GetDebtsByLenderIdSqlOperation(lenderId),
                                            new DebtsResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Debt> getDebts(String debtorId, String lenderId) {
        try {
            return jdbcService.executeQuery(new GetDebtsByDebtorAndLenderIds(debtorId, lenderId),
                                     new DebtsResultSetExtractor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
