package com.htc.vita.core.concurrent;

import java.util.concurrent.CancellationException;

public class CancellationToken {
    public static final CancellationToken NONE = new CancellationToken();

    private final CancellationTokenSource mCancellationTokenSource;

    public CancellationToken() {
        this(null);
    }

    /* package */ CancellationToken(CancellationTokenSource source) {
        mCancellationTokenSource = source;
    }

    public boolean canBeCanceled() {
        return mCancellationTokenSource != null;
    }

    public boolean isCancellationRequested() {
        if (mCancellationTokenSource == null) {
            return false;
        }
        return mCancellationTokenSource.isCancellationRequested();
    }

    public void throwIfCancellationRequested() throws CancellationException {
        if (isCancellationRequested()) {
            throw new CancellationException();
        }
    }
}
