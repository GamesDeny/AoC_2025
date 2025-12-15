package com.example.aoc_2025.aoc5;

import com.example.aoc_2025.AbstractAoC;
import com.example.aoc_2025.aoc2.StringToRange;
import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
The Elves in the kitchen explain the situation: because of their complicated new inventory management system,
they can't figure out which of their ingredients are fresh and which are spoiled.
 When you ask how it works, they give you a copy of their database (your puzzle input).

The database operates on ingredient IDs. It consists of a list of fresh ingredient ID ranges,
 a blank line, and a list of available ingredient IDs.

The fresh ID ranges are inclusive: the range 3-5 means that ingredient IDs 3, 4, and 5 are all fresh. The ranges can also overlap; an ingredient ID is fresh if it is in any range.

The Elves are trying to determine which of the available ingredient IDs are fresh. In this example, this is done as follows:

Ingredient ID 1 is spoiled because it does not fall into any range.
Ingredient ID 5 is fresh because it falls into range 3-5.
Ingredient ID 8 is spoiled.
Ingredient ID 11 is fresh because it falls into range 10-14.
Ingredient ID 17 is fresh because it falls into range 16-20 as well as range 12-18.
Ingredient ID 32 is spoiled.
So, in this example, 3 of the available ingredient IDs are fresh.
* */
public class AoC5 extends AbstractAoC<Long> {

    public static void main(String[] args) {
        new AoC5().execute(); // result=720
    }

    @Override
    public Long exec(File file) {
        long result = 0;

        TreeRangeSet<Long> rangeSet = TreeRangeSet.create();
        var ids = new ArrayList<Long>();
        try (var fis = new FileInputStream(file); var sc = new Scanner(fis)) {
            var line = "";
            while (sc.hasNextLine() && !"".equals(line = sc.nextLine())) {
                var stringToRange = new StringToRange(line);
                rangeSet.add(Range.closed(stringToRange.start(), stringToRange.end()));
            }
            while (sc.hasNextLine()) {
                ids.add(Long.parseLong(sc.nextLine()));
            }

            result = ids.stream()
                    .map(rangeSet::contains)
                    .filter(Boolean::booleanValue)
                    .count();

        } catch (FileNotFoundException _) {
            log.warn("File not found");
        } catch (IOException _) {
            log.warn("Error in file read");
        }

        return result;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Long expectedValue() {
        return 3L;
    }

}
