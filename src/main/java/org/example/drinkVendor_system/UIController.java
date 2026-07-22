package org.example.drinkVendor_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UIController {

    // === あなたの担当：FXML・画面要素の宣言 ===
    @FXML private Label nowMoneyLabel;

    @FXML private Button ColaButton;
    @FXML private Button OrangeButton;
    @FXML private Button AppleButton;
    @FXML private Button CoffeeButton;
    @FXML private Button CocoaButton;
    @FXML private Button WaterButton;
    @FXML private Button SparklingWaterButton;
    @FXML private Button GreenButton;
    @FXML private Button TeaButton;

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

    // ==========================================
    // 相手のコード（一切変更していません）
    // ==========================================

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
    @FXML
    private Button pushMoneyBtn; // fxmlファイルのonAction準拠
    //    お金ボタンが押されたときの処理
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
//            どこからDrinkVendor内のsumMoneyを取得するか確認して変更する
            sumMoney += InputAmount ;
        }
//        changeBtnColorを呼び出して表示の色を変更する
        changeBtnColor() ;
    }

    // ==========================================
    // あなたの担当：UI更新・返金処理
    // ==========================================

    /**
     * 総投入金額をラベル上に表示する処理です。
     */
    public void viewNowMoney() {
        int currentMoney = drinkVendor.getSumMoney();
        if (nowMoneyLabel != null) {
            nowMoneyLabel.setText(currentMoney + "円");
        }
    }

    /**
     * ボタンの色を変更する処理です。
     */
    public void changeBtnColor() {
        int currentMoney = drinkVendor.getSumMoney();
        Item[] items = drinkVendor.showItems();

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
        System.out.println("お釣り: " + currentMoney + "円");

        drinkVendor.setSumMoney(0);

        viewNowMoney();
        changeBtnColor();
    }
}
