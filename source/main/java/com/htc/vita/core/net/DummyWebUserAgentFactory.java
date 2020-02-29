package com.htc.vita.core.net;

public class DummyWebUserAgentFactory extends WebUserAgentFactory {

    @Override
    protected WebUserAgent onGetWebUserAgent() {
        return new DummyWebUserAgent().setName(null);
    }
}
