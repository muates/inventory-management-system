package com.muates.inventorymanagementsystem.util.servlet;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

public class RequestMapper {

    public static <T> T toRequest(HttpServletRequest request, Class<T> dtoClass, String[] params) {
        try {
            T dto = dtoClass.getDeclaredConstructor().newInstance();

            for (String paramName : params) {
                String value = request.getParameter(paramName);

                String methodName = "set" + paramName.substring(0, 1).toUpperCase() + paramName.substring(1);

                Method method = dtoClass.getMethod(methodName, String.class);
                method.invoke(dto, value);
            }

            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
