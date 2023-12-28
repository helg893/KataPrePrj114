package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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


}
