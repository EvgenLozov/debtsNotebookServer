package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.Debt;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
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
    private static final String FIELD_DATE = "date";
    private static final String FIELD_STATUS = "status";

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
                .set("status", debt.getStatus())
                .set("lastModified", debt.getLastModified());
        basicDAO.update(updateQuery, updateOps);

        return debt;
    }

    public List<Debt> getDebtsByDebtor(String debtorId) {
        return basicDAO.getDatastore().createQuery(Debt.class)
                .filter(FIELD_DEBTOR_ID, debtorId)
                .filter(FIELD_STATUS, Debt.Status.OPEN).asList();
    }

    public List<Debt> getDebtsByLender(String lenderId) {
        return basicDAO.getDatastore().createQuery(Debt.class)
                .filter(FIELD_LENDER_ID, lenderId)
                .filter(FIELD_STATUS, Debt.Status.OPEN).asList();
    }

    public List<Debt> getDebts(String debtorId, String lenderId) {
        return basicDAO.getDatastore().createQuery(Debt.class)
                .field(FIELD_DEBTOR_ID).equal(debtorId)
                .field(FIELD_LENDER_ID).equal(lenderId)
                .filter(FIELD_STATUS, Debt.Status.OPEN).asList();
    }
}
