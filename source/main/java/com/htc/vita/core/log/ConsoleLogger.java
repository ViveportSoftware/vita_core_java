package com.htc.vita.core.log;

public class ConsoleLogger extends Logger {
    protected ConsoleLogger(String name) {
        super(name);
    }

    @Override
    protected void onDebug(String tag, String message) {
        System.out.println("[" + getName() + "][debug][" + tag + "] " + message);
    }

    @Override
    protected void onDebug(String tag, String message, Exception exception) {
        if (exception == null)
        {
            System.out.println("[" + getName() + "][debug][" + tag + "] " + message);
        }
        else
        {
            System.out.println("[" + getName() + "][debug][" + tag + "] " + message + ", " + exception.toString());
        }
    }

    @Override
    protected void onError(String tag, String message) {
        System.out.println("[" + getName() + "][error][" + tag + "] " + message);
    }

    @Override
    protected void onError(String tag, String message, Exception exception) {
        if (exception == null)
        {
            System.out.println("[" + getName() + "][error][" + tag + "] " + message);
        }
        else
        {
            System.out.println("[" + getName() + "][error][" + tag + "] " + message + ", " + exception.toString());
        }
    }

    @Override
    protected void onFatal(String tag, String message) {
        System.out.println("[" + getName() + "][fatal][" + tag + "] " + message);
    }

    @Override
    protected void onFatal(String tag, String message, Exception exception) {
        if (exception == null)
        {
            System.out.println("[" + getName() + "][fatal][" + tag + "] " + message);
        }
        else
        {
            System.out.println("[" + getName() + "][fatal][" + tag + "] " + message + ", " + exception.toString());
        }
    }

    @Override
    protected void onInfo(String tag, String message) {
        System.out.println("[" + getName() + "][info][" + tag + "] " + message);
    }

    @Override
    protected void onInfo(String tag, String message, Exception exception) {
        if (exception == null)
        {
            System.out.println("[" + getName() + "][info][" + tag + "] " + message);
        }
        else
        {
            System.out.println("[" + getName() + "][info][" + tag + "] " + message + ", " + exception.toString());
        }
    }

    @Override
    protected void onShutdown() {
        System.err.println("Shutdown the logger ...");
    }

    @Override
    protected void onTrace(String tag, String message) {
        System.out.println("[" + getName() + "][trace][" + tag + "] " + message);
    }

    @Override
    protected void onTrace(String tag, String message, Exception exception) {
        if (exception == null)
        {
            System.out.println("[" + getName() + "][trace][" + tag + "] " + message);
        }
        else
        {
            System.out.println("[" + getName() + "][trace][" + tag + "] " + message + ", " + exception.toString());
        }
    }

    @Override
    protected void onWarn(String tag, String message) {
        System.out.println("[" + getName() + "][warn][" + tag + "] " + message);
    }

    @Override
    protected void onWarn(String tag, String message, Exception exception) {
        if (exception == null)
        {
            System.out.println("[" + getName() + "][warn][" + tag + "] " + message);
        }
        else
        {
            System.out.println("[" + getName() + "][warn][" + tag + "] " + message + ", " + exception.toString());
        }
    }
}
