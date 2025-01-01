package com.muates.inventorymanagementsystem.common.mapper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class RequestMapper {

    public static <T> T toRequest(HttpServletRequest request, Class<T> dtoClass, String[] params) {
        try {
            T dto = dtoClass.getDeclaredConstructor().newInstance();

            for (String paramName : params) {
                String value = request.getParameter(paramName);

                String methodName = "set" + paramName.substring(0, 1).toUpperCase() + paramName.substring(1);

                Field field = dtoClass.getDeclaredField(paramName);
                Class<?> fieldType = field.getType();

                Method method;
                if (fieldType.equals(String.class)) {
                    method = dtoClass.getMethod(methodName, String.class);
                    method.invoke(dto, value);
                } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                    method = dtoClass.getMethod(methodName, Integer.class);
                    method.invoke(dto, Integer.valueOf(value));
                } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                    method = dtoClass.getMethod(methodName, Double.class);
                    method.invoke(dto, Double.valueOf(value));
                } else if (fieldType.equals(BigDecimal.class)) {
                    method = dtoClass.getMethod(methodName, BigDecimal.class);
                    method.invoke(dto, new BigDecimal(value));
                }
            }

            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
