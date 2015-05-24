package com.lozov.debtsnotebook.service;

import com.lozov.debtsnotebook.entity.Debt;
import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.repository.DebtRepository;
import com.lozov.debtsnotebook.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lozov on 18.05.15.
 */
public class UserService {
    private UserRepository userRepository;
    private DebtRepository debtRepository;

    public UserService(UserRepository userRepository, DebtRepository debtRepository) {
        this.userRepository = userRepository;
        this.debtRepository = debtRepository;
    }

    public List<User> getLenders(String userId){
        List<Debt> debts = debtRepository.getDebts(userId);

        Set<String> lendersIds = new HashSet<>();

        for (Debt debt : debts) {
            lendersIds.add(debt.getLenderId());
        }

        return userRepository.get(lendersIds);
    }

    public List<User> getDebtors(String userId) {
        List<Debt> debts = debtRepository.getLoanedDebts(userId);

        Set<String> debtorIds = new HashSet<>();
        for (Debt debt : debts) {
            debtorIds.add(debt.getDebtorId());
        }

        return userRepository.get(debtorIds);
    }
}

