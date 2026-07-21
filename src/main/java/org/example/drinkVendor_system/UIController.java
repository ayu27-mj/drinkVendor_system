package org.example.drinkVendor_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UIController {
    void pushDrinkBtn( ActionEvent e ){
//        商品のボタンが押されたとき、何のボタンが押されたのかを取得する
        Button button = (Button) e.getSource(); // 押されたボタンの情報を取得
        String idString = button.getId(); // fx:idを取得する
//        押された商品に応じてserve用の商品IDを取得する
        int prodID = 0 ;
        switch (idString){
            case "ColaButton": prodID = 1; break;
            case "OrangeButton": prodID = 2; break;
            case "AppleButton" : prodID = 3; break;
            case "CoffeeButton" : prodID = 4; break;
            case "CocoaButton" : prodID = 5; break;
            case "WaterButton" : prodID = 6; break;
            case "SparklingWaterButton" : prodID = 7; break;
            case "GreenButton" : prodID = 8; break;
            case "TeaButton" : prodID = 9; break;
        }
//        商品IDをserveDrinkに送って取り出し口に商品を表示させる
        serveDrink( prodID ) ;
    }
}
