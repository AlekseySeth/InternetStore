package service;

import entity.order.Order;
import entity.user.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
        User user = UserService.newInstance().getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public Order createInitialOrder(User user) {
        return new Order(user);
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