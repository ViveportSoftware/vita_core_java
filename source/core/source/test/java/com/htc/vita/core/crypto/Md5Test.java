package com.htc.vita.core.crypto;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class Md5Test {
    @Test
    public void default_0_getInstance() {
        Md5 md5 = Md5.getInstance();
        Assert.assertNotNull(md5);
    }

    @Test
    public void default_1_generateInHex_withContent() {
        Md5 md5 = Md5.getInstance();
        Assert.assertNotNull(md5);
        Assert.assertEquals(
                "d41d8cd98f00b204e9800998ecf8427e",
                md5.generateInHex("")
        );
        Assert.assertEquals(
                "202cb962ac59075b964b07152d234b70",
                md5.generateInHex("123")
        );
    }

    @Test
    public void default_1_generateInHex_withFile() {
        Md5 md5 = Md5.getInstance();
        Assert.assertNotNull(md5);
        Assert.assertEquals(
                "a6afd7bbb8d59e5bb12c9dbcc4ec2cff",
                md5.generateInHex(getTestFilePath())
        );
    }

    @Test
    public void default_2_validateInHex_withContent() {
        Md5 md5 = Md5.getInstance();
        Assert.assertNotNull(md5);
        Assert.assertTrue(md5.validateInHex(
                "",
                "d41d8cd98f00b204e9800998ecf8427e"
        ));
        Assert.assertTrue(md5.validateInHex(
                "123",
                "202cb962ac59075b964b07152d234b70"
        ));
    }

    @Test
    public void default_2_validateInHex_withFile() {
        Md5 md5 = Md5.getInstance();
        Assert.assertNotNull(md5);
        Assert.assertTrue(md5.validateInHex(
                getTestFilePath(),
                "a6afd7bbb8d59e5bb12c9dbcc4ec2cff"
        ));
    }

    @Test
    public void default_3_generateInBase64_withContent() {
        Md5 md5 = Md5.getInstance();
        Assert.assertNotNull(md5);
        Assert.assertEquals(
                "1B2M2Y8AsgTpgAmY7PhCfg==",
                md5.generateInBase64("")
        );
        Assert.assertEquals(
                "ICy5YqxZB1uWSwcVLSNLcA==",
                md5.generateInBase64("123")
        );
    }

    @Test
    public void default_3_generateInBase64_withFile() {
        Md5 md5 = Md5.getInstance();
        Assert.assertNotNull(md5);
        Assert.assertEquals(
                "pq/Xu7jVnluxLJ28xOws/w==",
                md5.generateInBase64(getTestFilePath())
        );
    }

    @Test
    public void default_4_validateInBase64_withContent() {
        Md5 md5 = Md5.getInstance();
        Assert.assertNotNull(md5);
        Assert.assertTrue(md5.validateInBase64(
                "",
                "1B2M2Y8AsgTpgAmY7PhCfg=="
        ));
        Assert.assertTrue(md5.validateInBase64(
                "123",
                "ICy5YqxZB1uWSwcVLSNLcA=="
        ));
    }

    @Test
    public void default_4_validateInBase64_withFile() {
        Md5 md5 = Md5.getInstance();
        Assert.assertNotNull(md5);
        Assert.assertTrue(md5.validateInBase64(
                getTestFilePath(),
                "pq/Xu7jVnluxLJ28xOws/w=="
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
