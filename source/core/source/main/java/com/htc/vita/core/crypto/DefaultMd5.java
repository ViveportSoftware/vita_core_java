package com.htc.vita.core.crypto;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.Convert;
import com.htc.vita.core.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class DefaultMd5 extends Md5 {
    private static final int BUFFER_SIZE = 1024 * 8;
    private static final String DIGEST_ALGORITHM_MD5 = "MD5";

    private byte[] generateInBytes(
            File file,
            CancellationToken cancellationToken) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM_MD5);
        InputStream inputStream = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            inputStream = new FileInputStream(file);
            int read = 0;
            while (read != -1) {
                if (cancellationToken.isCancellationRequested()) {
                    Logger.getInstance(DefaultMd5.class.getSimpleName()).warn(StringUtils.rootLocaleFormat(
                            "Generating checksum from \"%s\" is cancelled",
                            file.getName()
                    ));
                    return null;
                }

                read = inputStream.read(buffer);
                if (read > 0) {
                    messageDigest.update(
                            buffer,
                            0,
                            read
                    );
                }
            }
            return messageDigest.digest();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private byte[] generateInBytes(String content) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM_MD5);
        messageDigest.update(StringUtils.toBytesByUtf8(content));
        return messageDigest.digest();
    }

    @Override
    protected String onGenerateInBase64(
            File file,
            CancellationToken cancellationToken) throws Exception {
        byte[] data = generateInBytes(
                file,
                cancellationToken
        );
        if (data == null) {
            return "";
        }
        return Convert.toBase64String(data);
    }

    @Override
    protected String onGenerateInBase64(String content) throws Exception {
        return Convert.toBase64String(generateInBytes(content));
    }

    @Override
    protected String onGenerateInHex(
            File file,
            CancellationToken cancellationToken) throws Exception {
        byte[] data = generateInBytes(
                file,
                cancellationToken
        );
        if (data == null) {
            return "";
        }
        return Convert.toHexString(data);
    }

    @Override
    protected String onGenerateInHex(String content) throws Exception {
        return Convert.toHexString(generateInBytes(content));
    }
}
