package org.web.task_application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class FormRequestDTO {
    private String operationId;
    private Map<String, String> formParams;
}
