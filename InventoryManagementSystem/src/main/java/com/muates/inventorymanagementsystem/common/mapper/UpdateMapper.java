package com.muates.inventorymanagementsystem.common.mapper;

import java.lang.reflect.Field;

public class UpdateMapper {

    public static <T> void updateFields(T source, T target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Source and target objects must not be null");
        }

        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object sourceValue = field.get(source);

                if (sourceValue != null) {
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, sourceValue);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
