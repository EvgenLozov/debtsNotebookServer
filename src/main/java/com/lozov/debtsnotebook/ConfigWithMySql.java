package com.lozov.debtsnotebook;

import com.lozov.debtsnotebook.db.ApacheDataSourceProvider;
import com.lozov.debtsnotebook.db.HikariDataSourceProvider;
import com.lozov.debtsnotebook.db.JdbcService;
import com.lozov.debtsnotebook.repository.*;
import com.lozov.debtsnotebook.service.UserService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.net.UnknownHostException;

@SpringBootApplication
public class ConfigWithMySql {
    private static final String MYSQL_HEROKU_DATABASE_CONFIG_ARG = "CLEARDB_DATABASE_URL";
    private static final String MYSQL_LOCAL_DATABASE_URL = "jdbc:mysql://localhost:3306/debts?user=root&password=neuser50";
    private static final String MYSQL_HEROKU_DATABASE_URL =
            "jdbc:mysql://us-cdbr-iron-east-02.cleardb.net:3306/heroku_abc1d9f1cdfdc97?user=bf0374fa9d6fd8&password=bcd6188a";

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
    public JdbcService jdbcService(){
        return new JdbcService(dataSource());
    }

    @Bean
    public DataSource dataSource(){
        return herokuWithHikari().get();
    }


    public HikariDataSourceProvider herokuWithHikari(){
        String url = System.getenv(MYSQL_HEROKU_DATABASE_CONFIG_ARG);

        if (url != null)
            return new HikariDataSourceProvider(url, 3);

        return new HikariDataSourceProvider(MYSQL_HEROKU_DATABASE_URL, 3);
    }

    public ApacheDataSourceProvider herokuWithapache(){
        String url = System.getenv(MYSQL_HEROKU_DATABASE_CONFIG_ARG);

        if (url != null)
            return new ApacheDataSourceProvider(url, 3);

        return new ApacheDataSourceProvider(MYSQL_HEROKU_DATABASE_URL, 3);
    }


    @Bean HikariDataSourceProvider local(){
        return new HikariDataSourceProvider(MYSQL_LOCAL_DATABASE_URL, 10);
    }
}
