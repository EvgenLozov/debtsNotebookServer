package com.lozov.debtsnotebook;

import com.lozov.debtsnotebook.db.HikariDataSourceProvider;
import com.lozov.debtsnotebook.db.JdbcService;
import com.lozov.debtsnotebook.repository.*;
import com.lozov.debtsnotebook.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.net.UnknownHostException;

//@SpringBootApplication
public class ConfigWithMySql {
    private static final String MYSQL_DATABASE_URL = "jdbc:mysql://localhost:3306/debts?user=root&password=neuser50";

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
        HikariDataSourceProvider dataSourceProvider = new HikariDataSourceProvider(MYSQL_DATABASE_URL, 10);
        return dataSourceProvider.get();
    }
}
