package service;

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

    public User signIn() {

        return null;
    }
}