package com.example.aoc_2025.aoc3;

import lombok.extern.slf4j.Slf4j;

/*
The batteries are arranged into banks; each line of digits in your input corresponds to a single bank of batteries. Within each bank, you need to turn on exactly two batteries;
 he joltage that the bank produces is equal to the number formed by the digits on the batteries you've turned on.

 For example, if you have a bank like 12345 and you turn on batteries 2 and 4, the bank would produce 24 jolts. (You cannot rearrange batteries.)
You'll need to find the largest possible joltage each bank can produce. In the above example:

In 987654321111111, you can make the largest joltage possible, 98, by turning on the first two batteries.
In 811111111111119, you can make the largest joltage possible by turning on the batteries labeled 8 and 9, producing 89 jolts.
In 234234234234278, you can make 78 by turning on the last two batteries (marked 7 and 8).
In 818181911112111, the largest joltage you can produce is 92.
The total output joltage is the sum of the maximum joltage from each bank, so in this example, the total output joltage is 98 + 89 + 78 + 92 = 357.
* */
@Slf4j
public class AoC3P2 extends AoC3 {

    public static void main(String[] args) {
        new AoC3P2().execute();//res =173161749617495
    }

    @Override
    Long parseResult(String line) {
        var result = new StringBuilder();
        int startIndex = 0;

        for (int remaining = 12; remaining > 0; remaining--) {
            int endIndex = line.length() - remaining;
            var maxDigit = 0L;
            int maxIndex = startIndex;

            for (int i = startIndex; i <= endIndex; i++) {
                final long longValue = Long.parseLong(String.valueOf(line.charAt(i)));
                log.info("Comparing {} to {}", longValue, maxDigit);
                if (longValue > maxDigit) {
                    maxDigit = longValue;
                    maxIndex = i;
                }
            }

            log.info("Found max digit: {} ", maxDigit);
            result.append(maxDigit);
            startIndex = maxIndex + 1;
        }

        return Long.parseLong(result.toString());
    }

    @Override
    public Long expectedValue() {
        return 3121910778619L;
    }

    @Override
    public int getLength() {
        return 12;
    }

}
