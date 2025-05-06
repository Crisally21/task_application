package org.web.task_application;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.web.task_application.controller.OperationController;
import org.web.task_application.util.HmacUtil;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OperationControllerTest {

    @Mock
    private HmacUtil hmacUtil;

    @InjectMocks
    private OperationController operationController;

    @Test
    public void testProcessOperation() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("name1", "value1");
        params.put("name2", "value2");

        when(hmacUtil.calculateHmac(anyMap())).thenReturn("mocked-hmac-signature");

        var response = operationController.processOperation("123", params);

        assertEquals("success", response.getStatus());
        assertEquals(1, response.getResult().length);
        assertEquals("mocked-hmac-signature", response.getResult()[0].getSignature());
    }
}
