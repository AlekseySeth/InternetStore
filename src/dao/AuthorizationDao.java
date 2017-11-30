package dao;

import connection.ConnectionManager;
import entity.user.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        List<String> pages = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM pages p JOIN roles_pages rp ON p.id=rp.page_id " +
                    "JOIN roles r ON rp.role_id = r.id";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int role_id = resultSet.getInt("role_id");
                if (role_id == 1) {
                    pages.add(resultSet.getString("url"));
                    permissions.put(Role.ADMIN, pages);
                }
                if (role_id == 2) {
                    pages.add(resultSet.getString("url"));
                    permissions.put(Role.MARKETER, pages);
                }
                if (role_id == 3) {
                    pages.add(resultSet.getString("url"));
                    permissions.put(Role.CUSTOMER, pages);
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
