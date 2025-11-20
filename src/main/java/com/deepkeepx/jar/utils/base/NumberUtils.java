package com.deepkeepx.jar.utils.base;

import java.util.Random;

/**
 * &#064;Description:  TODO
 * &#064;Author:  LiuGangGang
 * &#064;PackageName  deepkeepx-utils
 * &#064;Package  com.deepkeepx.jar.utils.base
 * &#064;Date:  Created in 01:38 2025/9/27
 * &#064;Version  1.0.0
 */
public class NumberUtils {

    /**
     * Randomly generate numbers within a specified range.
     *
     * @param min number
     * @param max number
     * @return random number
     */
    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}