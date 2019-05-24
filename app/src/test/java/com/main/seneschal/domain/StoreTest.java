package com.main.seneschal.domain;

import org.junit.*;

public class StoreTest {

    private Store store;
    private int id;
    private String name;
    private DaySchedule[] schedule;
    private Address address;

    @Before
    public void setUp (){

        schedule = new DaySchedule[7];
        address = new Address();
        store = new Store("Kivotos", schedule, address);

        address = new Address("Papaioannou", "23", "karnavalia");
        store.setAddress(address);
        store.setName("ola3euro");
        store.setSchedule(schedule);
    }

    @After
    public void tearDown (){
    }

    @Test
    public void getThemAll (){
    Assert.assertEquals(store.getId(), 0);
    Assert.assertEquals(store.getAddress(), address);
    Assert.assertEquals(store.getName(), "ola3euro");
    Assert.assertEquals(store.getSchedule(), schedule);

}}
