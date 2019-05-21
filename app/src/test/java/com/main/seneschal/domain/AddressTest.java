package com.main.seneschal.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

    private Address address1;
    private Address address2;

    @Before
    public void setUp() throws Exception {
        address1 = new Address();
        address2 = new Address("Palaiologou", "10","Athens");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void correctAddressSetup(){
        address1.setStreet("Kodrigtonos");
        address1.setNumber("12");
        address1.setArea("Athens");

        Assert.assertEquals(address1.getStreet(),"Kodrigtonos");
        Assert.assertEquals(address2.getStreet(),"Palaiologou");
        Assert.assertEquals(address1.getNumber(),"12");
        Assert.assertEquals(address2.getNumber(),"10");
        Assert.assertEquals(address1.getArea(), "Athens");
        Assert.assertEquals(address2.getArea(),"Athens");
    }
}