package com.main.seneschal.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DayScheduleTest {

    private DaySchedule ds;

    @Before
    public void setUp() throws Exception {
        ds = new DaySchedule(7,18,true);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void correctDayScheduleSetUp(){
        Assert.assertEquals(ds.getOpeningTime(),7);
        Assert.assertEquals(ds.getClosingTime(),18);
        Assert.assertEquals(ds.isOpenToday(),true);
    }
}