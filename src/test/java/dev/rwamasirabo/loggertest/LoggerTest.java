package dev.rwamasirabo.loggertest;

import dev.rwamasirabo.utilities.Logger;
import jdk.jfr.internal.LogLevel;
import org.junit.jupiter.api.Test;

public class LoggerTest {
    @Test
    void info_log_test(){
        Logger.log("testing", LogLevel.INFO);
        Logger.log("Hello", LogLevel.DEBUG);
    }
}
