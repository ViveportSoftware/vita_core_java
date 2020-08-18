package com.htc.vita.core.crypto;

import com.htc.vita.core.util.Convert;
import com.htc.vita.core.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class DefaultSha1 extends Sha1 {
    private static final int BUFFER_SIZE = 1024 * 8;

    @Override
    protected String onGenerateInHex(File file) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        InputStream inputStream = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            inputStream = new FileInputStream(file);
            int read = 0;
            while (read != -1) {
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
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(StringUtils.toBytesByUtf8(content));
        return Convert.toHexString(messageDigest.digest());
    }
}
