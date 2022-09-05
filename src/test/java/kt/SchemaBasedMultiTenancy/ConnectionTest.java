package kt.SchemaBasedMultiTenancy;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Fail.fail;

public class ConnectionTest {

    @Test
    void testConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/schema-based?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8",
                    "root",
                    "1234");
            System.out.println(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
