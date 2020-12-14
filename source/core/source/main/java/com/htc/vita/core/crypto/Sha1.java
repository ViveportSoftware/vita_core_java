package com.htc.vita.core.crypto;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import com.htc.vita.core.util.TypeRegistry;

import java.io.File;

public abstract class Sha1 {
    static {
        TypeRegistry.registerDefault(
                Sha1.class,
                DefaultSha1.class
        );
    }

    public static <T extends Sha1> void register(Class<T> clazz) {
        TypeRegistry.register(
                Sha1.class,
                clazz
        );
    }

    public static Sha1 getInstance() {
        return TypeRegistry.getInstance(Sha1.class);
    }

    public static <T extends Sha1> Sha1 getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                Sha1.class,
                clazz
        );
    }

    public String generateInBase64(File file) {
        return generateInBase64(
                file,
                null
        );
    }

    public String generateInBase64(
            File file,
            CancellationToken cancellationToken) {
        if (file == null || !file.isFile()) {
            return "";
        }

        CancellationToken realCancellationToken = cancellationToken;
        if (realCancellationToken == null) {
            realCancellationToken = CancellationToken.NONE;
        }
        String result = "";
        try {
            result = onGenerateInBase64(
                    file,
                    realCancellationToken
            );
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public String generateInBase64(String content) {
        if (content == null) {
            return "";
        }

        String result = "";
        try {
            result = onGenerateInBase64(content);
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public String generateInHex(File file) {
        return generateInHex(
                file,
                null
        );
    }

    public String generateInHex(
            File file,
            CancellationToken cancellationToken) {
        if (file == null || !file.isFile()) {
            return "";
        }

        CancellationToken realCancellationToken = cancellationToken;
        if (realCancellationToken == null) {
            realCancellationToken = CancellationToken.NONE;
        }
        String result = "";
        try {
            result = onGenerateInHex(
                    file,
                    realCancellationToken
            );
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public String generateInHex(String content) {
        if (content == null) {
            return "";
        }

        String result = "";
        try {
            result = onGenerateInHex(content);
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public boolean validateInBase64(
            File file,
            String checksum) {
        return validateInBase64(
                file,
                checksum,
                null
        );
    }

    public boolean validateInBase64(
            File file,
            String checksum,
            CancellationToken cancellationToken) {
        if (file == null || !file.isFile() || StringUtils.isNullOrWhiteSpace(checksum)) {
            return false;
        }

        CancellationToken realCancellationToken = cancellationToken;
        if (realCancellationToken == null) {
            realCancellationToken = CancellationToken.NONE;
        }
        boolean result = false;
        try {
            result = checksum.equalsIgnoreCase(onGenerateInBase64(
                    file,
                    realCancellationToken
            ));
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public boolean validateInBase64(
            String content,
            String checksum) {
        if (content == null || StringUtils.isNullOrWhiteSpace(checksum)) {
            return false;
        }

        boolean result = false;
        try {
            result = checksum.equalsIgnoreCase(onGenerateInBase64(content));
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public boolean validateInHex(
            File file,
            String checksum) {
        return validateInHex(
                file,
                checksum,
                null
        );
    }

    public boolean validateInHex(
            File file,
            String checksum,
            CancellationToken cancellationToken) {
        if (file == null || !file.isFile() || StringUtils.isNullOrWhiteSpace(checksum)) {
            return false;
        }

        CancellationToken realCancellationToken = cancellationToken;
        if (realCancellationToken == null) {
            realCancellationToken = CancellationToken.NONE;
        }
        boolean result = false;
        try {
            result = checksum.equalsIgnoreCase(onGenerateInHex(
                    file,
                    realCancellationToken
            ));
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public boolean validateInHex(
            String content,
            String checksum) {
        if (content == null || StringUtils.isNullOrWhiteSpace(checksum)) {
            return false;
        }

        boolean result = false;
        try {
            result = checksum.equalsIgnoreCase(onGenerateInHex(content));
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract String onGenerateInBase64(
            File file,
            CancellationToken cancellationToken
    ) throws Exception;
    protected abstract String onGenerateInBase64(String content) throws Exception;
    protected abstract String onGenerateInHex(
            File file,
            CancellationToken cancellationToken
    ) throws Exception;
    protected abstract String onGenerateInHex(String content) throws Exception;
}
