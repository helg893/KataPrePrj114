package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
    	final String SQL_QUERY_CREATE_USERS = """
                CREATE TABLE IF NOT EXISTS users (
                  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                  name VARCHAR(45) NOT NULL,
                  lastName VARCHAR(45) NOT NULL,
                  age INT NOT NULL)""";

    	Transaction transaction = null;
    	try (Session session = Util.getSessionFactory().openSession()) {
    		transaction = session.beginTransaction();
    		session.createSQLQuery(SQL_QUERY_CREATE_USERS).executeUpdate();
    		transaction.commit();
    	} catch (Exception e) {
    		if (transaction != null) {
    			transaction.rollback();
    		}
    		System.out.println("create users table error: " + e);
    	}
    }

    @Override
    public void dropUsersTable() {
    	Transaction transaction = null;
    	try (Session session = Util.getSessionFactory().openSession()) {
    		transaction = session.beginTransaction();
    		session.createSQLQuery("drop table if exists users").executeUpdate();
    		transaction.commit();
    	} catch (Exception e) {
    		if (transaction != null) {
    			transaction.rollback();
    		}
    		System.out.println("drop users table error: "  + e);
    	}

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
    	Transaction transaction = null;
    	try (Session session = Util.getSessionFactory().openSession()) { 
    		transaction = session.beginTransaction();
    		session.save(new User(name, lastName, age));
    		transaction.commit();
    		System.out.println("User с именем – " + name + " добавлен в базу данных");
    	} catch (Exception e) {
    		if (transaction != null) {
    			transaction.rollback();
    		}
    		System.out.println("save user error: "  + e);
    	}
    }

    @Override
    public void removeUserById(long id) {
    	Transaction transaction = null;
    	try (Session session = Util.getSessionFactory().openSession()) {
    		User user = new User();
    		user.setId(id);
    		transaction = session.beginTransaction();
    		session.delete(user);
    		transaction.commit();
    	} catch (Exception e) {
    		if (transaction != null) {
    			transaction.rollback();
    		}
    		System.out.println("remove user by id error: " + e);
    	}
    }

    @Override
    public List<User> getAllUsers() {
    	try (Session session = Util.getSessionFactory().openSession()) {
    		return session.createQuery("from User", User.class).list();
    	} catch(Exception e) {
    		System.out.println("get all users error: " + e);
    	}
		return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
    	Transaction transaction = null;
    	try (Session session = Util.getSessionFactory().openSession()) {
    		transaction = session.beginTransaction();
    		session.createQuery("delete from User").executeUpdate();
    		transaction.commit();
    	} catch (Exception e) {
    		if (transaction != null) {
    			transaction.rollback();
    		}
    		System.out.println("clean users table error: " + e);
    	}
    }
}
