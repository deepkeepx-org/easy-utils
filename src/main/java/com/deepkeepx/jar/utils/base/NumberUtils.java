package com.deepkeepx.jar.utils.base;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Utility class for number-related operations.
 *
 * @author deepkeepx
 * @version 1.0.1
 */
public class NumberUtils {

    /**
     * Generates a random integer within a specified range.
     *
     * @param min the lower bound (inclusive)
     * @param max the upper bound (inclusive)
     * @return a random integer between min and max
     */
    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Performs high-precision division and rounds the result.
     *
     * @param num     the dividend
     * @param divisor the divisor
     * @param scale   the number of decimal places to retain
     * @return the rounded result as BigDecimal
     * @throws IllegalArgumentException if divisor is zero
     */
    private static BigDecimal divideAndRound(double num, int divisor, int scale) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }
        BigDecimal bd = new BigDecimal(num);
        return bd.divide(new BigDecimal(divisor), scale, RoundingMode.HALF_UP);
    }

}