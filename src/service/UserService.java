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

    private static UserService INSTANCE;

    private UserService() {
    }

    public static UserService newInstance() {
        if (INSTANCE == null) {
            synchronized (UserService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserService();
                }
            }
        }
        return INSTANCE;
    }

    public User createNewUser(User user) {
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