package com.example.aoc_2025.aoc2;

import com.google.common.base.Splitter;

import java.util.HashSet;

public class AoC2P2 extends AoC2 {

    public static void main(String[] args) {
        new AoC2P2().execute();// res = 20077272987
    }

    @Override
    boolean isValInvalid(long l) {
        if (l > 10) {
            var s = String.valueOf(l);
            for (var i = 1; i <= s.length() / 2; i++) {
                var splitted = Splitter.fixedLength(i)
                        .splitToList(s);

                var set = new HashSet<>(splitted);
                if (set.size() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Long expectedValue() {
        return 4174379265L;
    }

}
