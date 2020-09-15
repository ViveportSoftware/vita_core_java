package com.htc.vita.core.util;

import com.htc.vita.core.log.Logger;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class TypeRegistry {
    private static final Map<Class<?>, Class<?>> CONCRETE_CLASS_MAP = new HashMap<Class<?>, Class<?>>();
    private static final Map<String, Object> INSTANCE_MAP = new HashMap<String, Object>();

    @SuppressWarnings("unchecked")
    private static <TBaseClass, TSubClass extends TBaseClass> TBaseClass doGetInstance(
            Class<TBaseClass> abstractClassType,
            Class<TSubClass> subClassType) {
        Class<?> concreteClassType = CONCRETE_CLASS_MAP.get(abstractClassType);
        if (subClassType != null)
        {
            concreteClassType = subClassType;
        }

        if (concreteClassType == null) {
            return null;
        }

        String key = StringUtils.rootLocaleFormat(
                "%s_",
                concreteClassType.getName()
        );
        TBaseClass instance = null;
        synchronized (INSTANCE_MAP) {
            if (INSTANCE_MAP.containsKey(key)) {
                instance = (TBaseClass) INSTANCE_MAP.get(key);
            }
            if (instance == null) {
                Logger.getInstance(TypeRegistry.class.getSimpleName()).info(StringUtils.rootLocaleFormat(
                        "Initializing %s...",
                        key
                ));
                try {
                    Constructor<?> constructor = concreteClassType.getConstructor();
                    instance = (TBaseClass) constructor.newInstance();
                } catch (Exception e) {
                    Logger.getInstance(TypeRegistry.class.getSimpleName()).error(e.toString());
                }
            }
            if (!INSTANCE_MAP.containsKey(key)) {
                INSTANCE_MAP.put(
                        key,
                        instance
                );
            }
        }

        return instance;
    }

    public static <TBaseClass> TBaseClass getInstance(Class<TBaseClass> baseClass) {
        return doGetInstance(
                baseClass,
                null
        );
    }

    public static <TBaseClass, TSubClass extends TBaseClass> TBaseClass getInstance(
            Class<TBaseClass> baseClass,
            Class<TSubClass> subClass) {
        return doGetInstance(
                baseClass,
                subClass
        );
    }

    public static <TBaseClass, TSubClass extends TBaseClass> void register(
            Class<TBaseClass> baseClass,
            Class<TSubClass> subClass) {
        if (!CONCRETE_CLASS_MAP.containsKey(baseClass))
        {
            CONCRETE_CLASS_MAP.put(
                    baseClass,
                    subClass
            );
            Logger.getInstance(TypeRegistry.class.getSimpleName()).info(StringUtils.rootLocaleFormat(
                    "Registered %s type to %s",
                    baseClass.getSimpleName(),
                    subClass.getName()
            ));
            return;
        }

        if (subClass != CONCRETE_CLASS_MAP.get(baseClass))
        {
            CONCRETE_CLASS_MAP.put(
                    baseClass,
                    subClass
            );
            Logger.getInstance(TypeRegistry.class.getSimpleName()).info(StringUtils.rootLocaleFormat(
                    "Registered %s type to %s",
                    baseClass.getSimpleName(),
                    subClass.getName()
            ));
        }
    }

    public static <TBaseClass, TSubClass extends TBaseClass> void registerDefault(
            Class<TBaseClass> baseClass,
            Class<TSubClass> subClass) {
        if (CONCRETE_CLASS_MAP.containsKey(baseClass)) {
            Class<?> oldSubClass = CONCRETE_CLASS_MAP.get(baseClass);
            if (oldSubClass != subClass)
            {
                Logger.getInstance(TypeRegistry.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                        "%s had been registered to %s. Registering to %s will be ignored.",
                        baseClass.getName(),
                        oldSubClass.getName(),
                        subClass.getName()
                ));
            }
            return;
        }

        CONCRETE_CLASS_MAP.put(
                baseClass,
                subClass
        );
    }
}
