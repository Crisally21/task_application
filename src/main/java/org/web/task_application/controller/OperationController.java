package org.web.task_application.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web.task_application.dto.SignatureResponseDTO;
import org.web.task_application.util.HmacUtil;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    @PostMapping("/{operationId}")
    public SignatureResponseDTO processOperation(
            @PathVariable String operationId,
            @RequestParam Map<String, String> formParams) {

        try {
            String signature = HmacUtil.calculateHmac(formParams);

            SignatureResponseDTO.SignatureResult signatureResult =
                    new SignatureResponseDTO.SignatureResult(signature);

            return new SignatureResponseDTO("success",
                    new SignatureResponseDTO.SignatureResult[]{signatureResult});

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return new SignatureResponseDTO("error", null);
        }
    }
}
