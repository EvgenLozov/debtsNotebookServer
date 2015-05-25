package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.Debt;

import java.util.List;

/**
 * Created by Yevhen on 2015-05-24.
 */
public interface DebtRepository<T> {
    Debt create(Debt debt);
    Debt getById(T id);
    List<Debt> getDebts(T debtorId);
    List<Debt> getLoanedDebts(T lenderId);
    List<Debt> getDebts(T debtorId, T lenderId);
}
