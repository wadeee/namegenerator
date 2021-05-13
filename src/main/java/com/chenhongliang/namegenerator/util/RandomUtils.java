package com.chenhongliang.namegenerator.util;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomUtils {
    static Random rand = new Random(new Date().getTime());

    public static <T> T randomSelect(List<T> constrainedCharacters) {
        return constrainedCharacters.get(rand.nextInt(constrainedCharacters.size()));
    }

}
