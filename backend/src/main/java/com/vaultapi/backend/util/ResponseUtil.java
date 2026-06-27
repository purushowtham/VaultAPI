package com.vaultapi.backend.util;

import com.vaultapi.backend.dto.response.ApiResponse;

public class ResponseUtil {

    private ResponseUtil() {
    }

    public static <T> ApiResponse<T> success(String message, T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();

    }

    public static <T> ApiResponse<T> failure(String message) {

        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .build();

    }

}
