package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;

public class DummyWebUserAgentFactory extends WebUserAgentFactory {
    public DummyWebUserAgentFactory() {
        Logger.getInstance(DummyWebUserAgentFactory.class.getSimpleName()).error(String.format(
                "You are using dummy %s instance!!",
                WebUserAgentFactory.class.getSimpleName()
        ));
    }

    @Override
    protected WebUserAgent onGetWebUserAgent() {
        return new DummyWebUserAgent().setName(null);
    }
}
