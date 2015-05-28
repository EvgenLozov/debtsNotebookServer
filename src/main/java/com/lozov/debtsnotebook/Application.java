package com.lozov.debtsnotebook;

import com.lozov.debtsnotebook.db.HikariDataSourceProvider;
import com.lozov.debtsnotebook.db.JdbcService;
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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.net.UnknownHostException;

@SpringBootApplication
public class Application {
    private static final String MONGODB_NAME = "heroku_app36911993";
    private static final String LOCAL_MONGODB_NAME = "debts";
    private static final String MYSQL_DATABASE_URL = "jdbc:mysql://localhost:3306/debts?user=root&password=neuser50";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public UserService userService() throws UnknownHostException {
        return mySqlUserRepository() ;
    }

    @Bean(name = "userRepository")
    public UserRepository userRepository() throws UnknownHostException {
        return mySqlUserRepository();
    }

    @Bean
    public MySqlUserRepository mySqlUserRepository(){
        return new MySqlUserRepository(jdbcService());
    }

    @Bean
    public DebtRepository debtRepository() throws UnknownHostException {
        return new MySqlDebtRepository(jdbcService());
    }

    @Bean
    public Morphia morphia(){
        Morphia morphia = new Morphia();
        morphia.map(User.class, Debt.class);
        return morphia;
    }

    @Bean
    public Datastore datastore() throws UnknownHostException {
        return morphia().createDatastore(localMongoClient(), MONGODB_NAME);
    }

    @Bean
    public JdbcService jdbcService(){
        return new JdbcService(dataSource());
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

//    @Bean
//    public DataSource dataSource(){
//        HikariDataSourceProvider dataSourceProvider = new HikariDataSourceProvider(MYSQL_DATABASE_URL, 10);
//        return dataSourceProvider.get();
//    }
}
