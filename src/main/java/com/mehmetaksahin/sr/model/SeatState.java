package com.mehmetaksahin.sr.model;

public enum SeatState {

    AVAILABLE("Available", "A"),
    NOT_AVAILABLE("Not Available ", "x"),
    SOLD("Sold", "S");

    private String stateName;
    private String stateShortName;

    SeatState(String stateName, String stateShortName) {
        this.stateName = stateName;
        this.stateShortName = stateShortName;
    }

    public String getStateName() {
        return stateName;
    }

    public String getStateShortName() {
        return stateShortName;
    }
}
