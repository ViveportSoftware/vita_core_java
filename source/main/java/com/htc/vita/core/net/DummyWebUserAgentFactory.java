package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;

import java.util.Locale;

public class DummyWebUserAgentFactory extends WebUserAgentFactory {
    public DummyWebUserAgentFactory() {
        Logger.getInstance(DummyWebUserAgentFactory.class.getSimpleName()).error(String.format(
                Locale.ROOT,
                "You are using dummy %s instance!!",
                WebUserAgentFactory.class.getSimpleName()
        ));
    }

    @Override
    protected WebUserAgent onGetWebUserAgent() {
        return new DummyWebUserAgent().setName(null);
    }
}
