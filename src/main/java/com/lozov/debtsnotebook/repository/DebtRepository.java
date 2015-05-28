package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.Debt;

import java.util.List;

/**
 * Created by Yevhen on 2015-05-24.
 */
public interface DebtRepository {
    Debt create(Debt debt);
    Debt getById(String id);
    Debt update(Debt debt);
    List<Debt> getDebts(String debtorId);
    List<Debt> getLoanedDebts(String lenderId);
    List<Debt> getDebts(String debtorId, String lenderId);
}
