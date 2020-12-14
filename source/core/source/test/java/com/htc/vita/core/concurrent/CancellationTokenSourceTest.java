package com.htc.vita.core.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CancellationException;

public class CancellationTokenSourceTest {
    @Test
    public void default_0_isCancellationRequested() {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        Assert.assertFalse(cancellationTokenSource.isCancellationRequested());
    }

    @Test
    public void default_1_getToken() {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        CancellationToken cancellationToken = cancellationTokenSource.getToken();
        Assert.assertNotNull(cancellationToken);
    }

    @Test
    public void default_2_cancel() {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        cancellationTokenSource.cancel();
        Assert.assertTrue(cancellationTokenSource.isCancellationRequested());
    }

    @Test
    public void default_3_close() {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        cancellationTokenSource.cancel();
        Assert.assertTrue(cancellationTokenSource.isCancellationRequested());
        Assert.assertNotNull(cancellationTokenSource.getToken());
        cancellationTokenSource.close();
        IllegalStateException illegalStateException = null;
        try {
            cancellationTokenSource.isCancellationRequested();
        } catch (IllegalStateException e) {
            illegalStateException = e;
        } finally {
            Assert.assertNotNull(illegalStateException);
        }
        illegalStateException = null;
        try {
            cancellationTokenSource.getToken();
        } catch (IllegalStateException e) {
            illegalStateException = e;
        } finally {
            Assert.assertNotNull(illegalStateException);
        }
    }

    @Test
    public void cancellationToken_0_canBeCanceled() {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        CancellationToken cancellationToken = cancellationTokenSource.getToken();
        Assert.assertNotNull(cancellationToken);
        Assert.assertTrue(cancellationToken.canBeCanceled());
    }

    @Test
    public void cancellationToken_0_canBeCanceled_withNone() {
        CancellationToken cancellationToken = CancellationToken.NONE;
        Assert.assertNotNull(cancellationToken);
        Assert.assertFalse(cancellationToken.canBeCanceled());
    }

    @Test
    public void cancellationToken_1_isCancellationRequested() {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        CancellationToken cancellationToken = cancellationTokenSource.getToken();
        Assert.assertNotNull(cancellationToken);
        Assert.assertFalse(cancellationToken.isCancellationRequested());
        cancellationTokenSource.cancel();
        Assert.assertTrue(cancellationToken.isCancellationRequested());
    }

    @Test
    public void cancellationToken_2_throwIfCancellationRequested() {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        CancellationToken cancellationToken = cancellationTokenSource.getToken();
        Assert.assertNotNull(cancellationToken);
        CancellationException cancellationException = null;
        try {
            cancellationToken.throwIfCancellationRequested();
        } catch (CancellationException e) {
            cancellationException = e;
        } finally {
            Assert.assertNull(cancellationException);
        }
        cancellationTokenSource.cancel();
        try {
            cancellationToken.throwIfCancellationRequested();
        } catch (CancellationException e) {
            cancellationException = e;
        } finally {
            Assert.assertNotNull(cancellationException);
        }
    }
}
