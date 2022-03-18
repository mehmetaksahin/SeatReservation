package com.mehmetaksahin.sr;

import com.mehmetaksahin.sr.service.SeatReservationService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SeatReservationServiceTest {

    @BeforeClass
    public static void runOnceBeforeClass() {
        System.out.println("SeatReservationService test is started");
    }

    @AfterClass
    public static void runOnceAfterClass() {
        System.out.println("SeatReservationService test is finished");
    }

    @Test
    public void validate_cinema_seats_row_count() {
        // given
        final int DEFAULT_ROW_COL_NUMBER = 10;


        // when
        SeatReservationService seatReservationService = new SeatReservationService(-3, 6);
        // then
        Assert.assertEquals(DEFAULT_ROW_COL_NUMBER, seatReservationService.getTotalRowCount());


        // when
        seatReservationService = new SeatReservationService(4, -2);
        // then
        Assert.assertEquals(DEFAULT_ROW_COL_NUMBER, seatReservationService.getTotalColCount());


        // when
        seatReservationService = new SeatReservationService(56, 120);
        // then
        Assert.assertEquals(DEFAULT_ROW_COL_NUMBER, seatReservationService.getTotalRowCount());
        Assert.assertEquals(DEFAULT_ROW_COL_NUMBER, seatReservationService.getTotalColCount());
    }

    @Test
    public void test_reserve_one_seat() {
        // given
        final int totalRow = 7;
        final int totalCol = 10;


        // when
        SeatReservationService seatReservationService = new SeatReservationService(totalRow, totalCol);
        seatReservationService.printSeatInfos(false);


        // then
        boolean result = seatReservationService.reserveOneSeat(2, 3); // C-4 Seat -> 2, 3
        Assert.assertTrue(result);
    }

    @Test
    public void test_reserve_one_seat_2() {
        // given
        final int totalRow = 7;
        final int totalCol = 10;


        // when
        SeatReservationService seatReservationService = new SeatReservationService(totalRow, totalCol);
        seatReservationService.printSeatInfos(false);


        // then
        boolean result = seatReservationService.reserveOneSeat(2, 3); // C-4 Seat -> 2, 3
        Assert.assertTrue(result);

        boolean result2 = seatReservationService.reserveOneSeat(1, 3); // B-4 Seat -> 1, 3
        Assert.assertFalse(result2);

        boolean result3 = seatReservationService.reserveOneSeat(1, 0); // B-1 Seat -> 1, 0
        Assert.assertTrue(result3);

    }

    @Test
    public void test_reserve_multi_seat() {
        // given
        final int totalRow = 7;
        final int totalCol = 10;


        // when
        SeatReservationService seatReservationService = new SeatReservationService(totalRow, totalCol);
        seatReservationService.printSeatInfos(false);


        // then
        boolean result = seatReservationService.reserveManySeat(3, 1, 3); // D-2, D-3, D-4
        Assert.assertTrue(result);
    }

    @Test
    public void test_reserve_many_seats() {
        // given
        final int totalRow = 7;
        final int totalCol = 10;


        // when
        SeatReservationService seatReservationService = new SeatReservationService(totalRow, totalCol);
        seatReservationService.printSeatInfos(false);


        // then
        boolean result = seatReservationService.reserveOneSeat(2, 3); // C-4 Seat -> 2, 3
        Assert.assertTrue(result);

        boolean result2 = seatReservationService.reserveOneSeat(1, 3); // B-4 Seat -> 1, 3
        Assert.assertFalse(result2);

        boolean result3 = seatReservationService.reserveOneSeat(1, 0); // B-1 Seat -> 1, 0
        Assert.assertTrue(result3);

        boolean result4 = seatReservationService.reserveManySeat(3, 1, 3); // D-2, D-3, D-4
        Assert.assertFalse(result4);

        boolean result5 = seatReservationService.reserveManySeat(3, 0, 3); // D-1, D-2, D-3
        Assert.assertTrue(result5);

        boolean result6 = seatReservationService.reserveOneSeat(5, 6); // F-7
        Assert.assertTrue(result6);

        boolean result7 = seatReservationService.reserveOneSeat(5, 5); // F-6
        Assert.assertFalse(result7);

        boolean result8 = seatReservationService.reserveManySeat(1, 6, 4); // B-7, B-8, B-9, B-10
        Assert.assertTrue(result8);

        boolean result9 = seatReservationService.reserveOneSeat(2, 5); // C-6
        Assert.assertTrue(result9);

    }

}
