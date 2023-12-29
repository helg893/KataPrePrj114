package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        System.out.println("Hello KATA PreProject! 1.1.4");

//        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
//        userDao.saveUser("Chuck", "Norris", (byte) 35);
//        userDao.saveUser("Bruce", "Lee", (byte) 35);
//        userDao.saveUser("Al", "Pachino", (byte) 43);
//        System.out.println(userDao.getAllUsers());

//        userDao.cleanUsersTable();
//        System.out.println(userDao.getAllUsers());
//        userDao.dropUsersTable();
//        userDao.createUsersTable();
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Chuck", "Norris", (byte) 44);
        userService.saveUser("Bruce", "Lee", (byte) 44);
        userService.saveUser("Mickey", "Rourke", (byte) 32);
        userService.saveUser("Keanu", "Reeves", (byte) 20);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
