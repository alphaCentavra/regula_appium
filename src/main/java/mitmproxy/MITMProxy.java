package mitmproxy;

import io.appium.mitmproxy.InterceptedMessage;
import io.appium.mitmproxy.MitmproxyJava;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class MITMProxy {
    private static final Logger logger = LogManager.getLogger();
    @Getter
    private final List<InterceptedMessages> networkCalls = new ArrayList<>();
    private static MITMProxy proxyInstance = null;
    private MitmproxyJava proxy;

    // применение синглтон паттерна
    private MITMProxy() {
        startProxyListener();
    }

    public static MITMProxy getProxy() {
        if (proxyInstance == null)
            proxyInstance = new MITMProxy();
        return proxyInstance;
    }

    private void startProxyListener() {
        List<String> extraMitmproxyParams = Arrays.asList("--showhost", "<domain-name-filter>");
        int mitmproxyPort = 8080;

        // использовать свой путь к mitmdump.exe
        this.proxy = new MitmproxyJava("C:\\Users\\vasilyuk.n\\AppData\\Local\\Programs\\Python\\Python313\\Scripts\\mitmdump.exe", (InterceptedMessage message) -> {
            InterceptedMessages interceptedMessage = new InterceptedMessages()
                    .setTimestamp(new Date())
                    .setInterceptedMessage(message);
            networkCalls.add(interceptedMessage);

            logger.info("Captured Network Request at: " + interceptedMessage.getTimestamp());
            logger.info("Request Details: " + message);

            return message;
        }, mitmproxyPort, extraMitmproxyParams);

        try {
            this.proxy.start();
        } catch (IOException | TimeoutException ex) {
            throw new RuntimeException("Failed to Start Proxy: " + ex.getMessage());
        }

        logger.info("Proxy Listener Started Successfully!");
    }

    public void stopProxyListener() {
        logger.info("Stopping MITM Proxy Listener...");
        try {
            this.proxy.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException("Не удалось остановить прокси: " + e.getMessage());
        }
        logger.info("Proxy Listener Stopped Successfully!");
    }

    public void printCapturedLogs() {
        logger.info("Printing Captured Network Logs...");

        if (networkCalls.isEmpty()) {
            logger.info("No network requests were intercepted.");
            return;
        }

        for (InterceptedMessages msg : networkCalls) {
            logger.info("Captured Request at: " + msg.getTimestamp());
            logger.info("Request Details: " + msg.getInterceptedMessage());
            logger.info("--------------------------------------------------");
        }
    }
}
