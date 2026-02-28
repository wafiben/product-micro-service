package org.example.productmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ProductManagmentApplication {

    private static final Logger log = LoggerFactory.getLogger(ProductManagmentApplication.class);

    public static void main(String[] args) {
        log.info("Aplication lunched");
        SpringApplication.run(ProductManagmentApplication.class, args);
    }
}