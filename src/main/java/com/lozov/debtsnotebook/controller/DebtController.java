package com.lozov.debtsnotebook.controller;

import com.lozov.debtsnotebook.entity.Debt;
import com.lozov.debtsnotebook.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lozov on 12.05.15.
 */
@RestController
@RequestMapping("/user/{debtorId}/debt")
public class DebtController {

    public static final String PARAM_BORROWER_ID = "borrowerId";

    @Autowired
    private DebtRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Debt create(@PathVariable String debtorId, @RequestBody Debt debt){
        debt.setDebtorId(debtorId);
        String id = (String) repository.save(debt).getId();
        debt.setId(id);

        return debt;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Debt get(@PathVariable String id){
        return repository.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Debt> list(@PathVariable String debtorId){
        return repository.getDebts(debtorId);
    }

    @RequestMapping(method = RequestMethod.GET, params = PARAM_BORROWER_ID)
    public List<Debt> listForBorrower(@PathVariable String debtorId,
                                      @RequestParam(PARAM_BORROWER_ID) String borrowerId){

        return repository.getDebts(debtorId, borrowerId);
    }
}
