package com.example.drinkVendor_system;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.drinkVendor_system.DrinkVendorApplication;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class JavaFXTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(DrinkVendorApplication.class.getResource("drinkVendor-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void testAutoRefundAfter3Purchases() {
        clickOn("#yen1000Button");
        verifyThat("#AmountLabel", hasText("1000"));

        clickOn("#ColaButton");
        clickOn("#OrangeButton");
        clickOn("#AppleButton");

        verifyThat("#AmountLabel", hasText("0"));
        verifyThat("#ChangeLabel", hasText("500"));
        verifyThat("#OutputLabel", hasText("リンゴジュース"));
    }

    @Test
    void testInsufficientFunds() {
        clickOn("#yen100Button");
        verifyThat("#AmountLabel", hasText("100"));

        clickOn("#CoffeeButton");

        verifyThat("#AmountLabel", hasText("100"));
    }

    @Test
    void testIgnore5YenCoin() {
        clickOn("#yen50Button");
        verifyThat("#AmountLabel", hasText("50"));

        clickOn("#yen5Button");

        verifyThat("#AmountLabel", hasText("50"));
    }

    @Test
    void testNormalPurchase() {
        clickOn("#yen100Button");
        clickOn("#yen100Button");

        clickOn("#ColaButton");

        verifyThat("#OutputLabel", hasText("コーラ"));
        verifyThat("#AmountLabel", hasText("40"));
    }

    @Test
    void testRefund() {
        clickOn("#yen500Button");

        clickOn("#RefundButton");

        verifyThat("#AmountLabel", hasText("0"));
        verifyThat("#ChangeLabel", hasText("500"));
    }
}