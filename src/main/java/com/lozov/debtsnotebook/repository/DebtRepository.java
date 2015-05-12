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
    public DebtRepository(Class<Debt> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public Debt getById(String id){
        return findOne("_id", new ObjectId(id));
    }

    public List<Debt> getDebts(String debtorId) {
        return find(new QueryImpl<Debt>(Debt.class, getCollection(), getDatastore())
                   .filter("debtorId", debtorId)).asList();
    }
}
