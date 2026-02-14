package org.example.productmanagment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

class ProductManagementIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

//    @Test
//    void shouldCreateCategoryAndProduct() {
//        CreateProductRequest productRequest = new CreateProductRequest(
//                "Laptop",
//                "Gaming laptop",
//                "1500.00",
//                "10",
//                "ELECTRONICS"
//        );
//
//        var productResponse = restTemplate.postForEntity(
//                "/api/products",
//                productRequest,
//                Void.class
//        );
//
//        assertEquals(HttpStatus.CREATED, productResponse.getStatusCode());
//        assertNotNull(productResponse.getBody());
//    }
}


