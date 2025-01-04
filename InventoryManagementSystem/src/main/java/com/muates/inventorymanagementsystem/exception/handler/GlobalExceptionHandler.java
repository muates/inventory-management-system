package com.muates.inventorymanagementsystem.exception.handler;

import com.muates.inventorymanagementsystem.common.model.dto.response.CustomResponse;
import com.muates.inventorymanagementsystem.exception.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalExceptionHandler {

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<>();

    public static void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        requestHolder.set(request);
        responseHolder.set(response);
    }

    public static void handle(ErrorCode errorCode) {
        try {
            HttpServletRequest request = requestHolder.get();
            HttpServletResponse response = responseHolder.get();

            if (request == null || response == null) {
                throw new IllegalStateException("Request or Response is not set for the current thread");
            }

            CustomResponse<Object> errorResponse = CustomResponse.error(errorCode.getMessage(), errorCode.getCode());

            request.setAttribute("errorResponse", errorResponse);
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            requestHolder.remove();
            responseHolder.remove();
        }
    }
}
