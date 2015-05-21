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
    private static final String FIELD_LENDER_ID = "lenderId";

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

    public List<Debt> getLoanedDebts(String lenderId) {
        return find(new QueryImpl<Debt>(Debt.class, getCollection(), getDatastore())
                .filter(FIELD_LENDER_ID, lenderId)).asList();
    }

    public List<Debt> getDebts(String debtorId, String lenderId) {
        return getDatastore().createQuery(Debt.class)
                .field(FIELD_DEBTOR_ID).equal(debtorId)
                .field(FIELD_LENDER_ID).equal(lenderId).asList();
    }
}
