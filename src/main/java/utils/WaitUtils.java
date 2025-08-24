package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class WaitUtils {

    private static final Logger logger = LogManager.getLogger();

    private final int defaultWaitTime;

    private final int waitTimeInMillis = 500;

    public WaitUtils(int defaultWaitTime) {
        this.defaultWaitTime = defaultWaitTime;
    }

    public WebElement waitForElementVisible(WebElement element, int timeout) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeout * 1000L) {
            try {
                if (element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {
                logger.info("Элемент не виден или не существует, продолжаем ждать");
            }
            sleep(waitTimeInMillis);
        }
        throw new TimeoutException("Элемент не стал видимым в течение " + timeout + " секунд");
    }

    public void waitForElementNotVisible(WebElement element, int timeout) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeout * 1000L) {
            try {
                if (!element.isDisplayed()) {
                    logger.info("Элемент не виден - успех!");
                    return;
                }
            } catch (StaleElementReferenceException e) {
                logger.info("Элемент больше не существует в DOM - успех!");
                return;
            } catch (NotFoundException e) {
                logger.info("Элемент не найден -> считаем его невидимым");
                return;
            }
            sleep(waitTimeInMillis);
        }
        throw new TimeoutException("Элемент остался видимым в течение " + timeout + " секунд");
    }

    public void waitForElementNotVisible(WebElement element) {
        waitForElementNotVisible(element, defaultWaitTime);
    }

    public boolean isElementVisible(WebElement element, int timeout) {
        try {
            waitForElementVisible(element, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(WebElement element) {
        return isElementVisible(element, defaultWaitTime);
    }

    /**
     * Приостанавливает выполнение текущего потока на указанное время.
     *
     * @param millis длительность ожидания в миллисекундах
     * @throws IllegalArgumentException если millis отрицательно
     * @throws SleepInterruptedException если поток был прерван во время ожидания
     */
    private void sleep(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Время сна не может быть отрицательным: " + millis);
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SleepInterruptedException("Ожидание было прервано", e);
        }
    }
}