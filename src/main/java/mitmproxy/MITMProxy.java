package mitmproxy;

import io.appium.mitmproxy.InterceptedMessage;
import io.appium.mitmproxy.MitmproxyJava;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * MITM Proxy Utility for Capturing Network Logs in Automation Framework.
 */
public class MITMProxy {
    @Getter
    private final List<InterceptedMessages> networkCalls = new ArrayList<>();

    private static MITMProxy proxyInstance = null;
    private MitmproxyJava proxy;

    // Singleton Pattern to Ensure Single Instance
    private MITMProxy() {
        startProxyListener();
    }

    public static MITMProxy getProxy() {
        if (proxyInstance == null)
            proxyInstance = new MITMProxy();
        return proxyInstance;
    }

    /**
     * Starts MITM Proxy and Listens for Network Traffic
     */
    private void startProxyListener() {
        System.out.println("Starting MITM Proxy Listener...");

        List<String> extraMitmproxyParams = Arrays.asList("--showhost", "<domain-name-filter>");
        int mitmproxyPort = 8080;

        // remember to set local OS proxy settings in the Network Preferences
        this.proxy = new MitmproxyJava("C:\\Users\\vasilyuk.n\\AppData\\Local\\Programs\\Python\\Python313\\Scripts\\mitmdump.exe", (InterceptedMessage message) -> {
            InterceptedMessages interceptedMessage = new InterceptedMessages()
                    .setTimestamp(new Date())
                    .setInterceptedMessage(message);
            networkCalls.add(interceptedMessage);

            // Log each intercepted message
            System.out.println("Captured Network Request at: " + interceptedMessage.getTimestamp());
            System.out.println("Request Details: " + message);

            return message;
        }, mitmproxyPort, extraMitmproxyParams);

        try {
//            // Kill existing process on the same port if running
//            String processId = ProcessExecutor.executeCommandSync("lsof -t -i:" + mitmproxyPort + " -sTCP:LISTEN").trim();
//            if (!processId.isEmpty())
//                ProcessExecutor.executeCommandSync("kill -9 " + processId);
//
            this.proxy.start();
        } catch (IOException | TimeoutException ex) {
            throw new RuntimeException("Failed to Start Proxy: " + ex.getMessage());
        }

        System.out.println("Proxy Listener Started Successfully!");
    }

    /**
     * Retrieves MITM Dump Path
     */
//    private String getMitmDumpPath() {
//        String result = ProcessExecutor.executeCommandSync("whereis mitmdump");
//        return result.split("mitmdump: ")[1].split(" ")[0].trim();
//    }

    /**
     * Stops MITM Proxy
     */
    public void stopProxyListener() {
        System.out.println("Stopping MITM Proxy Listener...");
        try {
            this.proxy.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to Stop Proxy: " + e.getMessage());
        }
        System.out.println("Proxy Listener Stopped Successfully!");
    }

    /**
     * Prints All Captured Network Logs
     */
    public void printCapturedLogs() {
        System.out.println("Printing Captured Network Logs...");

        if (networkCalls.isEmpty()) {
            System.out.println("No network requests were intercepted.");
            return;
        }

        for (InterceptedMessages msg : networkCalls) {
            System.out.println("Captured Request at: " + msg.getTimestamp());
            System.out.println("Request Details: " + msg.getInterceptedMessage());
            System.out.println("--------------------------------------------------");
        }
    }
}
