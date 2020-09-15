package com.htc.vita.core.crypto;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.Convert;
import com.htc.vita.core.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class DefaultSha1 extends Sha1 {
    private static final int BUFFER_SIZE = 1024 * 8;
    private static final String DIGEST_ALGORITHM_SHA_1 = "SHA-1";

    @Override
    protected String onGenerateInBase64(
            File file,
            CancellationToken cancellationToken) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA_1);
        InputStream inputStream = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            inputStream = new FileInputStream(file);
            int read = 0;
            while (read != -1) {
                if (cancellationToken.isCancellationRequested()) {
                    Logger.getInstance(DefaultSha1.class.getSimpleName()).warn(StringUtils.rootLocaleFormat(
                            "Generating checksum from %s is cancelled",
                            file.getName()
                    ));
                    return "";
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
            return Convert.toBase64String(messageDigest.digest());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Override
    protected String onGenerateInBase64(String content) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA_1);
        messageDigest.update(StringUtils.toBytesByUtf8(content));
        return Convert.toBase64String(messageDigest.digest());
    }

    @Override
    protected String onGenerateInHex(
            File file,
            CancellationToken cancellationToken) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA_1);
        InputStream inputStream = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            inputStream = new FileInputStream(file);
            int read = 0;
            while (read != -1) {
                if (cancellationToken.isCancellationRequested()) {
                    Logger.getInstance(DefaultSha1.class.getSimpleName()).warn(StringUtils.rootLocaleFormat(
                            "Generating checksum from %s is cancelled",
                            file.getName()
                    ));
                    return "";
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
            return Convert.toHexString(messageDigest.digest());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Override
    protected String onGenerateInHex(String content) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA_1);
        messageDigest.update(StringUtils.toBytesByUtf8(content));
        return Convert.toHexString(messageDigest.digest());
    }
}
