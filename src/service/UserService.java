package service;

import dao.UserDao;
import entity.user.Role;
import entity.user.User;

import java.sql.Date;
import java.util.List;

/**
 * @author a.shestovsky
 */
public class UserService {

    public User createNewUser(String firstName, String lastName, String email, String password,
                              String phone, String address, Date registrationDate, Role role) {
        User user = new User(firstName, lastName, email, password, phone, address, registrationDate, role);
        UserDao userDao = UserDao.newInstance();
        return userDao.save(user);
    }

    public User updateUser() {

        return null;
    }

    public List<User> getUsersList() {

        return null;
    }

    public User getUserById(Long id) {

        return null;
    }

    public User getUserByEmail(String email) {
        UserDao userDao = UserDao.newInstance();
        return userDao.getByEmail(email);
    }
}