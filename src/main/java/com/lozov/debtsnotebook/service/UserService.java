package com.lozov.debtsnotebook.service;

import com.lozov.debtsnotebook.entity.Debt;
import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.repository.DebtRepository;
import com.lozov.debtsnotebook.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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

    public List<User> getBorrowers(String debtorId){
        List<Debt> debts = debtRepository.getDebts(debtorId);

        List<String> borrowerIds = new ArrayList<String>();

        for (Debt debt : debts) {
            borrowerIds.add(debt.getBorrowerId());
        }

        return userRepository.get(borrowerIds);
    }
}

