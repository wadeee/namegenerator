package com.chenhongliang.namegenerator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SplitStringUtils {

    public static List<String> splitString(String str) {
        if (Objects.isNull(str) || str.isEmpty()) return new ArrayList<>();
        return Arrays.asList(str.split("(　|\\s)*(,|，|　|\\s)(　|\\s)*"));
    }

}
