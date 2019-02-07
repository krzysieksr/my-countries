package com.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class ConnectionPoolContextListener implements ServletContextListener {
    private static final String CLOUD_SQL_CONNECTION_NAME =
            "country-230809:europe-west2:country-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "dupa";
    private static final String DB_NAME = "postgres";

    private DataSource createConnectionPool() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(String.format("jdbc:postgresql:///%s", DB_NAME));
        config.setUsername(DB_USER);
        config.setPassword(DB_PASS);

        config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.postgres.SocketFactory");
        config.addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME);

        return new HikariDataSource(config);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataSource pool = (DataSource) servletContextEvent.getServletContext().getAttribute("my-pool");
        if (pool == null) {
            pool = createConnectionPool();
            servletContextEvent.getServletContext().setAttribute("my-pool", pool);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HikariDataSource pool = (HikariDataSource) servletContextEvent.getServletContext().getAttribute("my-pool");
        if (pool != null) {
            pool.close();
        }
    }
}
