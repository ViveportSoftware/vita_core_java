package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

public class DummyWebUserAgentFactory extends WebUserAgentFactory {
    public DummyWebUserAgentFactory() {
        Logger.getInstance(DummyWebUserAgentFactory.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                WebUserAgentFactory.class.getSimpleName()
        ));
    }

    @Override
    protected WebUserAgent onGetWebUserAgent() {
        return new DummyWebUserAgent().setName(null);
    }
}
