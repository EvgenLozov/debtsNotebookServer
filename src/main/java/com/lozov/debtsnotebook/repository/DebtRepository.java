package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.Debt;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.QueryImpl;

import java.util.List;

/**
 * Created by lozov on 12.05.15.
 */
public class DebtRepository extends BasicDAO<Debt, String> {
    public static final String FIELD_DEBTOR_ID = "debtorId";
    private static final String FIELD_BORROWER_ID = "borrowerId";

    public DebtRepository(Class<Debt> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public Debt getById(String id){
        return findOne("_id", new ObjectId(id));
    }

    public List<Debt> getDebts(String debtorId) {
        return find(new QueryImpl<Debt>(Debt.class, getCollection(), getDatastore())
                   .filter(FIELD_DEBTOR_ID, debtorId)).asList();
    }

    public List<Debt> getLoanedDebts(String borrowerId) {
        return find(new QueryImpl<Debt>(Debt.class, getCollection(), getDatastore())
                .filter(FIELD_BORROWER_ID, borrowerId)).asList();
    }

    public List<Debt> getDebts(String debtorId, String borrowerId) {
        return getDatastore().createQuery(Debt.class)
                .field(FIELD_DEBTOR_ID).equal(debtorId)
                .field(FIELD_BORROWER_ID).equal(borrowerId).asList();
    }
}
