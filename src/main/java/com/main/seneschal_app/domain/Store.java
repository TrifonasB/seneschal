package com.main.seneschal_app.domain;


public class Store {

    protected static int currentStoreId = 0;
    private int id;
    private String name;
    private DaySchedule[] schedule;
    private Address address;

    public Store() {
    }

    public Store(String name, DaySchedule[] schedule, Address address) {
        id = currentStoreId++;
        this.name = name;
        schedule = new DaySchedule[7];
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id = currentStoreId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DaySchedule[] getSchedule() {
        return schedule;
    }

    public void setSchedule(DaySchedule[] schedule) {
        if (schedule.length!=7) throw new IllegalArgumentException("Μη συμβατός πίνακας");
        this.schedule = schedule;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
