package com.htc.vita.core.concurrent;

import java.io.Closeable;

public class CancellationTokenSource implements Closeable {
    private final Object mLock = new Object();

    private boolean mIsCancellationRequested;
    private boolean mIsClosed;

    public void cancel() {
        synchronized (mLock) {
            if (mIsCancellationRequested) {
                return;
            }

            mIsCancellationRequested = true;
        }
    }

    @Override
    public void close() {
        synchronized (mLock) {
            if (mIsClosed) {
                return;
            }

            mIsClosed = true;
        }
    }

    public CancellationToken getToken() {
        synchronized (mLock) {
            throwIfClosed();
            return new CancellationToken(this);
        }
    }

    public boolean isCancellationRequested() {
        synchronized (mLock) {
            throwIfClosed();
            return mIsCancellationRequested;
        }
    }

    private void throwIfClosed() {
        if (mIsClosed) {
            throw new IllegalStateException(String.format(
                    "%s is already closed",
                    CancellationTokenSource.class.getSimpleName()
            ));
        }
    }
}
