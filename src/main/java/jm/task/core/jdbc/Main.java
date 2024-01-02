package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

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
