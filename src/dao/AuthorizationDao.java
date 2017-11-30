package dao;

import connection.ConnectionManager;
import entity.user.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author a.shestovsky
 */
public class AuthorizationDao {

    private static AuthorizationDao INSTANCE;

    private AuthorizationDao() {
    }

    public static AuthorizationDao newInstance() {
        if (INSTANCE == null) {
            synchronized (AuthorizationDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AuthorizationDao();
                }
            }
        }
        return INSTANCE;
    }

    public Map<Role, List<String>> getPermissions() {
        Map<Role, List<String>> permissions = new HashMap<>();
        List<String> guestPages = new ArrayList<>();
        List<String> adminPages = new ArrayList<>();
        List<String> marketerPages = new ArrayList<>();
        List<String> customerPages = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM pages p JOIN roles_pages rp ON p.id=rp.page_id " +
                    "JOIN roles r ON rp.role_id = r.id";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int role_id = resultSet.getInt("role_id");
                if (role_id == 0) {
                    guestPages.add(resultSet.getString("url"));
                    permissions.put(Role.GUEST, guestPages);
                }
                if (role_id == 1) {
                    adminPages.add(resultSet.getString("url"));
                    permissions.put(Role.ADMIN, adminPages);
                }
                if (role_id == 2) {
                    marketerPages.add(resultSet.getString("url"));
                    permissions.put(Role.MARKETER, marketerPages);
                }
                if (role_id == 3) {
                    customerPages.add(resultSet.getString("url"));
                    permissions.put(Role.CUSTOMER, customerPages);
                }
            }
            resultSet.close();
            statement.close();
            return permissions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}