package org.example.productmanagment.endtoend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {
    public static final String BASE_URL = "http://localhost:8080";

    @Autowired
    private DataSource dataSource;

    protected void printDatabaseUrl() {
        try {
            System.out.println("✅ DATABASE_URL: " + dataSource.getConnection().getMetaData().getURL());
        } catch (SQLException e) {
            System.out.println("❌ Impossible de récupérer l'URL: " + e.getMessage());
        }
    }
}