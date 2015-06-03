package com.lozov.debtsnotebook.db;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

/**
 * Created by Yevhen on 2015-06-02.
 */
public class ApacheDataSourceProvider {
    private BasicDataSource dataSource;

    public ApacheDataSourceProvider(String url, int maxPoolSize) {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setMaxActive(maxPoolSize);
        dataSource.setMaxWait(10000);
        dataSource.setMaxIdle(10);
    }

    public DataSource get() {
        return dataSource;
    }
}
