package com.upwork.mehmetaksahin.csr.service;

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
        init();
    }

    /**
     * initial assignment of seat status
     */
    private void init() {
        for (int i = 0; i < totalRowCount; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                String seatName = ((char) (i + 1 + 'A' - 1)) + "-" + (j + 1);
                seats[i][j] = new Seat(seatName, SeatState.AVAILABLE);
                //LOG.info(seats[i][j].toString());
            }
        }
    }

    public void printSeatInfos() {
        StringBuilder seatInfos = new StringBuilder();
        seatInfos.append("\nMovie Screen\n-------------\n");
        for (int i = 0; i < totalRowCount; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatInfos.append(seats[i][j].getShortInfo() + " ");
            }
            seatInfos.append("\n");
        }
        LOG.info("{}", seatInfos);
    }

    public boolean reserveOneSeat(int row, int col) {
        Seat seat = seats[row][col];
        if (SeatState.AVAILABLE.equals(seat.getSeatState())) {

            seat.setSeatState(SeatState.SOLD);

            // left
            if (col > -1) {
                seats[row][col - 1].setSeatState(SeatState.NOT_AVAILABLE);
            }
            // right
            if (col < seatsPerRow - 1) {
                seats[row][col + 1].setSeatState(SeatState.NOT_AVAILABLE);
            }
            // up
            if (row < totalRowCount - 1) {
                seats[row + 1][col].setSeatState(SeatState.NOT_AVAILABLE);
            }
            // bottom
            if (row > -1) {
                seats[row - 1][col].setSeatState(SeatState.NOT_AVAILABLE);
            }
            printSeatInfos();
            return true;
        } else {
            LOG.info("This seat is not available: {}", seat);
            return false;
        }
    }

    // birden fazla koltuk için önce A kontrolü yap
}
