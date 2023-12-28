package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        System.out.println("Hello KATA PreProject! 1.1.4");

//        try(Connection conn = Util.getMySQLConnection()) {
//            System.out.println("get conn...");
//            System.out.println(conn);
//            System.out.println("done!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
        userDao.saveUser("Al", "Pachino", (byte) 43);
        System.out.println(userDao.getAllUsers());
        userDao.removeUserById(4);
        System.out.println(userDao.getAllUsers());

    }
}
