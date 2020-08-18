package com.htc.vita.core.crypto;

public class DefaultAesFactory extends AesFactory {
    @Override
    protected Aes onGet(
            AesCipherMode cipherMode,
            AesPaddingMode paddingMode) {
        return new DefaultAes()
                .setCipherMode(cipherMode)
                .setPaddingMode(paddingMode);
    }
}
