package com.example.aoc_2025.aoc2;

public record StringToRange(Long start, Long end) {

    public StringToRange(String s) {
        var split = s.split("-");
        this(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }

}
