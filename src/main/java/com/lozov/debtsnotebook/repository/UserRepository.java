package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.QueryImpl;

/**
 * Created by lozov on 12.05.15.
 */

public class UserRepository extends BasicDAO<User, String> {
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_PASSWORD = "password";

    public UserRepository(Class<User> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public User getById(String id) {
        return findOne("_id", new ObjectId(id));
    }

    public User get(String username, String password) {
        return findOne(new QueryImpl<>(User.class, getCollection(), getDatastore())
                        .filter(FIELD_USERNAME, username).filter(FIELD_PASSWORD, password));
    }
}
