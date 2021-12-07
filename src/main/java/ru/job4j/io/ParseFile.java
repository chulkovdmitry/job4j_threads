package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public StringBuilder content(Predicate<Character> filter) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = inputStream.read()) > 0) {
                if (filter.test((char) data)) {
                    stringBuilder.append(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    public StringBuilder getContent() throws IOException {
        return content(data -> true);
    }

    public StringBuilder getContentWithoutUnicode() throws IOException {
        return content(data -> data < 0x80);
    }
}