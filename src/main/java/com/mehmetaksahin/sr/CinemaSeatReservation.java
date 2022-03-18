package com.mehmetaksahin.sr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class CinemaSeatReservation {

    private static final Logger LOG = LoggerFactory.getLogger(CinemaSeatReservation.class);
    private static final int MAX_ROW_COL_NUMBER = 15;

    public static void main(String[] args) {

        LOG.info("---------------------");
        LOG.info("AKSAHIN CINEMA");
        LOG.info("---------------------");
        LOG.info("");

        int rowCountOfTheater = getCountOfLine("Please enter number of rows in the movie theater");
        int seatsPerRowOfTheater = getCountOfLine("Please enter the number of seats in each row in the movie theater");


    }

    private static int getCountOfLine(String message) {
        Scanner scanner = new Scanner(System.in);
        LOG.info(message);
        int count = checkAndValidateCountOfLine(scanner.nextLine());
        if (count == 0) {
            count = getCountOfLine(message);
        }
        return count;
    }

    private static int checkAndValidateCountOfLine(String countOfRowOrColStr) {
        try {
            // control of valid number
            int countOfRowOrCol = Integer.parseInt(countOfRowOrColStr);

            // control of edge cases
            if (countOfRowOrCol <= 0) {
                LOG.info("The number of lines in the movie theater cannot be less than zero");
                return 0;
            }
            if (countOfRowOrCol > MAX_ROW_COL_NUMBER) {
                LOG.info("No more than " + MAX_ROW_COL_NUMBER + " seats can be placed in each row in the movie theater.");
                return 0;
            }

            return countOfRowOrCol;
        } catch (NumberFormatException e) {
            LOG.info("Please enter a valid number");
            return 0;
        }
    }
}
