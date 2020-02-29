package com.htc.vita.core.net;

public class DummyWebUserAgent extends WebUserAgent {
    @Override
    protected WebUserAgent onSetName(String name) {
        return this;
    }

    @Override
    public String toString() {
        String javaRuntimeName = System.getProperty("java.runtime.name");
        String javaRuntimeVersion = System.getProperty("java.runtime.version");
        return String.format("JRE/%s (%s)", javaRuntimeVersion, javaRuntimeName);
    }
}
