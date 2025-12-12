package com.example.aoc_2025.aoc4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AoC4P2 extends AoC4 {

    public static void main(String[] args) {
        new AoC4P2().execute();// res = 9518
    }

    @Override
    public String exec(File file) {
        var result = 0L;

        var maxLength = 0;
        var fileRes = new String[1000][];

        try (var fis = new FileInputStream(file); var sc = new Scanner(fis)) {
            while (sc.hasNextLine()) {
                fileRes[maxLength++] = sc.nextLine().split("");
            }
        } catch (FileNotFoundException _) {
            logger.severe("File not found");
        } catch (IOException _) {
            logger.severe("Error in file read");
        }

        int index;
        long increment;
        do {
            index = 0;
            increment = 0;
            var tempLines = new String[][]{{}, {}, {}};
            for (var i = 0; i < maxLength; i++) {
                var line = fileRes[i];
                tempLines[0] = tempLines[1];
                tempLines[1] = tempLines[2];
                tempLines[2] = line != null ? line : EMPTY_STRING_ARRAY;

                if (Arrays.stream(tempLines)
                        .filter(Objects::nonNull)
                        .filter(tempLine -> tempLine.length == 0)
                        .count() < 2) {
                    increment += calculateForLines(tempLines);
                }

                if (tempLines[0] != null && tempLines[0].length > 0) {
                    fileRes[index++] = Arrays.stream(tempLines[0])
                            .map(s -> s.equals(TEMP_SYMBOL) ? "." : s)
                            .toArray(String[]::new);
                }
            }

            tempLines[0] = tempLines[1];
            tempLines[1] = tempLines[2];
            tempLines[2] = EMPTY_STRING_ARRAY;
            increment += calculateForLines(tempLines);
            result += increment;

            fileRes[index++] = Arrays.stream(tempLines[0])
                    .map(s -> s.equals(TEMP_SYMBOL) ? "." : s)
                    .toArray(String[]::new);
            fileRes[index] = Arrays.stream(tempLines[1])
                    .map(s -> s.equals(TEMP_SYMBOL) ? "." : s)
                    .toArray(String[]::new);

            logger.info("Final bigArray with increment %d \n\n%s\n\n"
                    .formatted(
                            increment,
                            Arrays.stream(fileRes)
                                    .limit(maxLength)
                                    .map(Arrays::toString)
                                    .collect(Collectors.joining("\n")))
            );
        } while (increment != 0);

        return String.valueOf(result);
    }

    @Override
    public String expectedValue() {
        return "43";
    }

}
