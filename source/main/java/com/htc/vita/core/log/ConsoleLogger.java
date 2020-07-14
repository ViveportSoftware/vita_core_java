package com.htc.vita.core.log;

public class ConsoleLogger extends Logger {
    protected ConsoleLogger(String name) {
        super(name);
    }

    @Override
    protected void onDebug(
            String tag,
            String message) {
        onDebug(
                tag,
                message,
                null
        );
    }

    @Override
    protected void onDebug(
            String tag,
            String message,
            Exception exception) {
        if (exception == null)
        {
            System.out.printf(
                    "[%s][debug][%s] %s%n",
                    getName(),
                    tag,
                    message
            );
        }
        else
        {
            System.out.printf(
                    "[%s][debug][%s] %s, %s%n",
                    getName(),
                    tag,
                    message,
                    exception.toString()
            );
        }
    }

    @Override
    protected void onError(
            String tag,
            String message) {
        onError(
                tag,
                message,
                null
        );
    }

    @Override
    protected void onError(
            String tag,
            String message,
            Exception exception) {
        if (exception == null)
        {
            System.out.printf(
                    "[%s][error][%s] %s%n",
                    getName(),
                    tag,
                    message
            );
        }
        else
        {
            System.out.printf(
                    "[%s][error][%s] %s, %s%n",
                    getName(),
                    tag,
                    message,
                    exception.toString()
            );
        }
    }

    @Override
    protected void onFatal(
            String tag,
            String message) {
        onFatal(
                tag,
                message,
                null
        );
    }

    @Override
    protected void onFatal(
            String tag,
            String message,
            Exception exception) {
        if (exception == null)
        {
            System.out.printf(
                    "[%s][fatal][%s] %s%n",
                    getName(),
                    tag,
                    message
            );
        }
        else
        {
            System.out.printf(
                    "[%s][fatal][%s] %s, %s%n",
                    getName(),
                    tag,
                    message,
                    exception.toString()
            );
        }
    }

    @Override
    protected void onInfo(
            String tag,
            String message) {
        onInfo(
                tag,
                message,
                null
        );
    }

    @Override
    protected void onInfo(
            String tag,
            String message,
            Exception exception) {
        if (exception == null)
        {
            System.out.printf(
                    "[%s][info][%s] %s%n",
                    getName(),
                    tag,
                    message
            );
        }
        else
        {
            System.out.printf(
                    "[%s][info][%s] %s, %s%n",
                    getName(),
                    tag,
                    message,
                    exception.toString()
            );
        }
    }

    @Override
    protected void onShutdown() {
        System.err.println("Shutdown the logger ...");
    }

    @Override
    protected void onTrace(
            String tag,
            String message) {
        onTrace(
                tag,
                message,
                null
        );
    }

    @Override
    protected void onTrace(
            String tag,
            String message,
            Exception exception) {
        if (exception == null)
        {
            System.out.printf(
                    "[%s][trace][%s] %s%n",
                    getName(),
                    tag,
                    message
            );
        }
        else
        {
            System.out.printf(
                    "[%s][trace][%s] %s, %s%n",
                    getName(),
                    tag,
                    message,
                    exception.toString()
            );
        }
    }

    @Override
    protected void onWarn(
            String tag,
            String message) {
        onWarn(
                tag,
                message,
                null
        );
    }

    @Override
    protected void onWarn(
            String tag,
            String message,
            Exception exception) {
        if (exception == null)
        {
            System.out.printf(
                    "[%s][warn][%s] %s%n",
                    getName(),
                    tag,
                    message
            );
        }
        else
        {
            System.out.printf(
                    "[%s][warn][%s] %s, %s%n",
                    getName(),
                    tag,
                    message,
                    exception.toString()
            );
        }
    }
}
