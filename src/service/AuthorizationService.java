package service;

import dao.UserDao;
import entity.order.Order;
import entity.user.User;

/**
 * @author a.shestovsky
 */
public class AuthorizationService {

    private static AuthorizationService INSTANCE;

    private AuthorizationService() {
    }

    public static AuthorizationService newInstance() {
        if (INSTANCE == null) {
            synchronized (AuthorizationService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AuthorizationService();
                }
            }
        }
        return INSTANCE;
    }

    public Order createInitialOrder(User user) {
        return new Order(user);
    }

    public User signIn(String email, String password) {
        User user = UserDao.newInstance().getByEmail(email);

        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}