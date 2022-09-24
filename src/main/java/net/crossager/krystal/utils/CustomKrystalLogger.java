package net.crossager.krystal.utils;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CustomKrystalLogger extends Logger {
    public CustomKrystalLogger() {
        super("Krystal", null);
    }

    @Override
    public void log(LogRecord record) {
        PrintStream out = isWarning(record.getLevel()) ? System.err : System.out;
        out.printf("[%s %s | %s] %s\n",
                this.getName(),
                new SimpleDateFormat("MM/dd HH:mm:ss", Locale.GERMANY).format(System.currentTimeMillis()),
                record.getLevel().getName(),
                record.getMessage());
    }

    private boolean isWarning(Level level) {
        return level == Level.WARNING || level == Level.SEVERE;
    }
}
