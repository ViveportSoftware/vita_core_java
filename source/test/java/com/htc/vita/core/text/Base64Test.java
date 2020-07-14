package com.htc.vita.core.text;

import com.htc.vita.core.util.Convert;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class Base64Test {
    private static final String DATA_IN_HEX = "0000010001001010000001002000680400001600000028000000100000002000"
                                            + "0000010020000000000040040000000000000000000000000000000000000000"
                                            + "000000000000bf7f0004e1b10055e1b100a3e1b100d5e2b100ede2b100fde3b3"
                                            + "00ffe1b100f3e1b100dfe1b100b5e0b2006ddfaf001000000000000000000000"
                                            + "0000e2ae002ce0b100d4e0b100b1e1b00068e0b0005ae1b10065e0b2006de1b0"
                                            + "0068e0af0043e0b00041e0b20074e1b200c4e2b200eee0b1004b00000000dcae"
                                            + "0016e1b100d0dfb10048e1b00099e1b100f1e1b200f5e2b1008ddeb1002e0000"
                                            + "000000000000000000000000000000000000dfb2003fe1b100dde0ae0029e2b2"
                                            + "007ae1b1005fe0b100e6e3b300ffe2b100d3e2af002300000000000000000000"
                                            + "00000000000000000000000000000000000000000000e1ae003ce0b2008ee1b1"
                                            + "00abe0b0005be3b300ffe1b100fae0b200210000000000000000000000000000"
                                            + "0000000000000000000000000000000000000000000000000000e1b1009de1b1"
                                            + "00a2e0b1005ce3b300ffe2b200a6000000000000000000000000000000000000"
                                            + "00000000000000000000000000000000000000000000bf7f0004e1b2009be0b1"
                                            + "006ce0b1006ce2b200fee0b0006b000000000000000000000000000000000000"
                                            + "00000000000000000000000000000000000000000000e2b0003de0b1006ce2af"
                                            + "0023e0b200bae1b100bbe2b20060000000000000000000000000000000000000"
                                            + "00000000000000000000000000000000000000000000e2b10096dcb100240000"
                                            + "0000e1b100c9e1b1006fe2b1007200000000000000000000000000000000e2b0"
                                            + "0034e0b1007ce1b1009adb92000700000000dfaf0010e1b100be000000000000"
                                            + "0000e1b0005ee1b100a3e2b2007a0000000000000000000000007f7f0002dfb2"
                                            + "006a00000000e1b20071dfb2004900000000e1b20081e1b0005e000000000000"
                                            + "0000bf7f0004e1b200d6e0b1005c000000000000000000000000d1a2000be1b1"
                                            + "007f00000001e1b100a7ddb20035e2af0023e1b100cfbf7f0004000000000000"
                                            + "000000000000e1b00044e1b100d5d1a2000b000000000000000000000000e0b1"
                                            + "0086e1b100d0e1b10080cc990005e2b100c2e1b0004400000000000000000000"
                                            + "00000000000000000000e1b20092e1b100abaaaa000300000000000000000000"
                                            + "00000000000000000001e1b2009be1b200920000000000000000000000000000"
                                            + "00000000000000000000cc990005e1b100bbe2b100aedba4000e000000000000"
                                            + "0000cc99000ae1b100a3e1b100bbcc9900050000000000000000000000000000"
                                            + "0000000000000000000000000000cc99000ae0b200bae1b200ece1b20098e0b1"
                                            + "0097e1b200e8e0b200bacc99000a000000000000000000000000000000000000"
                                            + "000000000000000000000000000000000000aaaa0003dfb20059e2b1009ee2b1"
                                            + "009edfb20059aaaa00030000000000000000000000000000000000000000c003"
                                            + "00008001000000f8000003fc000007fe00000ffc00000ffc00000ffc00008f09"
                                            + "00008e4900008e010000c7030000e3c70000e1870000f00f0000f81f0000";
    private static final String DATA_IN_BASE64 = "AAABAAEAEBAAAAEAIABoBAAAFgAAACgAAAAQAAAAIAAAAAEAIAAAAAAAQAQAAAAA"
                                               + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAL9/AAThsQBV4bEAo+GxANXisQDt4rEA/eOz"
                                               + "AP/hsQDz4bEA3+GxALXgsgBt368AEAAAAAAAAAAAAAAAAOKuACzgsQDU4LEAseGw"
                                               + "AGjgsABa4bEAZeCyAG3hsABo4K8AQ+CwAEHgsgB04bIAxOKyAO7gsQBLAAAAANyu"
                                               + "ABbhsQDQ37EASOGwAJnhsQDx4bIA9eKxAI3esQAuAAAAAAAAAAAAAAAAAAAAAAAA"
                                               + "AADfsgA/4bEA3eCuACnisgB64bEAX+CxAObjswD/4rEA0+KvACMAAAAAAAAAAAAA"
                                               + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAOGuADzgsgCO4bEAq+CwAFvjswD/4bEA+uCy"
                                               + "ACEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA4bEAneGx"
                                               + "AKLgsQBc47MA/+KyAKYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                                               + "AAAAAAAAv38ABOGyAJvgsQBs4LEAbOKyAP7gsABrAAAAAAAAAAAAAAAAAAAAAAAA"
                                               + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAOKwAD3gsQBs4q8AI+CyALrhsQC74rIAYAAA"
                                               + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADisQCW3LEAJAAA"
                                               + "AADhsQDJ4bEAb+KxAHIAAAAAAAAAAAAAAAAAAAAA4rAANOCxAHzhsQCa25IABwAA"
                                               + "AADfrwAQ4bEAvgAAAAAAAAAA4bAAXuGxAKPisgB6AAAAAAAAAAAAAAAAf38AAt+y"
                                               + "AGoAAAAA4bIAcd+yAEkAAAAA4bIAgeGwAF4AAAAAAAAAAL9/AAThsgDW4LEAXAAA"
                                               + "AAAAAAAAAAAAANGiAAvhsQB/AAAAAeGxAKfdsgA14q8AI+GxAM+/fwAEAAAAAAAA"
                                               + "AAAAAAAA4bAAROGxANXRogALAAAAAAAAAAAAAAAA4LEAhuGxANDhsQCAzJkABeKx"
                                               + "AMLhsABEAAAAAAAAAAAAAAAAAAAAAAAAAADhsgCS4bEAq6qqAAMAAAAAAAAAAAAA"
                                               + "AAAAAAAAAAAAAeGyAJvhsgCSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAzJkABeGx"
                                               + "ALvisQCu26QADgAAAAAAAAAAzJkACuGxAKPhsQC7zJkABQAAAAAAAAAAAAAAAAAA"
                                               + "AAAAAAAAAAAAAAAAAADMmQAK4LIAuuGyAOzhsgCY4LEAl+GyAOjgsgC6zJkACgAA"
                                               + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKqqAAPfsgBZ4rEAnuKx"
                                               + "AJ7fsgBZqqoAAwAAAAAAAAAAAAAAAAAAAAAAAAAAwAMAAIABAAAA+AAAA/wAAAf+"
                                               + "AAAP/AAAD/wAAA/8AACPCQAAjkkAAI4BAADHAwAA48cAAOGHAADwDwAA+B8AAA==";

    @Test
    public void dummy_0_getInstance() {
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);
    }

    @Test
    public void dummy_1_decode() {
        Base64.preferJava8Impl(false);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);
        Assert.assertNull(base64.decode("VGVzdA=="));
        Assert.assertNull(base64.decode("VGVzdA==", Base64Option.Basic));
        Assert.assertNull(base64.decode("VGVzdA==", Base64Option.Mime));
        Assert.assertNull(base64.decode("VGVzdA==", Base64Option.UrlSafe));
    }

    @Test
    public void dummy_2_encodeToString() throws UnsupportedEncodingException {
        Base64.preferJava8Impl(false);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);
        byte[] data = "Test".getBytes("UTF-8");
        Assert.assertNull(base64.encodeToString(data));
        Assert.assertNull(base64.encodeToString(data, Base64Option.Basic));
        Assert.assertNull(base64.encodeToString(data, Base64Option.Mime));
        Assert.assertNull(base64.encodeToString(data, Base64Option.UrlSafe));
    }

    @Test
    public void java_1_encodeToString() {
        Base64.preferJava8Impl(true);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);

        byte[] data = Convert.fromHexString(DATA_IN_HEX);
        Assert.assertNotNull(data);
        String encoded = base64.encodeToString(data);
        Assert.assertEquals(
                DATA_IN_BASE64,
                encoded
        );
    }

    @Test
    public void java_1_encodeToString_withBasicOption() {
        Base64.preferJava8Impl(true);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);

        byte[] data = Convert.fromHexString(DATA_IN_HEX);
        Assert.assertNotNull(data);
        String encoded = base64.encodeToString(
                data,
                Base64Option.Basic
        );
        Assert.assertEquals(
                DATA_IN_BASE64,
                encoded
        );
    }

    @Test
    public void java_1_encodeToString_withMimeOption() {
        Base64.preferJava8Impl(true);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);

        byte[] data = Convert.fromHexString(DATA_IN_HEX);
        Assert.assertNotNull(data);
        String encoded = base64.encodeToString(
                data,
                Base64Option.Mime
        );
        Assert.assertNotEquals(
                DATA_IN_BASE64,
                encoded
        );
        encoded = encoded.replace(
                "\r\n",
                ""
        );
        Assert.assertEquals(
                DATA_IN_BASE64,
                encoded
        );
    }

    @Test
    public void java_1_encodeToString_withUrlSafeOption() {
        Base64.preferJava8Impl(true);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);

        byte[] data = Convert.fromHexString(DATA_IN_HEX);
        Assert.assertNotNull(data);
        String encoded = base64.encodeToString(
                data,
                Base64Option.UrlSafe
        );
        Assert.assertNotEquals(
                DATA_IN_BASE64,
                encoded
        );
        encoded = encoded.replace(
                "-",
                "+"
        ).replace(
                "_",
                "/"
        );
        Assert.assertEquals(
                DATA_IN_BASE64.replace(
                        "=",
                        ""
                ),
                encoded
        );
    }

    @Test
    public void java_2_decode() throws UnsupportedEncodingException {
        Base64.preferJava8Impl(true);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);

        byte[] decodedInBytes = base64.decode("VGVzdA==");
        Assert.assertNotNull(decodedInBytes);
        String decoded = new String(decodedInBytes, "UTF-8");
        Assert.assertEquals("Test", decoded);
    }

    @Test
    public void java_2_decode_withBasicOption() {
        Base64.preferJava8Impl(true);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);

        byte[] data = Convert.fromHexString(DATA_IN_HEX);
        Assert.assertNotNull(data);
        String encoded = base64.encodeToString(
                data,
                Base64Option.Basic
        );
        byte[] decoded = base64.decode(
                encoded,
                Base64Option.Basic
        );
        Assert.assertEquals(
                DATA_IN_HEX,
                Convert.toHexString(decoded)
        );
    }

    @Test
    public void java_2_decode_withMimeOption() {
        Base64.preferJava8Impl(true);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);

        byte[] data = Convert.fromHexString(DATA_IN_HEX);
        Assert.assertNotNull(data);
        String encoded = base64.encodeToString(
                data,
                Base64Option.Mime
        );
        byte[] decoded = base64.decode(
                encoded,
                Base64Option.Mime
        );
        Assert.assertEquals(
                DATA_IN_HEX,
                Convert.toHexString(decoded)
        );
    }

    @Test
    public void java_2_decode_withUrlSafeOption() {
        Base64.preferJava8Impl(true);
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);

        byte[] data = Convert.fromHexString(DATA_IN_HEX);
        Assert.assertNotNull(data);
        String encoded = base64.encodeToString(
                data,
                Base64Option.UrlSafe
        );
        byte[] decoded = base64.decode(
                encoded,
                Base64Option.UrlSafe
        );
        Assert.assertEquals(
                DATA_IN_HEX,
                Convert.toHexString(decoded)
        );
    }
}
