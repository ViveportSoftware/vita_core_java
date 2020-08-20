package com.htc.vita.core.crypto;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.Convert;
import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class AesFactoryTest {
    @Test
    public void default_0_getInstance() {
        AesFactory aesFactory = AesFactory.getInstance();
        Assert.assertNotNull(aesFactory);
    }

    @Test
    public void default_1_get() {
        AesFactory aesFactory = AesFactory.getInstance();
        Assert.assertNotNull(aesFactory);

        Aes aes = aesFactory.get();
        Assert.assertNotNull(aes);
    }

    @Test
    public void default_1_encrypt_withInput_withPassword() {
        AesFactory aesFactory = AesFactory.getInstance();
        Assert.assertNotNull(aesFactory);

        Aes aes = aesFactory.get();
        Assert.assertNotNull(aes);

        String plain = "data";
        String password = "p@ssword";
        byte[] inputInBytes = StringUtils.toBytesByUtf8(plain);
        byte[] outputInBytes = aes.encrypt(
                inputInBytes,
                password
        );
        Assert.assertNotNull(outputInBytes);
    }

    @Test
    public void default_1_encrypt_withEmptyInput_withPassword() {
        AesFactory aesFactory = AesFactory.getInstance();
        Assert.assertNotNull(aesFactory);

        Aes aes = aesFactory.get();
        Assert.assertNotNull(aes);

        String plain = "";
        String password = "p@ssword";
        byte[] inputInBytes = StringUtils.toBytesByUtf8(plain);
        byte[] outputInBytes = aes.encrypt(
                inputInBytes,
                password
        );
        Assert.assertNotNull(outputInBytes);
    }

    @Test
    public void default_2_decrypt_withPassword() {
        AesFactory aesFactory = AesFactory.getInstance();
        Assert.assertNotNull(aesFactory);

        Aes aesEncryptor = aesFactory.get();
        Assert.assertNotNull(aesEncryptor);

        String plain = "test data";
        String password = "p@ssword";
        byte[] inputInBytes = StringUtils.toBytesByUtf8(plain);
        byte[] encryptedInBytes = aesEncryptor.encrypt(
                inputInBytes,
                password
        );
        Assert.assertNotNull(encryptedInBytes);
        Logger.getInstance(AesFactoryTest.class.getSimpleName()).info("encrypted: " + Convert.toHexString(encryptedInBytes));

        Aes aesDecryptor = aesFactory.get();
        Assert.assertNotNull(aesDecryptor);
        Assert.assertNotEquals(aesDecryptor, aesEncryptor);

        byte[] decryptedInBytes = aesDecryptor.decrypt(
                encryptedInBytes,
                password
        );
        String decrypted = StringUtils.fromBytesByUtf8(decryptedInBytes);
        Assert.assertEquals(plain, decrypted);
    }

    @Test
    public void default_3_decrypt_withDataFromCSharp() {
        AesFactory aesFactory = AesFactory.getInstance();
        Assert.assertNotNull(aesFactory);
        Aes aesDecryptor = aesFactory.get();
        Assert.assertNotNull(aesDecryptor);

        String encodedString = "0c26a374f397122387a88ec7aa84394b80131f06b9ef0b9855ee7a10beeefa91";
        byte[] encodedData = Convert.fromHexString(encodedString);
        Assert.assertNotNull(encodedData);
        String password = "p@ssword";
        byte[] decodedData = aesDecryptor.decrypt(encodedData, password);
        Assert.assertNotNull(decodedData);
        String decodedString = StringUtils.fromBytesByUtf8(decodedData);
        Assert.assertEquals("test data", decodedString);
    }
}
