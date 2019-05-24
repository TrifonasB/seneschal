package com.main.seneschal.domain;


public class Store {
    private int id;
    private String name;
    private DaySchedule[] schedule;
    private Address address;

    public Store() {
    }

    public Store(String name, DaySchedule[] schedule, Address address) {
        this.name = name;
        this.schedule = schedule;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
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
