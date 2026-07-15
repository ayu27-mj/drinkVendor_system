package com.example.drinkVendor_system;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.drinkVendor_system.HelloApplication;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

// アサーション（検証）に必要なメソッドをインポート
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class JavaFXTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void 足し算の結果がラベルに正しく表示されること() {
        // 1. 操作（Arrange/Act）
        clickOn("#one");
        clickOn("#plus");
        clickOn("#two");
        clickOn("#equal"); // 「=」ボタンをクリック

        // 2. 検証（Assert）: 第3日のメインテーマ
        // IDが "display" のラベルのテキストが "3.0" であることを確認する
        verifyThat("#display", hasText("3.0"));
    }

    @Test
    void クリアボタンで表示が空になること() {
        System.out.println("おお");
        clickOn("#one");
        System.out.println("おおじゃないが");
        clickOn("#clear");

        // 表示が消えている（空文字である）ことを検証
        verifyThat("#display", hasText(""));
    }
}
