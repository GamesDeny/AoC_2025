package com.example.aoc_2025.aoc1;

import java.util.concurrent.atomic.AtomicLong;

/*
"Due to newer security protocols, please use password method 0x434C49434B until further notice."

You remember from the training seminar that "method 0x434C49434B" means you're actually supposed to
    count the number of times any click causes the dial to point at 0
    regardless of whether it happens during a rotation or at the end of one.

The dial starts by pointing at 50.
The dial is rotated L68 to point at 82; during this rotation, it points at 0 once.
The dial is rotated L30 to point at 52.
The dial is rotated R48 to point at 0.
The dial is rotated L5 to point at 95.
The dial is rotated R60 to point at 55; during this rotation, it points at 0 once.
The dial is rotated L55 to point at 0.
The dial is rotated L1 to point at 99.
The dial is rotated L99 to point at 0.
The dial is rotated R14 to point at 14.
The dial is rotated L82 to point at 32; during this rotation, it points at 0 once.
In this example, the dial points at 0 three times at the end of a rotation,
plus three more times during a rotation.
So, in this example, the new password would be 6.


Using password method 0x434C49434B, what is the password to open the door?
* */
public class AoC1P2 extends AoC1 {

    public static void main(String[] args) {
        new AoC1P2().execute();//expected =
    }

    @Override
    long calculateCode(DirectionLength direction, AtomicLong code, AtomicLong result) {
        var res = switch (direction.direction()) {
            case RIGHT -> code.get() + direction.value();
            case LEFT -> code.get() - direction.value();
        };
        var totalCount = Math.abs(res) / 100;
        if (code.get() != 0 && (res == 0 || res < 0)) {
            totalCount += 1;
        }
        result.addAndGet(totalCount);

        final long finalRes = res < 0
                ? ((100 - (Math.abs(res) % 100)) % 100)
                : (Math.abs(res) % 100);
        if (totalCount > 0 && direction.value() > 100) {
            logger.info("Start-final dir-val: %d-%d %s%d   total: %d".formatted(
                    code.get(), finalRes,
                    direction.direction().name().charAt(0), direction.value(), totalCount
            ));
        }
        return finalRes;
    }

    @Override
    public Long expectedValue() {
        return 6L;
    }

}
