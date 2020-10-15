package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

public class TypeRegistryTest {
    @Test
    public void default_0_registerDefault() {
        TypeRegistry.registerDefault(
                BaseClass.class,
                SubClass1.class
        );
    }

    @Test
    public void default_1_register() {
        TypeRegistry.registerDefault(
                BaseClass.class,
                SubClass1.class
        );
        TypeRegistry.register(
                BaseClass.class,
                SubClass2.class
        );
    }

    @Test
    public void default_2_getInstance() {
        TypeRegistry.registerDefault(
                BaseClass.class,
                SubClass1.class
        );
        TypeRegistry.register(
                BaseClass.class,
                SubClass2.class
        );
        BaseClass obj = TypeRegistry.getInstance(BaseClass.class);
        Assert.assertFalse(obj instanceof SubClass1);
        Assert.assertTrue(obj instanceof SubClass2);
    }

    public static abstract class BaseClass {
    }

    public static class SubClass1 extends BaseClass {
    }

    public static class SubClass2 extends BaseClass {
    }
}
