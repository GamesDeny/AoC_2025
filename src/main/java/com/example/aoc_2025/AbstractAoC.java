package com.example.aoc_2025;

import lombok.AccessLevel;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Objects;

@Slf4j(access = AccessLevel.PUBLIC)
public abstract class AbstractAoC<T> {

    public void execute() {
        var exRes = exec(FileUtils.getExample(getCount()));
        log.info("Ex res: {}", exRes);

        if (!Objects.equals(exRes, expectedValue())) {
            throw new IllegalArgumentException("FAILED TEST, expected " + expectedValue() + ", got " + exRes);
        }
        log.info("Validation result: {}", exec(FileUtils.get(getCount())));
    }

    public abstract T exec(File file);

    public abstract int getCount();

    public abstract T expectedValue();

}
