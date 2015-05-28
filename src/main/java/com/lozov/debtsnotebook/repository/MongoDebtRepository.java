package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.Debt;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryImpl;
import org.mongodb.morphia.query.UpdateOpsImpl;

import java.util.List;

/**
 * Created by lozov on 12.05.15.
 */
public class MongoDebtRepository implements DebtRepository {
    public static final String FIELD_DEBTOR_ID = "debtorId";
    private static final String FIELD_LENDER_ID = "lenderId";

    private BasicDAO<Debt, String> basicDAO;

    public MongoDebtRepository(BasicDAO<Debt, String> basicDAO) {
        this.basicDAO = basicDAO;
    }

    @Override
    public Debt create(Debt debt) {
        String id = (String) basicDAO.save(debt).getId();
        debt.setId(id);
        
        return debt;
    }

    public Debt getById(String id){
        return basicDAO.findOne("_id", new ObjectId(id));
    }

    @Override
    public Debt update(Debt debt) {
        Query<Debt> updateQuery = new QueryImpl<>(Debt.class, basicDAO.getCollection(), basicDAO.getDatastore())
                .filter("_id", new ObjectId(debt.getId()));
        UpdateOpsImpl<Debt> updateOps = (UpdateOpsImpl<Debt>) basicDAO.createUpdateOperations()
                .set("desc", debt.getDesc())
                .set("amountOfMoney", debt.getAmountOfMoney())
                .set("status", debt.getStatus());
        basicDAO.update(updateQuery, updateOps);

        return debt;
    }

    public List<Debt> getDebts(String debtorId) {
        return basicDAO.find(new QueryImpl<>(Debt.class, basicDAO.getCollection(), basicDAO.getDatastore())
                .filter(FIELD_DEBTOR_ID, debtorId)).asList();
    }

    public List<Debt> getLoanedDebts(String lenderId) {
        return basicDAO.find(new QueryImpl<>(Debt.class, basicDAO.getCollection(), basicDAO.getDatastore())
                .filter(FIELD_LENDER_ID, lenderId)).asList();
    }

    public List<Debt> getDebts(String debtorId, String lenderId) {
        return basicDAO.getDatastore().createQuery(Debt.class)
                .field(FIELD_DEBTOR_ID).equal(debtorId)
                .field(FIELD_LENDER_ID).equal(lenderId).asList();
    }
}
