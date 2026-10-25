package org.example.productmanagment.endtoend;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

    @LocalServerPort
    protected int port;

    protected String baseUrl() {
        return "http://localhost:" + port;
    }
}