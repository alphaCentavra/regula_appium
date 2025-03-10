package mitmproxy;

import io.appium.mitmproxy.InterceptedMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

public class InterceptedMessages {
    @Accessors(chain = true)
    @Getter@Setter
    private Date timestamp;

    @Accessors(chain = true)
    @Getter@Setter
    private InterceptedMessage interceptedMessage;
}