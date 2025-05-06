package org.web.task_application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OperationControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final String validToken = "your-secure-token-here";

    @Test
    public void testValidRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Token", validToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String params = "name1=value1&name2=value2";

        ResponseEntity<String> response = restTemplate.exchange(
                "/api/operations/123",
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("\"status\":\"success\""));
        assertTrue(response.getBody().contains("\"signature\":"));
    }

    @Test
    public void testInvalidToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Token", "invalid-token");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ResponseEntity<String> response = restTemplate.exchange(
                "/api/operations/123",
                HttpMethod.POST,
                new HttpEntity<>("param=value", headers),
                String.class
        );

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testMissingToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ResponseEntity<String> response = restTemplate.exchange(
                "/api/operations/123",
                HttpMethod.POST,
                new HttpEntity<>("param=value", headers),
                String.class
        );

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
