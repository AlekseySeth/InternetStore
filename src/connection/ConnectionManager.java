package connection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * @author a.shestovsky
 */
public class ConnectionManager {
    private static PoolProperties poolProperties = new PoolProperties();
    static {
        poolProperties.setUrl("jdbc:mysql://localhost:3306/nutrition_store?useSSL=false");
        poolProperties.setUsername("root");
        poolProperties.setPassword("root");
        poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
    }
    public static DataSource dataSource = new DataSource(poolProperties);
}