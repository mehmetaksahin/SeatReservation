package com.mehmetaksahin.sr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    private String seatName;
    private SeatState seatState = SeatState.AVAILABLE;

    @Override
    public String toString() {
        return "[Seat " + seatName + ", " + seatState.getStateName() + "]";
    }

    public String getDetailedInfo() {
        return "|" + seatName + "| " + seatState.getStateShortName() + " ";
    }

    public String getShortInfo() {
        return "| " + seatState.getStateShortName() + " ";
    }

}
