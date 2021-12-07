package lesson4Test;


import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class LogTestTime implements AfterTestExecutionCallback, BeforeTestExecutionCallback {
    private static final AtomicReference<Logger> logger;

    static {
        logger = new AtomicReference<Logger>();
        logger.set((Logger) LoggerFactory.getLogger(TriangleTest.class));
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        long currentTimeMillis = System.currentTimeMillis();
        long startTime = (long) extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("start time");

        logger.get().info("Test: " + extensionContext.getDisplayName() + " has Duration " + (currentTimeMillis - startTime));
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("start time", System.currentTimeMillis());
    }
}