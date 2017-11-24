package service;

import dao.UserDao;
import entity.order.Order;
import entity.user.User;

/**
 * @author a.shestovsky
 */
public class AuthenticationService {

    private static AuthenticationService INSTANCE;

    private AuthenticationService() {
    }

    public static AuthenticationService newInstance() {
        if (INSTANCE == null) {
            synchronized (AuthenticationService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AuthenticationService();
                }
            }
        }
        return INSTANCE;
    }

    public User signIn(String email, String password) {
        User user = UserDao.newInstance().getByEmail(email);

        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public Order createInitialOrder(User user) {
        return new Order(user);
    }
}