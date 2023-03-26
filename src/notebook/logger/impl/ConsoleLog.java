package notebook.logger.impl;

import notebook.logger.Logger;

import java.time.LocalDateTime;

public class ConsoleLog implements Logger {
    @Override
    public void log(String text) {
        System.err.println("\n" + LocalDateTime.now() + ": " + text);
    }
}
