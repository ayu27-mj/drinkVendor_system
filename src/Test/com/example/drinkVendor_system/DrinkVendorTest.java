package com.example.drinkVendor_system;

import org.example.drinkVendor_system.DrinkVendor;
import org.example.drinkVendor_system.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DrinkVendorTest {
    //ゲッターとセッターのテスト
    @Test
    void testArrayGetterSetter() {
        DrinkVendor drinkVendor = new DrinkVendor();
        Item[] expected = {
                new Item(1,"コーラ",160,"@../../../img/coke.png"),
                new Item(2,"オレンジジュース",160,"@../../../img/orange.png")
        };
        drinkVendor.setItems(expected);
        Item[] actual = drinkVendor.getItems();
        assertArrayEquals(expected, actual);
    }

    //防護的コピーのテスト
    @Test
    void testDefensiveCopy() {
        DrinkVendor drinkVendor = new DrinkVendor();
        Item[] original={
                new Item(1,"コーラ",160,"@../../../img/coke.png")
        };
        drinkVendor.setItems(original);
        Item[] retrieved = drinkVendor.getItems();
        retrieved[0]=new Item(5,"モンスター",220,"@../../../img/monster.png");
        assertArrayEquals(original,drinkVendor.getItems());
    }
}