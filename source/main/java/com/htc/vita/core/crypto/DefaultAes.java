package com.htc.vita.core.crypto;

import com.htc.vita.core.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DefaultAes extends Aes {
    protected byte[] doTransform(
            byte[] input,
            byte[] key,
            byte[] iv,
            int cipherMode) throws Exception {
        if (iv == null || iv.length != IV_SIZE_128BIT_IN_BYTE) {
            throw new IllegalArgumentException("iv size is not match");
        }

        if (key.length != KEY_SIZE_128BIT_IN_BYTE
                && key.length != KEY_SIZE_192BIT_IN_BYTE
                && key.length != KEY_SIZE_256BIT_IN_BYTE) {
            throw new IllegalArgumentException("key size is not match");
        }

        Cipher cipher = Cipher.getInstance(StringUtils.rootLocaleFormat(
                "AES/%s/%s",
                getCipherMode().value(),
                getPaddingMode().value()
        ));
        cipher.init(
                cipherMode,
                new SecretKeySpec(
                        key,
                        "AES"
                ),
                new IvParameterSpec(iv)
        );
        return cipher.doFinal(input);
    }

    @Override
    protected byte[] onDecrypt(
            byte[] input,
            byte[] key,
            byte[] iv) throws Exception {
        return doTransform(
                input,
                key,
                iv,
                Cipher.DECRYPT_MODE
        );
    }

    @Override
    protected byte[] onEncrypt(
            byte[] input,
            byte[] key,
            byte[] iv) throws Exception {
        return doTransform(
                input,
                key,
                iv,
                Cipher.ENCRYPT_MODE
        );
    }
}
