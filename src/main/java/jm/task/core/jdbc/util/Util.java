package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import jm.task.core.jdbc.model.User;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://%s:3306/%s";
    private static final String DB_HOST_NAME = "localhost";
    private static final String DB_NAME = "katapreprj114users";
    private static final String DB_USER_NAME = "newuser";
    private static final String DB_PWD = "qwE$5678";

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        return getMySQLConnection(DB_HOST_NAME, DB_NAME, DB_USER_NAME, DB_PWD);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String pwd)
            throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(String.format(DB_CONNECTION_URL, hostName, dbName), userName, pwd);
    }
    
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
    	if (sessionFactory == null) {
    		try {
    			Configuration cfg = new Configuration();
    			Properties settings = new Properties();
    			settings.put(Environment.DRIVER, DB_DRIVER);
    			settings.put(Environment.URL, String.format(DB_CONNECTION_URL, DB_HOST_NAME, DB_NAME)  +  "?useSSL=false");
    			settings.put(Environment.USER, DB_USER_NAME);
    			settings.put(Environment.PASS, DB_PWD);
    			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
    			settings.put(Environment.SHOW_SQL, "true");
    			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//	            settings.put(Environment.HBM2DDL_AUTO, "update");
//	            settings.put(Environment.HBM2DDL_AUTO, "create-drop");
	            cfg.setProperties(settings);
	            cfg.addAnnotatedClass(User.class);
	
	            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                .applySettings(cfg.getProperties()).build();
	
	            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
    		} catch (Exception e) {
    			System.out.println("creating session factory error: " + e);
//    			e.printStackTrace();
    		}
    	}
    	return sessionFactory;
    }


}
