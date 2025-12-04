package com.example.aoc_2025;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Objects;

public class FileUtils {

    public static final String AOC_FILE = "aoc";
    public static final String EX_SUFFIX = "_ex";
    public static final String FILE_TYPE = ".txt";

    public static File get(int n) {
        return getFileFromClasspath(
                AOC_FILE + n + "/" + AOC_FILE + FILE_TYPE
        );
    }

    public static File getExample(int n) {
        return getFileFromClasspath(
                AOC_FILE + n + "/" + AOC_FILE + EX_SUFFIX + FILE_TYPE
        );
    }

    private static File getFileFromClasspath(String filename) {
        return Paths.get(
                URLDecoder.decode(Objects.requireNonNull(
                                FileUtils.class.getClassLoader()
                                        .getResource(filename)
                        ).getFile(), StandardCharsets.UTF_8)
                        .substring(1)
        ).toFile();
    }

}
