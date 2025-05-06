package org.web.task_application.dto;

import lombok.Data;

@Data
public class SignatureResponseDTO {
    private String status;
    private SignatureResult[] result;

    @Data
    public static class SignatureResult {
        private String signature;
    }
}
