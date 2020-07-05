package com.htc.vita.core.util;

public class EnumUtils {
    private static <T extends Enum<T>> T[] getEnumConstantArrayOrThrow(Class<T> enumClass)
            throws IllegalArgumentException {
        if (enumClass == null) {
            throw new IllegalArgumentException("enumClass can not be null");
        }

        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("enumClass should be an Enum class");
        }

        T[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null || enumConstants.length <= 0) {
            throw new IllegalArgumentException("enumClass does not have valid enum type");
        }

        return enumConstants;
    }

    public static <T extends Enum<T>> T parseTypeByName(
            Class<T> enumClass,
            String data)
                    throws IllegalArgumentException {
        T[] enumConstantArray = getEnumConstantArrayOrThrow(enumClass);
        T defaultValue = enumConstantArray[0];

        if (StringUtils.isNullOrWhiteSpace(data)) {
            return defaultValue;
        }

        for (T enumConstant : enumConstantArray) {
            if (enumConstant == null) {
                continue;
            }

            if (enumConstant.name().equals(data)) {
                return enumConstant;
            }
        }

        return defaultValue;
    }

    public static <T extends Enum<T>> T parseTypeByToString(
            Class<T> enumClass,
            String data)
            throws IllegalArgumentException {
        T[] enumConstantArray = getEnumConstantArrayOrThrow(enumClass);
        T defaultValue = enumConstantArray[0];

        if (StringUtils.isNullOrWhiteSpace(data)) {
            return defaultValue;
        }

        for (T enumConstant : enumConstantArray) {
            if (enumConstant == null) {
                continue;
            }

            if (enumConstant.toString().equals(data)) {
                return enumConstant;
            }
        }

        return defaultValue;
    }
}
