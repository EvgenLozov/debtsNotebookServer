package com.lozov.debtsnotebook;

import com.lozov.debtsnotebook.entity.Debt;
import com.lozov.debtsnotebook.entity.User;
import com.lozov.debtsnotebook.repository.DebtRepository;
import com.lozov.debtsnotebook.repository.UserRepository;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.UnknownHostException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public UserRepository userRepository() throws UnknownHostException {
        Morphia morphia = morphia();
        MongoClient mongoClient = mongoClient();
        Datastore datastore = morphia.createDatastore(mongoClient, "debts");
        return new UserRepository(User.class, datastore);
    }

    @Bean
    public DebtRepository debtRepository() throws UnknownHostException {
        Morphia morphia = morphia();
        MongoClient mongoClient = mongoClient();
        Datastore datastore = morphia.createDatastore(mongoClient, "debts");
        return new DebtRepository(Debt.class, datastore);
    }

    @Bean
    public Morphia morphia(){
        Morphia morphia = new Morphia();
        morphia.map(User.class, Debt.class);
        return morphia;
    }

    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient("127.0.0.1", 27017);
    }

}
