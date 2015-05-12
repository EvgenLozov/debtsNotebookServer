package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * Created by lozov on 12.05.15.
 */

public class UserRepository extends BasicDAO<User, String> {
    public UserRepository(Class<User> entityClass, Datastore ds) {
        super(entityClass, ds);
    }

    public User getById(String id) {
        return findOne("_id", new ObjectId(id));
    }

}
