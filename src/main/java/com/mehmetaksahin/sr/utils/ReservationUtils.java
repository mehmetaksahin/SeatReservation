package com.mehmetaksahin.sr.utils;

public class ReservationUtils {

    private static final int MAX_ROW_COL_NUMBER = 15;

    public static boolean validateCountOfRowOrCol(int countOfRowOrCol) {
        try {
            // control of edge cases - min
            if (countOfRowOrCol <= 0) {
                return false;
            }
            // control of edge cases - max
            if (countOfRowOrCol > MAX_ROW_COL_NUMBER) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
