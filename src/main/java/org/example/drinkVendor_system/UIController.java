package org.example.drinkVendor_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UIController {
//    onAction準拠
    void pushDrinkBtn( ActionEvent e ){
//        商品のボタンが押されたとき、何のボタンが押されたのかを取得する
        Button button = (Button) e.getSource(); // 押されたボタンの情報を取得
        String idString = button.getId(); // fx:idを取得する
//        押された商品に応じてserve用の商品IDを取得する
        int prodID = 0 ;
        switch (idString){
//            fxmlファイルを内の商品準拠、Itemに格納する内容にもよる（要変更）
            case "coffee": prodID = 1; break;
            case "drink": prodID = 2; break;
        }
//        商品IDをserveDrinkに送って取り出し口に商品を表示させる
        serveDrink( prodID ) ;
    }
}
