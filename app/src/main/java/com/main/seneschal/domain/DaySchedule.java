package com.main.seneschal.domain;

public class DaySchedule {

    private int openingTime;
    private int closingTime;
    private boolean openToday;

    public DaySchedule(int openingTime, int closingTime, boolean openToday) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.openToday = openToday;
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isOpenToday() {
        return openToday;
    }

    public void setOpenToday(boolean openToday) {
        this.openToday = openToday;
    }
}
