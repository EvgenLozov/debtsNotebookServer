package com.lozov.debtsnotebook.repository;

import com.lozov.debtsnotebook.entity.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.QueryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lozov on 12.05.15.
 */

public class MongoUserRepository implements UserRepository<String>{
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_ID = "_id";

    private BasicDAO<User, String> basicDAO;

    public MongoUserRepository(BasicDAO<User, String> basicDAO) {
        this.basicDAO = basicDAO;
    }

    public User getById(String id) {
        return basicDAO.findOne("_id", new ObjectId(id));
    }

    public User get(String username, String password) {
        return basicDAO.findOne(new QueryImpl<>(User.class, basicDAO.getCollection(), basicDAO.getDatastore())
                .filter(FIELD_USERNAME, username).filter(FIELD_PASSWORD, password));
    }

    public List<User> get(Set<String> userIds) {
        if(userIds == null || userIds.isEmpty())
            return new ArrayList<>();

        return basicDAO.find(new QueryImpl<>(User.class, basicDAO.getCollection(), basicDAO.getDatastore())
                .filter(FIELD_ID + " in", convert(userIds))).asList();
    }

    @Override
    public User create(User user) {
        String userId = (String) basicDAO.save(user).getId();
        user.setId(userId);
        return user;
    }

    @Override
    public List<User> list() {
        return basicDAO.find().asList();
    }

    private List<ObjectId> convert(Set<String> ids){
        List<ObjectId> results = new ArrayList<>();
        for (String id : ids) {
            results.add(new ObjectId(id));
        }
        return results;
    }
}
