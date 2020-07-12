package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

public abstract class WebUserAgent {
    public WebUserAgent setName(String name) {
        if (StringUtils.isNullOrWhiteSpace(name)) {
            return this;
        }

        WebUserAgent result = null;
        try {
            result = onSetName(name);
        } catch (Exception e) {
            Logger.getInstance(WebUserAgent.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract WebUserAgent onSetName(String name);
}
