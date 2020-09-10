package com.htc.vita.core.crypto;

import com.htc.vita.core.log.Logger;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Locale;

public abstract class Aes {
    public static final int IV_SIZE_128BIT_IN_BIT = 128;
    public static final int IV_SIZE_128BIT_IN_BYTE = IV_SIZE_128BIT_IN_BIT / 8;
    public static final int KEY_SIZE_128BIT_IN_BIT = 128;
    public static final int KEY_SIZE_128BIT_IN_BYTE = KEY_SIZE_128BIT_IN_BIT / 8;
    public static final int KEY_SIZE_192BIT_IN_BIT = 192;
    public static final int KEY_SIZE_192BIT_IN_BYTE = KEY_SIZE_192BIT_IN_BIT / 8;
    public static final int KEY_SIZE_256BIT_IN_BIT = 256;
    public static final int KEY_SIZE_256BIT_IN_BYTE = KEY_SIZE_256BIT_IN_BIT / 8;
    public static final int SALT_SIZE_128BIT_IN_BIT = 128;
    public static final int SALT_SIZE_128BIT_IN_BYTE = SALT_SIZE_128BIT_IN_BIT / 8;

    private AesCipherMode mCipherMode = AesCipherMode.Cbc;
    private AesPaddingMode mPaddingMode = AesPaddingMode.Pkcs5;

    public AesCipherMode getCipherMode() {
        return mCipherMode;
    }

    public AesPaddingMode getPaddingMode() {
        return mPaddingMode;
    }

    public Aes setCipherMode(AesCipherMode cipherMode) {
        if (mCipherMode == cipherMode) {
            return this;
        }

        mCipherMode = cipherMode;
        Logger.getInstance(Aes.class.getSimpleName()).info(String.format(
                Locale.ROOT,
                "Set cipher mode to %s",
                cipherMode.value()
        ));
        return this;
    }

    public Aes setPaddingMode(AesPaddingMode paddingMode) {
        if (mPaddingMode == paddingMode) {
            return this;
        }

        mPaddingMode = paddingMode;
        Logger.getInstance(Aes.class.getSimpleName()).info(String.format(
                Locale.ROOT,
                "Set padding mode to %s",
                paddingMode.value()
        ));
        return this;
    }

    public byte[] decrypt(
            byte[] input,
            String password) {
        if (input == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find input to decrypt");
            return null;
        }

        if (password == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find password to decrypt");
            return null;
        }

        int encryptedDataLength = input.length - SALT_SIZE_128BIT_IN_BYTE;
        if (encryptedDataLength <= 0)
        {
            Logger.getInstance(Aes.class.getSimpleName()).error("input cipher text is malformed");
            return null;
        }

        byte[] saltInBytes = new byte[SALT_SIZE_128BIT_IN_BYTE];
        byte[] encryptedData = new byte[encryptedDataLength];
        byte[] keyInBytes = new byte[KEY_SIZE_256BIT_IN_BYTE];
        byte[] ivInBytes = new byte[IV_SIZE_128BIT_IN_BYTE];
        byte[] result = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(input);
            byteArrayInputStream.read(
                    saltInBytes,
                    0,
                    SALT_SIZE_128BIT_IN_BYTE
            );
            byteArrayInputStream.read(
                    encryptedData,
                    0,
                    encryptedDataLength
            );
            prepareDeriveBytes(
                    password,
                    saltInBytes,
                    keyInBytes,
                    ivInBytes
            );
            result = decrypt(
                    encryptedData,
                    keyInBytes,
                    ivInBytes
            );
        } catch (Exception e) {
            Logger.getInstance(Aes.class.getSimpleName()).fatal(String.format(
                    Locale.ROOT,
                    "Decrypt input with password error: %s",
                    e
            ));
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (Exception e) {
                    // Skip
                }
            }
        }
        return result;
    }

    public byte[] decrypt(
            byte[] input,
            byte[] key,
            byte[] iv) {
        if (input == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find input to decrypt");
            return null;
        }

        if (key == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find key to decrypt");
            return null;
        }

        if (iv == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find iv to decrypt");
            return null;
        }

        byte[] result = null;
        try {
            result = onDecrypt(
                    input,
                    key,
                    iv
            );
        } catch (Exception e) {
            Logger.getInstance(Aes.class.getSimpleName()).fatal(String.format(
                    Locale.ROOT,
                    "Decrypt input with key and iv error: %s",
                    e
            ));
        }
        return result;
    }

    public byte[] encrypt(
            byte[] input,
            String password) {
        if (input == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find input to encrypt");
            return null;
        }

        if (password == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find password to encrypt");
            return null;
        }

        byte[] saltInBytes = new byte[SALT_SIZE_128BIT_IN_BYTE];
        byte[] keyInBytes = new byte[KEY_SIZE_256BIT_IN_BYTE];
        byte[] ivInBytes = new byte[IV_SIZE_128BIT_IN_BYTE];
        byte[] result = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(saltInBytes);
            prepareDeriveBytes(
                    password,
                    saltInBytes,
                    keyInBytes,
                    ivInBytes
            );
            byte[] encryptedBytes = encrypt(
                    input,
                    keyInBytes,
                    ivInBytes
            );
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(saltInBytes);
            byteArrayOutputStream.write(encryptedBytes);
            result = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            Logger.getInstance(Aes.class.getSimpleName()).fatal(String.format(
                    Locale.ROOT,
                    "Encrypt input with password error: %s",
                    e
            ));
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e) {
                    // Skip
                }
            }
        }
        return result;
    }

    public byte[] encrypt(
            byte[] input,
            byte[] key,
            byte[] iv) {
        if (input == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find input to encrypt");
            return null;
        }

        if (key == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find key to encrypt");
            return null;
        }

        if (iv == null) {
            Logger.getInstance(Aes.class.getSimpleName()).warn("Can not find iv to encrypt");
            return null;
        }

        byte[] result = null;
        try {
            result = onEncrypt(
                    input,
                    key,
                    iv
            );
        } catch (Exception e) {
            Logger.getInstance(Aes.class.getSimpleName()).fatal(String.format(
                    Locale.ROOT,
                    "Encrypt input with key and iv error: %s",
                    e
            ));
        }
        return result;
    }

    private static void prepareDeriveBytes(
            String password,
            byte[] saltInBytes,
            byte[] keyInBytes,
            byte[] ivInBytes) throws Exception {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(
                password.toCharArray(),
                saltInBytes,
                1000,
                KEY_SIZE_256BIT_IN_BIT + IV_SIZE_128BIT_IN_BIT
        );
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        byte[] deriveBytes = secretKey.getEncoded();

        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(deriveBytes);
            byteArrayInputStream.read(
                    keyInBytes,
                    0,
                    KEY_SIZE_256BIT_IN_BYTE
            );
            byteArrayInputStream.read(
                    ivInBytes,
                    0,
                    IV_SIZE_128BIT_IN_BYTE
            );
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (Exception e) {
                    // Skip
                }
            }
        }
    }

    protected abstract byte[] onDecrypt(
            byte[] input,
            byte[] key,
            byte[] iv
    ) throws Exception;
    protected abstract byte[] onEncrypt(
            byte[] input,
            byte[] key,
            byte[] iv
    ) throws Exception;
}
