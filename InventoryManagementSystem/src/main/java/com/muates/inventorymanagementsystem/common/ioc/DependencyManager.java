package com.muates.inventorymanagementsystem.common.ioc;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DependencyManager {

    private static DependencyManager instance;
    private final Map<String, Object> container = new HashMap<>();

    public static DependencyManager getContainer() {
        if (instance == null) {
            instance = new DependencyManager();
        }
        return instance;
    }

    public <T> void register(Class<T> interfaceType, T implementation) {
        container.put(interfaceType.getName(), implementation);
    }

    public void register(Class<?> interfaceType, String key, Object implementation) {
        container.put(key + interfaceType.getName(), implementation);
    }

    public <T> T resolve(Class<T> interfaceType) {
        return (T) container.get(interfaceType.getName());
    }

    public <T> T resolve(Class<T> interfaceType, String key) {
        return (T) container.get(key + interfaceType.getName());
    }

    public void injectDependencies(Object target) {
        for (Field field : target.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Class<?> fieldType = field.getType();
                Object dependency = resolve(fieldType);
                if (dependency != null) {
                    field.setAccessible(true);
                    try {
                        field.set(target, dependency);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Dependency injection failed for field: " + field.getName(), e);
                    }
                }
            }
        }
    }

    public void injectAllDependencies() {
        container.values().forEach(this::injectDependencies);
    }
}
