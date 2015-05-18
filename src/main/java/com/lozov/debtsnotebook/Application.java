package com.lozov.debtsnotebook;

import com.lozov.debtsnotebook.entity.Debt;
import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.repository.DebtRepository;
import com.lozov.debtsnotebook.repository.UserRepository;
import com.lozov.debtsnotebook.service.UserService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.UnknownHostException;

@SpringBootApplication
public class Application {
    private static final String MONGODB_NAME = "heroku_app36911993";
    private static final String LOCAL_MONGODB_NAME = "debts";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public UserService userService() throws UnknownHostException {
        return new UserService(userRepository(), debtRepository());
    }

    @Bean
    public UserRepository userRepository() throws UnknownHostException {
        return new UserRepository(User.class, datastore());
    }

    @Bean
    public DebtRepository debtRepository() throws UnknownHostException {
        return new DebtRepository(Debt.class, datastore());
    }

    @Bean
    public Morphia morphia(){
        Morphia morphia = new Morphia();
        morphia.map(User.class, Debt.class);
        return morphia;
    }

    @Bean
    public Datastore datastore() throws UnknownHostException {
        MongoClient mongoClient;
        Datastore datastore;
        try {
            String url = System.getenv("MONGOLAB_URI");

            if (url != null){
                mongoClient = new MongoClient(new MongoClientURI(url));
                datastore = morphia().createDatastore(mongoClient, MONGODB_NAME);
            }
            else {
                mongoClient = new MongoClient();
                datastore = morphia().createDatastore(mongoClient, LOCAL_MONGODB_NAME);
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        return datastore;
    }

}
