package com.example.aoc_2025.aoc2;

import com.example.aoc_2025.AbstractAoC;
import com.google.common.base.Splitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.LongStream;

/*
11-22,95-115,998-1012,1188511880-1188511890,222220-222224,
1698522-1698528,446443-446449,38593856-38593862,565653-565659,
824824821-824824827,2121212118-2121212124


Since the young Elf was just doing silly patterns, you can find the invalid IDs by looking for any
ID which is made only of some sequence of digits repeated twice. So, 55 (5 twice), 6464 (64 twice), and 123123 (123 twice) would all be invalid IDs.

11-22 has two invalid IDs, 11 and 22.
95-115 has one invalid ID, 99.
998-1012 has one invalid ID, 1010.
1188511880-1188511890 has one invalid ID, 1188511885.
222220-222224 has one invalid ID, 222222.
1698522-1698528 contains no invalid IDs.
446443-446449 has one invalid ID, 446446.
38593856-38593862 has one invalid ID, 38593859.
The rest of the ranges contain no invalid IDs.
Adding up all the invalid IDs in this example produces 1227775554.

What do you get if you add up all of the invalid IDs?
* */
public class AoC2 extends AbstractAoC<Long> {

    public static void main(String[] args) {
        new AoC2().execute();//res = 18700015741
    }

    @Override
    public Long exec(File file) {
        var inputs = new ArrayList<Range>();

        try (var fis = new FileInputStream(file); var sc = new Scanner(fis)) {
            inputs.addAll(Arrays.stream(sc.nextLine().split(","))
                    .map(Range::new)
                    .toList()
            );
        } catch (FileNotFoundException _) {
            logger.severe("File not found");
        } catch (IOException _) {
            logger.severe("Error in file read");
        }

        return inputs.stream()
                .map(this::getInvalidIds)
                .flatMap(List::stream)
                .reduce(Long::sum)
                .orElse(0L);
    }

    private List<Long> getInvalidIds(Range range) {
        return LongStream.rangeClosed(range.start(), range.end())
                .filter(this::isValInvalid)
                .boxed()
                .toList();
    }

    private boolean isValInvalid(long l) {
        if (l > 10) {
            var s = String.valueOf(l);
            if (s.length() % 2 == 0) {
                var splitList = Splitter.fixedLength(s.length() / 2)
                        .splitToList(s);
                var set = new HashSet<>(splitList);
                return set.size() == 1;
            }
        }
        return false;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Long expectedValue() {
        return 1227775554L;
    }

}
