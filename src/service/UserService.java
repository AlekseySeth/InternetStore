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



    public boolean updatePassword(User user, String password) {
        String newPassword = AuthenticationService.newInstance().encryptPassword(user.getEmail(), password);
        user.setPassword(newPassword);
        return UserDao.newInstance().updatePassword(user);
    }

    public boolean updateProfile(User user, String firstName, String lastName, String phone, String address) {
        if (firstName.length() > 0) {
            user.setFirstName(firstName);
        }
        user.setLastName(lastName);
        if (phone.length() > 0) {
            user.setFirstName(phone);
        }
        if (address.length() > 0) {
            user.setFirstName(address);
        }
        return UserDao.newInstance().updateProfile(user);
    }

    public List<User> getUsersList() {
        return UserDao.newInstance().getAll();
    }

    public User getUserById(Long id) {

        return null;
    }

    public User getUserByEmail(String email) {
        UserDao userDao = UserDao.newInstance();
        return userDao.getByEmail(email);
    }
}