 package com.jakka.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBUtil {
	
	private static HikariDataSource dataSource;
	
	static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:oracle:thin:@3.39.49.195:1521:xe");
        config.setUsername("jakka");
        config.setPassword("java1234");
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        config.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(config);
    }
	
	
	public static Connection open() throws SQLException {
        return dataSource.getConnection();
    }

    public static Connection open(String host, String id, String pw) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:oracle:thin:@" + host + ":1521:xe");
        config.setUsername(id);
        config.setPassword(pw);
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        config.setMaximumPoolSize(10);

        HikariDataSource ds = new HikariDataSource(config);
        return ds.getConnection();
    }

}//End of class
