package utils;


/**
 * Кастомное исключение для лучшей семантики.
 */
public class SleepInterruptedException extends RuntimeException {
    public SleepInterruptedException(String message, InterruptedException cause) {
        super(message, cause);
    }
}