package service;

import dao.AuthorizationDao;
import dao.DeliveryDao;
import entity.order.Delivery;
import entity.order.Order;
import entity.user.Role;
import entity.user.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @author a.shestovsky
 */
public class AuthenticationService {

    public static final long DEFAULT_DELIVERY_ID = 1L;
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

    public Map<Role, List<String>> getPermissions() {
        List<String> allowedPages = new ArrayList<>(
                Arrays.asList("/", "/articles", "/contact-us", "/delivery-info", "/category",
                        "/product", "/category-list", "/login", "/registration", "/language"));
        Map<Role, List<String>> permissions = AuthorizationDao.newInstance().getPermissions();
        permissions.put(Role.GUEST, allowedPages);
        return permissions;
    }

    public User signIn(String email, String password) {
        User user = UserService.newInstance().getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public Order createInitialOrder(User user) {
        Delivery delivery = DeliveryDao.newInstance().get(DEFAULT_DELIVERY_ID);
        return new Order(user, delivery);
    }

    public String encryptPassword(String email, String originalPassword) {
        String encryptedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((originalPassword + email).getBytes("UTF-8"));
            encryptedPassword = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }
}