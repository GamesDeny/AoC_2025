package com.example.aoc_2025.aoc5;

import com.example.aoc_2025.aoc2.StringToRange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public class AoC5P2 extends AoC5 {

    public static void main(String[] args) {
        new AoC5P2().execute(); // result= 357608232770687
    }

    @Override
    public Long exec(File file) {
        var result = new AtomicLong();

        try (var fis = new FileInputStream(file); var sc = new Scanner(fis)) {
            var ranges = new ArrayList<Map.Entry<Long, Long>>();
            var tree = new TreeMap<Long, Long>();
            var line = "";
            while (sc.hasNextLine() && !"".equals(line = sc.nextLine())) {
                var range = new StringToRange(line);
                ranges.add(Map.entry(range.start(), range.end()));
            }

            for (var range : ranges) {
                Long leftAdd = range.getKey();
                Long rightAdd = range.getValue();

                if (!tree.isEmpty() &&
                        tree.firstKey().compareTo(rightAdd) <= 0 &&
                        tree.lastEntry().getValue().compareTo(leftAdd) >= 0) {
                    Long overlapStartKey = getLeftKey(tree, leftAdd);
                    Long overlapEndKey = getRightKey(tree, rightAdd);
                    if (overlapStartKey.compareTo(overlapEndKey) <= 0) {
                        leftAdd = Long.min(leftAdd, overlapStartKey);
                        rightAdd = Long.max(rightAdd, tree.get(overlapEndKey));
                        tree.subMap(overlapStartKey, true, overlapEndKey, true).clear();
                    }
                }
                tree.put(leftAdd, rightAdd);
            }

            return tree.entrySet()
                    .stream()
                    .map(e -> e.getValue() - e.getKey() + 1L)
                    .reduce(Long::sum)
                    .orElse(0L);

        } catch (FileNotFoundException _) {
            log.warn("File not found");
        } catch (IOException _) {
            log.warn("Error in file read");
        }

        return result.get();
    }

    @Override
    public Long expectedValue() {
        return 14L;
    }

    public static boolean inRange(TreeMap<Long, Long> tree, Long ing) {
        Long lowerKey = tree.floorKey(ing);
        return lowerKey != null && tree.get(lowerKey).compareTo(ing) >= 0;
    }

    public static Long getLeftKey(TreeMap<Long, Long> tree, Long left) {
        Long leftDelete = tree.floorKey(left);
        if (leftDelete != null) {
            if (tree.get(leftDelete).compareTo(left) < 0) {
                leftDelete = tree.higherKey(leftDelete);
            }
        } else {
            leftDelete = tree.firstKey();
        }
        return leftDelete;
    }

    public static Long getRightKey(TreeMap<Long, Long> tree, Long right) {
        Long rightDelete = tree.ceilingKey(right);
        if (rightDelete != null) {
            if (rightDelete.compareTo(right) != 0) {
                rightDelete = tree.lowerKey(rightDelete);
            }
        } else {
            rightDelete = tree.lastKey();
        }
        return rightDelete;
    }

}
