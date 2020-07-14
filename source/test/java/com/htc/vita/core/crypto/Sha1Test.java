package com.htc.vita.core.crypto;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Sha1Test {
    @Test
    public void default_0_getInstance() {
        Sha1 sha1 = Sha1.getInstance();
        Assert.assertNotNull(sha1);
    }

    @Test
    public void default_1_generateInHex_withContent() {
        Sha1 sha1 = Sha1.getInstance();
        Assert.assertNotNull(sha1);
        Assert.assertEquals(
                "da39a3ee5e6b4b0d3255bfef95601890afd80709",
                sha1.generateInHex("")
        );
        Assert.assertEquals(
                "40bd001563085fc35165329ea1ff5c5ecbdbbeef",
                sha1.generateInHex("123")
        );
    }

    @Test
    public void default_1_generateInHex_withFile() {
        Sha1 sha1 = Sha1.getInstance();
        Assert.assertNotNull(sha1);
        Assert.assertEquals(
                "f5e24078c0936ca78815260e7d58d1a940966eba",
                sha1.generateInHex(getTestFilePath())
        );
    }

    @Test
    public void default_2_validateInHex_withContent() {
        Sha1 sha1 = Sha1.getInstance();
        Assert.assertNotNull(sha1);
        Assert.assertTrue(sha1.validateInHex(
                "",
                "da39a3ee5e6b4b0d3255bfef95601890afd80709"
        ));
        Assert.assertTrue(sha1.validateInHex(
                "123",
                "40bd001563085fc35165329ea1ff5c5ecbdbbeef"
        ));
    }

    @Test
    public void default_2_validateInHex_withFile() {
        Sha1 sha1 = Sha1.getInstance();
        Assert.assertNotNull(sha1);
        Assert.assertTrue(sha1.validateInHex(
                getTestFilePath(),
                "f5e24078c0936ca78815260e7d58d1a940966eba"
        ));
    }

    private static File getTestFilePath() {
        String testFilePathString = System.getProperty("user.dir")
                + File.separator
                + "source"
                + File.separator
                + "test"
                + File.separator
                + "resources"
                + File.separator
                + "TestData.Sha1.txt";
        File testFilePath = new File(testFilePathString);
        if (!testFilePath.exists() || !testFilePath.isFile()) {
            return null;
        }
        return testFilePath;
    }
}