package com.mehmetaksahin.sr.service;

import com.mehmetaksahin.sr.model.Seat;
import com.mehmetaksahin.sr.model.SeatState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeatReservationService {

    private static final Logger LOG = LoggerFactory.getLogger(SeatReservationService.class);
    private final int totalRowCount;
    private final int seatsPerRow;
    private final Seat[][] seats;

    public SeatReservationService(int totalRowCount, int seatsPerRow) {
        this.totalRowCount = totalRowCount;
        this.seatsPerRow = seatsPerRow;
        seats = new Seat[totalRowCount][seatsPerRow];
        initSeatNames();
    }

    /**
     * This is main method for reservation of one seat
     *
     * @param rowOfSeat Seat[A-3] -> A (0)
     * @param colOfSeat Seat[A-3] -> 3 (2)
     * @return reserved/unreserved
     */
    public boolean reserveOneSeat(int rowOfSeat, int colOfSeat) {
        Seat selectedSeat = seats[rowOfSeat][colOfSeat];
        if (SeatState.AVAILABLE.equals(selectedSeat.getSeatState())) {
            selectedSeat.setSeatState(SeatState.SOLD);
            setSeatsNotAvailableStates(rowOfSeat, colOfSeat);
            LOG.info("Ticket reservation completed successfully: {}", selectedSeat);
            printSeatInfos(true);
            return true;
        } else {
            LOG.info("This seat is not available: {}", selectedSeat);
            return false;
        }
    }

    /**
     * This is main method for reservation of multi seats
     *
     * @param rowOfFirstSeat Seat[A-3] to Seat[A-9] -> A (0)
     * @param colOfFirstSeat Seat[A-3] to Seat[A-9] -> 3 (2)
     * @param seatCount      Seat[A-3] to Seat[A-9] -> total 6 seats
     * @return reserved/unreserved
     */
    public boolean reserveManySeat(int rowOfFirstSeat, int colOfFirstSeat, int seatCount) {
        if (checkAvailabilityOfMany(rowOfFirstSeat, colOfFirstSeat, seatCount)) {
            for (int i = 0; i < seatCount; i++) {
                Seat selectedSeat = seats[rowOfFirstSeat][colOfFirstSeat + i];
                selectedSeat.setSeatState(SeatState.SOLD);
                setSeatsNotAvailableStates(rowOfFirstSeat, colOfFirstSeat + i);
                LOG.info("Ticket reservation completed successfully: {}", selectedSeat);
            }
            printSeatInfos(true);
            return true;
        } else {
            LOG.info("There are not enough seats available for you to sit side by side - {} and {} seats", seats[rowOfFirstSeat][colOfFirstSeat], seatCount);
            return false;
        }
    }

    private boolean checkAvailabilityOfMany(int rowOfFirstSeat, int colOfFirstSeat, int seatCount) {
        for (int i = 0; i < seatCount; i++) {
            Seat selectedSeat = seats[rowOfFirstSeat][colOfFirstSeat + i];
            if (!SeatState.AVAILABLE.equals(selectedSeat.getSeatState())) {
                return false;
            }
        }
        return true;
    }

    private void setSeatsNotAvailableStates(int rowOfSeat, int colOfSeat) {
        // left
        if ((colOfSeat > 0) && !SeatState.SOLD.equals(seats[rowOfSeat][colOfSeat - 1].getSeatState())) {
            seats[rowOfSeat][colOfSeat - 1].setSeatState(SeatState.NOT_AVAILABLE);
        }
        // right
        if ((colOfSeat < seatsPerRow - 1) && !SeatState.SOLD.equals(seats[rowOfSeat][colOfSeat + 1].getSeatState())) {
            seats[rowOfSeat][colOfSeat + 1].setSeatState(SeatState.NOT_AVAILABLE);
        }
        // up
        if ((rowOfSeat < totalRowCount - 1) && !SeatState.SOLD.equals(seats[rowOfSeat + 1][colOfSeat].getSeatState())) {
            seats[rowOfSeat + 1][colOfSeat].setSeatState(SeatState.NOT_AVAILABLE);
        }
        // bottom
        if ((rowOfSeat > 0) && !SeatState.SOLD.equals(seats[rowOfSeat - 1][colOfSeat].getSeatState())) {
            seats[rowOfSeat - 1][colOfSeat].setSeatState(SeatState.NOT_AVAILABLE);
        }
    }

    public void initSeatNames() {
        for (int i = 0; i < totalRowCount; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                String seatName = ((char) (i + 1 + 'A' - 1)) + "-" + (j + 1);
                seats[i][j] = new Seat(seatName, SeatState.AVAILABLE);
            }
        }
    }

    public void printSeatInfos(boolean shortInfo) {
        StringBuilder seatInfos = new StringBuilder();
        seatInfos.append("\nMovie Screen\n-------------\n");
        for (int i = 0; i < totalRowCount; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatInfos.append(shortInfo ? seats[i][j].getShortInfo() : seats[i][j].getDetailedInfo());
            }
            seatInfos.append("\n");
        }
        LOG.info("{}", seatInfos);
    }

}
