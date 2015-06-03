package com.lozov.debtsnotebook;

import com.lozov.debtsnotebook.entity.Debt;
import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.repository.*;
import com.lozov.debtsnotebook.service.MongoUserService;
import com.lozov.debtsnotebook.service.UserService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.UnknownHostException;

/**
 * Created by lozov on 29.05.15.
 */
//@SpringBootApplication
public class ConfigWithMongo {
    private static final String MONGODB_NAME = "heroku_app36911993";
    private static final String LOCAL_MONGODB_NAME = "debts";

    @Bean
    public UserService userService() throws UnknownHostException {
        return new MongoUserService(userRepository(), debtRepository());
    }

    @Bean(name = "userRepository")
    public UserRepository userRepository() throws UnknownHostException {
        BasicDAO<User, String> basicDAO = new BasicDAO<>(User.class, datastore());
        return new MongoUserRepository(basicDAO);
    }

    @Bean
    public DebtRepository debtRepository() throws UnknownHostException {
        BasicDAO<Debt, String> basicDAO = new BasicDAO<>(Debt.class, datastore());
        return new MongoDebtRepository(basicDAO);
    }

    @Bean
    public Morphia morphia(){
        Morphia morphia = new Morphia();
        morphia.map(User.class, Debt.class);
        return morphia;
    }

    @Bean
    public Datastore datastore() throws UnknownHostException {
        return morphia().createDatastore(localMongoClient(), LOCAL_MONGODB_NAME);
    }

    @Bean
    public MongoClient prodMongoClient(){
        try {
            String url = System.getenv("MONGOLAB_URI");

            if (url != null){
                return new MongoClient(new MongoClientURI(url));
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Bean
    public MongoClient localMongoClient(){
        try {
            return new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
