package org.example.drinkVendor_system;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UIController {

    @FXML
    private Label nowMoneyLabel;

    @FXML private Button ColaButton;
    @FXML private Button OrangeButton;
    @FXML private Button AppleButton;
    @FXML private Button CoffeeButton;
    @FXML private Button CocoaButton;
    @FXML private Button WaterButton;
    @FXML private Button SparklingWaterButton;
    @FXML private Button GreenButton;
    @FXML private Button TeaButton;

    private Button[] drinkButtons;

    private DrinkVendor drinkVendor = new DrinkVendor();

    @FXML
    public void initialize() {
        drinkButtons = new Button[]{
                ColaButton, OrangeButton, AppleButton,
                CoffeeButton, CocoaButton, WaterButton,
                SparklingWaterButton, GreenButton, TeaButton
        };
    }

    public void viewNowMoney() {
        int currentMoney = drinkVendor.getSumMoney();
        if (nowMoneyLabel != null) {
            nowMoneyLabel.setText(currentMoney + "円");
        }
    }

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

    @FXML
    public void refund() {
        int currentMoney = drinkVendor.getSumMoney();
        System.out.println("お釣り: " + currentMoney + "円");

        drinkVendor.resetSumMoney();

        viewNowMoney();

        changeBtnColor();
    }
}