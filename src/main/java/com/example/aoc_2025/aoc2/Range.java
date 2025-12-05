package com.example.aoc_2025.aoc2;

public record Range(Long start, Long end) {

    public Range(String s) {
        var split = s.split("-");
        this(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }

}
