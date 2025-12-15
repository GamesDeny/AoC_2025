package com.example.aoc_2025.aoc3;

import com.example.aoc_2025.AbstractAoC;
import com.google.common.base.Splitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
public class AoC3 extends AbstractAoC<Long> {
    private static final String BANK_IS_EMPTY_EXCEPTION = "Bank is empty";

    public static void main(String[] args) {
        new AoC3().execute();//res =17427
    }

    @Override
    public Long exec(File file) {
        var result = 0L;
        try (var fis = new FileInputStream(file); var sc = new Scanner(fis)) {
            while (sc.hasNextLine()) {
                result += parseResult(sc.nextLine());
            }
        } catch (FileNotFoundException _) {
            log.warn("File not found");
        } catch (IOException _) {
            log.warn("Error in file read");
        }

        return result;
    }

    Long parseResult(String line) {
        var bank = Splitter.fixedLength(1)
                .splitToList(line).stream()
                .map(Long::parseLong)
                .toList();
        var maxL = bank.stream()
                .max(Long::compareTo)
                .orElseThrow(() -> new IllegalArgumentException(BANK_IS_EMPTY_EXCEPTION));

        var lIndex = 0;
        for (var i = 0; i < bank.size(); i++) {
            if (bank.get(i).equals(maxL)) {
                lIndex = i;
                break;
            }
        }

        if (lIndex == bank.size() - 1) {
            var otherMax = bank.stream()
                    .limit(lIndex)
                    .max(Long::compareTo)
                    .orElseThrow(() -> new IllegalArgumentException(BANK_IS_EMPTY_EXCEPTION));
            return otherMax * 10L + maxL;
        }
        var otherMax = bank.stream()
                .skip(lIndex + 1L)
                .max(Long::compareTo)
                .orElseThrow(() -> new IllegalArgumentException(BANK_IS_EMPTY_EXCEPTION));
        return maxL * 10L + otherMax;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Long expectedValue() {
        return 357L;
    }

    public int getLength() {
        return 2;
    }

}
