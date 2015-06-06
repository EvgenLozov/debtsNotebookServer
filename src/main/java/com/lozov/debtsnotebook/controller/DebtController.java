package com.lozov.debtsnotebook.controller;

import com.lozov.debtsnotebook.entity.Debt;
import com.lozov.debtsnotebook.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lozov on 12.05.15.
 */
@RestController
@RequestMapping("/user/{debtorId}/debt")
public class DebtController {

    public static final String PARAM_LENDER_ID = "lenderId";
    private static final String PARAM_STATUS = "status";

    @Autowired
    private DebtRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Debt create(@PathVariable String debtorId, @RequestBody Debt debt){
        debt.setDebtorId(debtorId);
        debt.setDate(new Date());

        return repository.create(debt);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Debt get(@PathVariable String id){
        return repository.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Debt update(@PathVariable String id, @RequestBody Debt debt){
        return repository.update(debt);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Debt> list(@PathVariable String debtorId){
        return repository.getDebtsByDebtor(debtorId);
    }

    @RequestMapping(method = RequestMethod.GET, params = PARAM_LENDER_ID)
    public List<Debt> debtsToLender(@PathVariable String debtorId,
                                    @RequestParam(PARAM_LENDER_ID) String lenderId,
                                    @RequestParam(PARAM_STATUS) String status){
        List<Debt> debts = repository.getDebts(debtorId, lenderId);
        Debt.Status debtStatus = Debt.Status.valueOf(status);
        Iterator<Debt> iterator = debts.iterator();
        while (iterator.hasNext()){
            if(!iterator.next().getStatus().equals(debtStatus))
                iterator.remove();
        }
        return debts;
    }
}
