package com.htc.vita.core.log;

import org.junit.Test;

public class LoggerTest {
    @Test
    public void default_0_debug() {
        Logger.getInstance(LoggerTest.class.getSimpleName()).debug("log message from debug");
        Logger.getInstance(LoggerTest.class.getSimpleName()).debug("log message from debug", new Exception("log exception from debug"));
    }

    @Test
    public void default_1_error() {
        Logger.getInstance(LoggerTest.class.getSimpleName()).error("log message from error");
        Logger.getInstance(LoggerTest.class.getSimpleName()).error("log message from error", new Exception("log exception from error"));
    }

    @Test
    public void default_2_fatal() {
        Logger.getInstance(LoggerTest.class.getSimpleName()).fatal("log message from fatal");
        Logger.getInstance(LoggerTest.class.getSimpleName()).fatal("log message from fatal", new Exception("log exception from fatal"));
    }

    @Test
    public void default_3_info() {
        Logger.getInstance(LoggerTest.class.getSimpleName()).info("log message from info");
        Logger.getInstance(LoggerTest.class.getSimpleName()).info("log message from info", new Exception("log exception from info"));
    }

    @Test
    public void default_4_trace() {
        Logger.getInstance(LoggerTest.class.getSimpleName()).trace("log message from trace");
        Logger.getInstance(LoggerTest.class.getSimpleName()).trace("log message from trace", new Exception("log exception from trace"));
    }

    @Test
    public void default_5_warn() {
        Logger.getInstance(LoggerTest.class.getSimpleName()).trace("log message from warn");
        Logger.getInstance(LoggerTest.class.getSimpleName()).trace("log message from warn", new Exception("log exception from warn"));
    }
}
