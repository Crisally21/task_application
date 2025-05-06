package org.web.task_application.dto;

import lombok.Data;

public class SignatureResponseDTO {
    private String status; // Статус операции
    private SignatureResult[] result; // Результат с подписью

    // Конструкторы
    public SignatureResponseDTO() {
    }

    public SignatureResponseDTO(String status, SignatureResult[] result) {
        this.status = status;
        this.result = result;
    }

    // Геттеры и сеттеры
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SignatureResult[] getResult() {
        return result;
    }

    public void setResult(SignatureResult[] result) {
        this.result = result;
    }

    // Вложенный класс для результата
    public static class SignatureResult {
        private String signature; // HMAC подпись

        public SignatureResult() {
        }

        public SignatureResult(String signature) {
            this.signature = signature;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }
}