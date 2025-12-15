package com.example.aoc_2025.aoc4;

import com.example.aoc_2025.AbstractAoC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/* Search in the square around of the selected @ */
public class AoC4 extends AbstractAoC<String> {
    static final String AT_SYMBOL = "@";
    static final String TEMP_SYMBOL = "x";
    static final String[] EMPTY_STRING_ARRAY = {};

    public static void main(String[] args) {
        new AoC4().execute();//res =1602
    }

    @Override
    public String exec(File file) {
        var result = 0L;
        try (var fis = new FileInputStream(file); var sc = new Scanner(fis)) {
            var tempLines = new String[][]{{}, {}, {}};

            do {
                tempLines[0] = tempLines[1];
                tempLines[1] = tempLines[2];
                tempLines[2] = sc.nextLine().split("");

                if (Arrays.stream(tempLines).filter(line -> line.length == 0).count() < 2) {
                    result += calculateForLines(tempLines);
                }
            } while (sc.hasNextLine());

            tempLines[0] = tempLines[1];
            tempLines[1] = tempLines[2];
            tempLines[2] = EMPTY_STRING_ARRAY;
            result += calculateForLines(tempLines);
        } catch (FileNotFoundException _) {
            log.warn("File not found");
        } catch (IOException _) {
            log.warn("Error in file read");
        }

        return String.valueOf(result);
    }

    long calculateForLines(String[][] lineRectangle) {
        var count = new AtomicInteger();

        var upperLine = lineRectangle[0];
        var mainLine = lineRectangle[1];
        var lowerLine = lineRectangle[2];

        for (int i = 0; i < mainLine.length; i++) {
            if (!AT_SYMBOL.equals(mainLine[i])) {
                continue;
            }

            var squareStream = Stream.builder();
            if (i == 0) {
                squareStream.add(mainLine[i + 1]);
                if (upperLine != null && upperLine.length > 0) {
                    squareStream.add(upperLine[i])
                            .add(upperLine[i + 1]);
                }
                if (lowerLine != null && lowerLine.length > 0) {
                    squareStream.add(lowerLine[i])
                            .add(lowerLine[i + 1]);
                }
            } else if (i == mainLine.length - 1) {
                squareStream.add(mainLine[i - 1]);
                if (upperLine != null && upperLine.length > 0) {
                    squareStream.add(upperLine[i])
                            .add(upperLine[i - 1]);
                }
                if (lowerLine != null && lowerLine.length > 0) {
                    squareStream.add(lowerLine[i])
                            .add(lowerLine[i - 1]);
                }
            } else {
                squareStream.add(mainLine[i + 1])
                        .add(mainLine[i - 1]);
                if (upperLine != null && upperLine.length > 0) {
                    squareStream.add(upperLine[i])
                            .add(upperLine[i + 1])
                            .add(upperLine[i - 1]);
                }
                if (lowerLine != null && lowerLine.length > 0) {
                    squareStream.add(lowerLine[i])
                            .add(lowerLine[i + 1])
                            .add(lowerLine[i - 1]);
                }
            }

            if (squareStream.build()
                    .filter(cell -> TEMP_SYMBOL.equals(cell) || AT_SYMBOL.equals(cell))
                    .count() < 4) {
                count.incrementAndGet();
                mainLine[i] = TEMP_SYMBOL;
            }
        }
        return count.get();
    }


    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public String expectedValue() {
        return "13";
    }

}
