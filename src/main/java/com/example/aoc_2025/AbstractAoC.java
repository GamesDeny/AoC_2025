package com.example.aoc_2025;

import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;

public abstract class AbstractAoC<T> {
    public static final Logger logger = Logger.getLogger(AbstractAoC.class.getName());

    public void execute() {
        var exRes = exec(FileUtils.getExample(getCount()));
        logger.info("Ex res: " + exRes);

        if (!Objects.equals(exRes, expectedValue())) {
            throw new IllegalArgumentException("FAILED TEST");
        }
        logger.info("Validation result: " + exec(FileUtils.get(getCount())));
    }

    public abstract T exec(File file);

    public abstract int getCount();

    public abstract T expectedValue();

}
