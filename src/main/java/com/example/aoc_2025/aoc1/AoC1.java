package com.example.aoc_2025.aoc1;

import com.example.aoc_2025.AbstractAoC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

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
public class AoC1 extends AbstractAoC<Long> {

    public static void main(String[] args) {
        new AoC1().execute();//expected = 1043
    }

    @Override
    public Long exec(File file) {
        var result = new AtomicLong();
        var code = new AtomicLong(50L);

        try (var fis = new FileInputStream(file); var sc = new Scanner(fis)) {
            while (sc.hasNextLine()) {
                var direction = new DirectionLength(sc.nextLine());

                code.set(calculateCode(direction, code, result));
            }
        } catch (FileNotFoundException _) {
            log.warn("File not found");
        } catch (IOException _) {
            log.warn("Error in file read");
        }

        return result.get();
    }

    long calculateCode(DirectionLength direction, AtomicLong code, AtomicLong result) {
        var res = switch (direction.direction()) {
            case RIGHT -> code.get() + direction.value();
            case LEFT -> code.get() - direction.value();
        } % 100;
        if (res == 0) {
            result.incrementAndGet();
        }
        return res;
    }

    @Override
    public Long expectedValue() {
        return 3L;
    }

    @Override
    public int getCount() {
        return 1;
    }

}
