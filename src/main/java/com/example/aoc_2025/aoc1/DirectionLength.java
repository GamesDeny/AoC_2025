package com.example.aoc_2025.aoc1;

public record DirectionLength(Direction direction, long value) {

    public DirectionLength(String row) {
        this(switch (row.charAt(0)) {
                    case 'R' -> Direction.RIGHT;
                    case 'L' -> Direction.LEFT;
                    default -> throw new IllegalArgumentException("Invalid direction");
                }, Long.parseLong(row.substring(1))
        );
    }
}
