package net.crossager.krystal.utils;

import java.io.IOException;
import java.io.InputStream;

public class ResourceAsString {
    private final String string;

    public ResourceAsString(String path) {
        InputStream inputStream = ResourceAsString.class.getResourceAsStream(path);
        if (inputStream == null) throw new IllegalStateException("Resource not found: " + path);
        try {
            this.string = new String(inputStream.readAllBytes());
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String string() {
        return string;
    }
}
