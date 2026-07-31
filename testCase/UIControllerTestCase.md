# UIControllerテストケース

## 画面操作と表示状態のテスト

| テストメソッド名 / 対象 | テストの内容                           | 入力されている値                                                                | 期待結果                                                                  |
|--|----------------------------------|-------------------------------------------------------------------------|-----------------------------------------------------------------------|
| testAutoRefundAfter3Purchases | 3回購入後に自動返却されて、各ラベルが正しく更新されるか     | 1. #yen1000Button クリック<br>2. #ColaButton クリック<br>3. #OrangeButton クリック<br>4. #AppleButton クリック | #AmountLabel : "0"<br>#ChangeLabel : "500"<br>#OutputLabel : "リンゴジュース" |
| testInsufficientFunds | 残高不足時に商品が購入されないで、投入金額が保持されるか     | 1. #yen100Button クリック<br>2. #CoffeeButton クリック                          | #AmountLabel : "100"                                                  |
| testIgnore5YenCoin | 無効な5円硬貨が投入された時に無視されるか            | 1. #yen50Button クリック<br>2. #yen5Button クリック                             | #AmountLabel : "50"                                                   |
| testNormalPurchase | 200円でコーラを購入して、40円お釣りが返却されるか      | 1. #yen100Button クリック<br>2. #yen100Button クリック<br>3. #ColaButton クリック   | #AmountLabel : "40"<br>#OutputLabel : "コーラ"                           |
| testRefund | 500円を投入して、返却ボタンを押すと正常にお釣りが帰ってくるか | 1. #yen500Button クリック<br>2. #RefundButton クリック                          | #AmountLabel : "0"<br>#ChangeLabel : "500"                            |

## 単体テスト

### initialize() - ボタン配列の初期化処理は正常か

### viewNowMoney() - 総投入金額がAmountLabelに正常に表示されているか
条件：SumMoneyには300が代入されているとき

| 期待     | 結果     |
|:-------|:-------|
| AmountLabelに"300"の表示 | AmountLabelに"300"の表示 |

### changeBtnColor() - 総投入金額によってボタンの色が正常に変化しているか
条件：SumMoneyには120が代入されているとき

| 対象                         | 期待      | 結果 |
|:---------------------------|:--------|:---|
| ColaButton(160円)           | 赤（購入不可） | 赤  |
| WaterButton（110円）          | 青（購入可能） | 青  |
| SparklingWaterButton（120円） | 青       | 青  |

### pushMoneyBtn()_1 - 100円ボタン押下時にsumMoneyに正常に反映されているか
| 期待                        | 結果 |
|:--------------------------|:---|
| setSumMoney(100)が呼び出されている | setSumMoney(100)が呼び出されている  |

### pushMoneyBtn()_2 - 5円ボタン押下時は何の処理も行わない"
| 期待                      | 結果 |
|:------------------------|:---|
| setSumMoney()が呼び出されていない |  setSumMoney()が呼び出されていない  |
## 画面操作と表示状態のテスト

### pushDrinkBtn() - 押したボタンと表示される商品は同じか
| 対象                         | 期待                      | 結果 |
|:---------------------------|:------------------------|:---|
| OrangeButton| outputLabelに"オレンジジュース"と表示 | outputLabelに"オレンジジュース"と表示  |

### refund() - 呼ばれたとき総投入金額がクリアされChangeLabelに反映されるか
| 期待                   | 結果                 |
|:---------------------|:-------------------|
| ChangeLabelに"400"と表示 | ChangeLabelに"400"と表示|
| setSumMoney(0)が呼ばれた  | setSumMoney(0)が呼ばれた|
| テストメソッド名 / 対象 | テストの内容                           | 入力されている値                                                                | 期待結果                                                                  |
|--|----------------------------------|-------------------------------------------------------------------------|-----------------------------------------------------------------------|
| testAutoRefundAfter3Purchases | 3回購入後に自動返却されて、各ラベルが正しく更新されるか     | 1. #yen1000Button クリック<br>2. #ColaButton クリック<br>3. #OrangeButton クリック<br>4. #AppleButton クリック | #AmountLabel : "0"<br>#ChangeLabel : "500"<br>#OutputLabel : "リンゴジュース" |
| testInsufficientFunds | 残高不足時に商品が購入されないで、投入金額が保持されるか     | 1. #yen100Button クリック<br>2. #CoffeeButton クリック                          | #AmountLabel : "100"                                                  |
| testIgnore5YenCoin | 無効な5円硬貨が投入された時に無視されるか            | 1. #yen50Button クリック<br>2. #yen5Button クリック                             | #AmountLabel : "50"                                                   |
| testNormalPurchase | 200円でコーラを購入して、40円お釣りが返却されるか      | 1. #yen100Button クリック<br>2. #yen100Button クリック<br>3. #ColaButton クリック   | #AmountLabel : "40"<br>#OutputLabel : "コーラ"                           |
| testRefund | 500円を投入して、返却ボタンを押すと正常にお釣りが帰ってくるか | 1. #yen500Button クリック<br>2. #RefundButton クリック                          | #AmountLabel : "0"<br>#ChangeLabel : "500"                            |