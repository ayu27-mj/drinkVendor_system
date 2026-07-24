package org.example.drinkVendor_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UIController {

    // === あなたの担当：FXML・画面要素の宣言 ===
    @FXML private Label nowMoneyLabel;

//    商品ボタン
    @FXML private Button ColaButton;
    @FXML private Button OrangeButton;
    @FXML private Button AppleButton;
    @FXML private Button CoffeeButton;
    @FXML private Button CocoaButton;
    @FXML private Button WaterButton;
    @FXML private Button SparklingWaterButton;
    @FXML private Button GreenButton;
    @FXML private Button TeaButton;
//    表示ラベル
    @FXML private Label OutputLabel ;
    @FXML private Label AmountLabel ;
    @FXML private Label ChangeLabel ;

    // ボタン格納用配列
    private Button[] drinkButtons;

    private DrinkVendor drinkVendor = new DrinkVendor();

    /**
     * 初期化処理（画面ロード時に自動実行）
     */
    @FXML
    public void initialize() {
        drinkButtons = new Button[]{
                ColaButton, OrangeButton, AppleButton,
                CoffeeButton, CocoaButton, WaterButton,
                SparklingWaterButton, GreenButton, TeaButton
        };
    }

    @FXML
    void pushDrinkBtn(ActionEvent e ){
//        商品のボタンが押されたとき、何のボタンが押されたのかを取得する
        Button button = (Button) e.getSource(); // 押されたボタンの情報を取得
        String idString = button.getId(); // fx:idを取得する
//        押された商品に応じてserve用の商品IDを取得する
        int prodID = 0;
        switch (idString){
//            DrinkVendor側で、IDを配列で処理しているため0スタート
            case "ColaButton": break;
            case "OrangeButton": prodID = 1; break;
            case "AppleButton" : prodID = 2; break;
            case "CoffeeButton" : prodID = 3; break;
            case "CocoaButton" : prodID = 4; break;
            case "WaterButton" : prodID = 5; break;
            case "SparklingWaterButton" : prodID = 6; break;
            case "GreenButton" : prodID = 7; break;
            case "TeaButton" : prodID = 8; break;
        }
//        商品IDをserveDrinkに送って取り出し口に商品を表示させる
        Item prod =  drinkVendor.serveDrink( prodID ) ;
        OutputLabel.setText( prod.getName() );
        OutputLabel.setVisible(true);
//        購入回数が3回に到達したときお釣りを吐き出させて強制終了する
        if( drinkVendor.getPurchase() >= 3 ){
            refund();
            drinkVendor.setPurchase( 0 );
        }
//        総購入金額の表示を行う
        viewNowMoney();
    }
    //    お金ボタンが押されたときの処理
    @FXML
    void pushMoneyBtn(ActionEvent e){
//        何円のボタンが押されたのかのチェック
        Button button = (Button) e.getSource(); // 押されたボタンの情報を取得
        String idString = button.getId(); // fx:idを取得する
        int InputAmount = 0;
        switch (idString){
//            5円の時加算なしで処理を終了する
            case "yen5Button": break ;
//            それ以外のボタンが押されたときは数字を受け取ってsumMoney（総投入金額）に加算する
            case "yen10Button":
                InputAmount = 10 ;
                break;
            case "yen50Button":
                InputAmount = 50 ;
                break;
            case "yen100Button":
                InputAmount = 100 ;
                break;
            case "yen500Button" :
                InputAmount = 500 ;
                break;
            case "yen1000Button":
                InputAmount = 1000 ;
                break;
            default:
        }
//        加算処理
        if (InputAmount >= 5){
            drinkVendor.setSumMoney( drinkVendor.getSumMoney() + InputAmount ) ;
        }
//        changeBtnColorを呼び出して表示の色を変更する
        changeBtnColor() ;
//        総購入金額の表示を行う
        viewNowMoney() ;
    }

    /**
     * 総投入金額をラベル上に表示する処理です。
     */
    public void viewNowMoney() {
        int currentMoney = drinkVendor.getSumMoney();
        if (AmountLabel != null) {
            AmountLabel.setText(currentMoney + "");
        }
    }

    /**
     * ボタンの色を変更する処理です。
     */
    public void changeBtnColor() {
        int currentMoney = drinkVendor.getSumMoney();
        Item[] items = drinkVendor.getItems() ;

        if (drinkButtons != null && items != null) {
            for (int i = 0; i < drinkButtons.length; i++) {
                if (i < items.length && drinkButtons[i] != null) {
                    if (currentMoney >= items[i].getPrice()) {
                        drinkButtons[i].setStyle("-fx-background-color: blue; -fx-text-fill: white;");
                    } else {
                        drinkButtons[i].setStyle("-fx-background-color: red; -fx-text-fill: white;");
                    }
                }
            }
        }
    }

    /**
     * おつりのボタンが押されたときの挙動です。
     */
    @FXML
    public void refund() {
        int currentMoney = drinkVendor.getSumMoney();

        drinkVendor.setSumMoney(0);
        ChangeLabel.setText(currentMoney + "");
        viewNowMoney();
        changeBtnColor();
    }
}
