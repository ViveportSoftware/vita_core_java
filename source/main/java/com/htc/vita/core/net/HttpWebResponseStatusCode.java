package com.htc.vita.core.net;

import com.htc.vita.core.util.StringUtils;

import java.net.HttpURLConnection;

public enum HttpWebResponseStatusCode {
    Unknown(0),
    Accepted(HttpURLConnection.HTTP_ACCEPTED),
    BadGateway(HttpURLConnection.HTTP_BAD_GATEWAY),
    BadMethod(HttpURLConnection.HTTP_BAD_METHOD),
    BadRequest(HttpURLConnection.HTTP_BAD_REQUEST),
    ClientTimeout(HttpURLConnection.HTTP_CLIENT_TIMEOUT),
    Conflict(HttpURLConnection.HTTP_CONFLICT),
    Created(HttpURLConnection.HTTP_CREATED),
    EntityTooLarge(HttpURLConnection.HTTP_ENTITY_TOO_LARGE),
    Forbidden(HttpURLConnection.HTTP_FORBIDDEN),
    GatewayTimeout(HttpURLConnection.HTTP_GATEWAY_TIMEOUT),
    Gone(HttpURLConnection.HTTP_GONE),
    InternalError(HttpURLConnection.HTTP_INTERNAL_ERROR),
    LengthRequired(HttpURLConnection.HTTP_LENGTH_REQUIRED),
    MovedPerm(HttpURLConnection.HTTP_MOVED_PERM),
    MovedTemp(HttpURLConnection.HTTP_MOVED_TEMP),
    MultiChoice(HttpURLConnection.HTTP_MULT_CHOICE),
    NotAcceptable(HttpURLConnection.HTTP_NOT_ACCEPTABLE),
    NotAuthoritative(HttpURLConnection.HTTP_NOT_AUTHORITATIVE),
    NotFound(HttpURLConnection.HTTP_NOT_FOUND),
    NotImplemented(HttpURLConnection.HTTP_NOT_IMPLEMENTED),
    NotModified(HttpURLConnection.HTTP_NOT_MODIFIED),
    NoContent(HttpURLConnection.HTTP_NO_CONTENT),
    Ok(HttpURLConnection.HTTP_OK),
    Partial(HttpURLConnection.HTTP_PARTIAL),
    PaymentRequired(HttpURLConnection.HTTP_PAYMENT_REQUIRED),
    PreconFailed(HttpURLConnection.HTTP_PRECON_FAILED),
    ProxyAuth(HttpURLConnection.HTTP_PROXY_AUTH),
    ReqTooLong(HttpURLConnection.HTTP_REQ_TOO_LONG),
    Reset(HttpURLConnection.HTTP_RESET),
    SeeOther(HttpURLConnection.HTTP_SEE_OTHER),
    Unauthorized(HttpURLConnection.HTTP_UNAUTHORIZED),
    Unavailable(HttpURLConnection.HTTP_UNAVAILABLE),
    UnsupportedType(HttpURLConnection.HTTP_UNSUPPORTED_TYPE),
    UseProxy(HttpURLConnection.HTTP_USE_PROXY),
    Version(HttpURLConnection.HTTP_VERSION);

    private final int mValue;

    HttpWebResponseStatusCode(int value) {
        mValue = value;
    }

    public static HttpWebResponseStatusCode fromValue(int value) {
        HttpWebResponseStatusCode[] values = values();
        for (HttpWebResponseStatusCode statusCode : values) {
            if (statusCode == null) {
                continue;
            }
            if (statusCode.equals(value)) {
                return statusCode;
            }
        }
        throw new IllegalArgumentException(StringUtils.rootLocaleFormat(
                "Can not find suitable status code from value: %d",
                value
        ));
    }

    public int value() {
        return mValue;
    }

    public boolean equals(int value) {
        return mValue == value;
    }
}
