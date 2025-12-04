package com.example.aoc_2025.aoc1;

import com.example.aoc_2025.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class AoC1 {
    private static final Logger logger = Logger.getLogger(AoC1.class.getName());

    /*
The safe has a dial with only an arrow on it; around the dial are the numbers 0 through 99 in order. As you turn the dial, it makes a small click noise as it reaches each number.

So, if the dial were pointing at 11, a rotation of R8 would cause the dial to point at 19. After that, a rotation of L19 would cause it to point at 0.
Because the dial is a circle, turning the dial left from 0 one click makes it point at 99. Similarly, turning the dial right from 99 one click makes it point at 0.
So, if the dial were pointing at 5, a rotation of L10 would cause it to point at 95. After that, a rotation of R5 could cause it to point at 0.
The actual password is the number of times the dial is left pointing at 0 after any rotation in the sequence.

For example, suppose the attached document contained the following rotations:
The dial starts by pointing at 50.
The dial is rotated L68 to point at 82.
The dial is rotated L30 to point at 52.
The dial is rotated R48 to point at 0.
The dial is rotated L5 to point at 95.
The dial is rotated R60 to point at 55.
The dial is rotated L55 to point at 0.
The dial is rotated L1 to point at 99.
The dial is rotated L99 to point at 0.
The dial is rotated R14 to point at 14.
The dial is rotated L82 to point at 32.
    * */

    public static void main(String[] args) {
        var exRes = exec(FileUtils.getExample(1));
        System.out.println(exRes);
        if (exRes != 3) {
            throw new RuntimeException("FAILED TEST");
        }

        System.out.println(exec(FileUtils.get(1)));
    }

    public static int exec(File file) {
        var result = 0;
        var start = 50L;

        try (var fis = new FileInputStream(file); var sc = new Scanner(fis)) {
            while (sc.hasNextLine()) {
                var direction = new DirectionLength(sc.nextLine());

                start += switch (direction.direction()) {
                    case RIGHT -> direction.value();
                    case LEFT -> -direction.value();
                };

                if (start < 0) {
                    start = 100- (Math.abs(start) % 100);
                    result++;
                } else if (start > 100) {
                    start %= 100;
                }
            }
        } catch (FileNotFoundException _) {
            logger.severe("File not found");
        } catch (IOException _) {
            logger.severe("Error in file read");
        }

        return result;
    }

}
