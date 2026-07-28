package com.example.drinkVendor_system;

import org.example.drinkVendor_system.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void constructorTest() {
        Item item = new Item(1, "コーラ", 150, "cola.png");

        assertEquals(1, item.getId());
        assertEquals("コーラ", item.getName());
        assertEquals(150, item.getPrice());
        assertEquals("cola.png", item.getImgURL());
    }

    @Test
    void setIdTest() {
        Item item = new Item(1, "コーラ", 150, "cola.png");

        item.setId(2);

        assertEquals(2, item.getId());
    }

    @Test
    void setNameTest() {
        Item item = new Item(1, "コーラ", 150, "cola.png");

        item.setName("お茶");

        assertEquals("お茶", item.getName());
    }

    @Test
    void setPriceTest() {
        Item item = new Item(1, "コーラ", 150, "cola.png");

        item.setPrice(200);

        assertEquals(200, item.getPrice());
    }

    @Test
    void setImgURLTest() {
        Item item = new Item(1, "コーラ", 150, "cola.png");

        item.setImgURL("tea.png");

        assertEquals("tea.png", item.getImgURL());
    }

}