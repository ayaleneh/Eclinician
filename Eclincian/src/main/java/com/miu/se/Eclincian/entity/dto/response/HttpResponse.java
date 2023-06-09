package com.miu.se.Eclincian.entity.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class HttpResponse {
    private String message;
    private HttpStatus status;
}
