package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

public class EnumUtilsTest {
    @Test
    public void default_0_parseTypeByName() {
        TestEnum testEnum = EnumUtils.parseTypeByName(
                TestEnum.class,
                "TestEnum8"
        );
        Assert.assertNotEquals(TestEnum.TestEnum9, testEnum);
        Assert.assertEquals(TestEnum.TestEnum8, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum7, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum6, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum5, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum4, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum3, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum2, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum1, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum0, testEnum);
    }

    @Test
    public void default_0_parseTypeByName_with_whitespace() {
        TestEnum testEnum = EnumUtils.parseTypeByName(
                TestEnum.class,
                ""
        );
        Assert.assertEquals(TestEnum.TestEnum9, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum8, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum7, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum6, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum5, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum4, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum3, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum2, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum1, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum0, testEnum);
        testEnum = EnumUtils.parseTypeByName(
                TestEnum.class,
                " "
        );
        Assert.assertEquals(TestEnum.TestEnum9, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum8, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum7, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum6, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum5, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum4, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum3, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum2, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum1, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum0, testEnum);
    }

    @Test
    public void default_0_parseTypeByName_with_notMatchedString() {
        TestEnum testEnum = EnumUtils.parseTypeByName(
                TestEnum.class,
                "Hello"
        );
        Assert.assertEquals(TestEnum.TestEnum9, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum8, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum7, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum6, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum5, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum4, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum3, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum2, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum1, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum0, testEnum);
    }

    @Test
    public void default_0_parseTypeByToString() {
        TestEnum testEnum = EnumUtils.parseTypeByToString(
                TestEnum.class,
                "test_enum_8"
        );
        Assert.assertNotEquals(TestEnum.TestEnum9, testEnum);
        Assert.assertEquals(TestEnum.TestEnum8, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum7, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum6, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum5, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum4, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum3, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum2, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum1, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum0, testEnum);
    }

    @Test
    public void default_0_parseTypeByToString_with_whitespace() {
        TestEnum testEnum = EnumUtils.parseTypeByToString(
                TestEnum.class,
                ""
        );
        Assert.assertEquals(TestEnum.TestEnum9, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum8, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum7, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum6, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum5, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum4, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum3, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum2, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum1, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum0, testEnum);
        testEnum = EnumUtils.parseTypeByToString(
                TestEnum.class,
                " "
        );
        Assert.assertEquals(TestEnum.TestEnum9, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum8, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum7, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum6, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum5, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum4, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum3, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum2, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum1, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum0, testEnum);
    }

    @Test
    public void default_0_parseTypeByToString_with_notMatchedString() {
        TestEnum testEnum = EnumUtils.parseTypeByToString(
                TestEnum.class,
                "Hello"
        );
        Assert.assertEquals(TestEnum.TestEnum9, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum8, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum7, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum6, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum5, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum4, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum3, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum2, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum1, testEnum);
        Assert.assertNotEquals(TestEnum.TestEnum0, testEnum);
    }

    public enum TestEnum {
        TestEnum9("test_enum_9"),
        TestEnum8("test_enum_8"),
        TestEnum7("test_enum_7"),
        TestEnum6("test_enum_6"),
        TestEnum5("test_enum_5"),
        TestEnum4("test_enum_4"),
        TestEnum3("test_enum_3"),
        TestEnum2("test_enum_2"),
        TestEnum1("test_enum_1"),
        TestEnum0("test_enum_0");

        private final String mDescription;

        TestEnum(String description) {
            mDescription = description;
        }

        @Override
        public String toString() {
            return mDescription;
        }
    }
}
