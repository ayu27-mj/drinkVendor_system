package com.example.drinkVendor_system;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
// 無理やりエラーを外す為の処理
// 本来はjavaクラスと同じ"org.example.drinkVendor_System"をTest直下に作ってその中に入れるらしい
import org.example.drinkVendor_system.DrinkVendor;
import org.example.drinkVendor_system.Item;
import org.example.drinkVendor_system.UIController;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UIControllerTest {

    // DrinkVendorをモック化
    @Mock
    private DrinkVendor drinkVendor;

    // UIControllerインスタンス生成時にモックのdrinkVendorを自動注入
    @InjectMocks
    private UIController controller;

    // UIコンポーネント（JavaFXの要素）
    private Label nowMoneyLabel,
            OutputLabel,
            AmountLabel,
            ChangeLabel;

    private Button ColaButton,
            OrangeButton,
            AppleButton,
            CoffeeButton,
            CocoaButton,
            WaterButton,
            SparklingWaterButton,
            GreenButton,
            TeaButton;

    @BeforeAll
    static void initJavaFX() {
        // JavaFX Toolkitの初期化（UIコンポーネント操作に必要な準備）
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // 既に初期化済みの場合は無視
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        // ダミーのUIコンポーネントを初期化
        nowMoneyLabel = new Label();
        OutputLabel = new Label();
        AmountLabel = new Label();
        ChangeLabel = new Label();

        ColaButton = new Button(); ColaButton.setId("ColaButton");
        OrangeButton = new Button(); OrangeButton.setId("OrangeButton");
        AppleButton = new Button(); AppleButton.setId("AppleButton");
        CoffeeButton = new Button(); CoffeeButton.setId("CoffeeButton");
        CocoaButton = new Button(); CocoaButton.setId("CocoaButton");
        WaterButton = new Button(); WaterButton.setId("WaterButton");
        SparklingWaterButton = new Button(); SparklingWaterButton.setId("SparklingWaterButton");
        GreenButton = new Button(); GreenButton.setId("GreenButton");
        TeaButton = new Button(); TeaButton.setId("TeaButton");

        // リフレクションを使用してUIControllerの@FXMLフィールドへ手動でセット
        setPrivateField(controller, "nowMoneyLabel", nowMoneyLabel);
        setPrivateField(controller, "OutputLabel", OutputLabel);
        setPrivateField(controller, "AmountLabel", AmountLabel);
        setPrivateField(controller, "ChangeLabel", ChangeLabel);

        setPrivateField(controller, "ColaButton", ColaButton);
        setPrivateField(controller, "OrangeButton", OrangeButton);
        setPrivateField(controller, "AppleButton", AppleButton);
        setPrivateField(controller, "CoffeeButton", CoffeeButton);
        setPrivateField(controller, "CocoaButton", CocoaButton);
        setPrivateField(controller, "WaterButton", WaterButton);
        setPrivateField(controller, "SparklingWaterButton", SparklingWaterButton);
        setPrivateField(controller, "GreenButton", GreenButton);
        setPrivateField(controller, "TeaButton", TeaButton);

        // initializeを実行
        controller.initialize();
    }

    private void setPrivateField(Object object, String fieldName, Object value) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    @Test
//    initialize()のテスト
    @DisplayName("initialize(): ボタン配列の初期化処理は正常か")
    void testInitialize() throws Exception {
        Field drinkButtonsField = UIController.class.getDeclaredField("drinkButtons");
        drinkButtonsField.setAccessible(true);
        Button[] drinkButtons = (Button[]) drinkButtonsField.get(controller);

        assertNotNull(drinkButtons);
        assertEquals(9, drinkButtons.length);
        assertEquals(ColaButton, drinkButtons[0]);
        assertEquals(TeaButton, drinkButtons[8]);
    }

    @Test
//    ViewNowMoney()のテスト
//    sumMoney()の値を正しく取得し表示出来ているか
    @DisplayName("viewNowMoney(): 総投入金額がAmountLabelに正常に表示されているか")
    void testViewNowMoney() {
//        drinkVendor.getSumMoney()が300を返すように
        when(drinkVendor.getSumMoney()).thenReturn(300);

        controller.viewNowMoney();

//        Labelの表示内容を検証
        assertEquals("300", AmountLabel.getText());
        verify(drinkVendor, times(1)).getSumMoney();
    }

    @Test
//    changeBtnColor()のテスト
//    投入金額が120円の時、コーラ（160円）と水（110円）と炭酸水（120円）のボタンの色は正しいのか
    @DisplayName("changeBtnColor(): 総投入金額によってボタンの色が正常に変化しているのか")
    void testChangeBtnColor() {
//        商品モックの作成
        Item mockCola = mock(Item.class);
        when(mockCola.getPrice()).thenReturn(160);

        Item mockOrange = mock(Item.class);
        when(mockOrange.getPrice()).thenReturn(160);

        Item mockApple = mock(Item.class);
        when(mockApple.getPrice()).thenReturn(180);

        Item mockCoffee = mock(Item.class);
        when(mockCoffee.getPrice()).thenReturn(180);

        Item mockCocoa = mock(Item.class);
        when(mockCocoa.getPrice()).thenReturn(180);

        Item mockWater = mock(Item.class);
        when(mockWater.getPrice()).thenReturn(110);

        Item mockSparkling = mock(Item.class);
        when(mockSparkling.getPrice()).thenReturn(120);

        Item mockGreenTea = mock(Item.class);
        when(mockGreenTea.getPrice()).thenReturn(160);

        Item mockTea = mock(Item.class);
        when(mockTea.getPrice()).thenReturn(150);

        Item[] mockItems = new Item[]{
                mockCola, mockOrange, mockApple,
                mockCoffee, mockCocoa, mockWater,
                mockSparkling, mockGreenTea, mockTea
        };
//        総投入金額120円の時
        when(drinkVendor.getSumMoney()).thenReturn(120);
        when(drinkVendor.getItems()).thenReturn(mockItems);

        controller.changeBtnColor();

//        総投入金額が120円の時コーラは購入不可（赤）
        assertTrue(ColaButton.getStyle().contains("-fx-background-color: red;"));
//        水は購入可能（青）
        assertTrue(WaterButton.getStyle().contains("-fx-background-color: blue;"));
//        炭酸水は購入可能（青）
        assertTrue(SparklingWaterButton.getStyle().contains("-fx-background-color: blue;"));
    }

    @Test
//    pushMoneyBtn()のテスト
//    代表として100円ボタン、押されたときにsumMoneyに反映されているか
    @DisplayName("pushMoneyBtn(): 100円ボタン押下時にsumMoneyに正常に反映されているか")
    void testPushMoneyBtn_yen100() {
        Button yen100Button = new Button();
        yen100Button.setId("yen100Button");
        ActionEvent event = new ActionEvent(yen100Button, null);
//        初期値としてsumMoneyを問い合わせたとき0を返すようにする
        when(drinkVendor.getSumMoney()).thenReturn(0);
//        pushMoneyBtn()の実行
        controller.pushMoneyBtn(event);

//        100円がsetSumMoneyに反映されているのかの検証（setSumMoney(100)が呼ばれたか）
        verify(drinkVendor).setSumMoney(100);
    }

    @Test
//    pushMoneyBtn()のテスト
    @DisplayName("pushMoneyBtn(): 5円ボタン押下時は何の処理も行わない")
    void testPushMoneyBtn_yen5() {
        Button yen5Button = new Button();
        yen5Button.setId("yen5Button");
        ActionEvent event = new ActionEvent(yen5Button, null);

        controller.pushMoneyBtn(event);

//        setSumMoney()が呼ばれていないかのチェック
        verify(drinkVendor, never()).setSumMoney(anyInt()); // anyInt() -> mockitoで使われ適当な数字として振る舞う
    }

    @Test
    @DisplayName("pushDrinkBtn(): 押したボタンと表示される商品は同じか")
    void testPushDrinkBtn() {
//        オレンジジュースのボタンが押されたとき
        ActionEvent event = new ActionEvent(OrangeButton, null);

        Item mockOrange = mock(Item.class);
        when(mockOrange.getName()).thenReturn("オレンジジュース");
        when(drinkVendor.serveDrink(1)).thenReturn(mockOrange);
        when(drinkVendor.getPurchase()).thenReturn(1);

        controller.pushDrinkBtn(event);

//        serveDrink(prodID:1)が呼び出されたかどうか
        verify(drinkVendor).serveDrink(1);
//        outputLabelに押したボタンの商品（オレンジジュース）が表示されているか
        assertEquals("オレンジジュース", OutputLabel.getText());
        assertTrue(OutputLabel.isVisible());
    }

    @Test
    @DisplayName("refund(): これが呼ばれたとき総投入金額がクリアされChangeLabelに反映されるか")
    void testRefund() {
        when(drinkVendor.getSumMoney()).thenReturn(400);

        controller.refund();

//        総投入金額400円が表示されるか
        assertEquals("400", ChangeLabel.getText());
//        sumMoneyが0にクリアされるか(今回はsetSumMoney(0)が呼ばれたか)
        verify(drinkVendor).setSumMoney(0);
    }
}