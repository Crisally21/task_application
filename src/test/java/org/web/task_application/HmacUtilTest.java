package org.web.task_application;

import org.junit.jupiter.api.Test;
import org.web.task_application.util.HmacUtil;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HmacUtilTest {

    @Test
    public void testCalculateHmac() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("name1", "value1");
        params.put("name2", "value2");

        String signature = HmacUtil.calculateHmac(params);

        assertNotNull(signature);
        assertEquals(64, signature.length());
    }

    @Test
    public void testParameterOrder() throws Exception {
        Map<String, String> params1 = new HashMap<>();
        params1.put("a", "1");
        params1.put("b", "2");

        Map<String, String> params2 = new HashMap<>();
        params2.put("b", "2");
        params2.put("a", "1");

        assertEquals(
                HmacUtil.calculateHmac(params1),
                HmacUtil.calculateHmac(params2)
        );
    }

}
