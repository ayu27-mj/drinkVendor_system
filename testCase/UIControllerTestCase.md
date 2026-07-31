## 画面操作と表示状態のテスト

| テストメソッド名 / 対象 | テストの内容                           | 入力されている値                                                                | 期待結果                                                                  |
|--|----------------------------------|-------------------------------------------------------------------------|-----------------------------------------------------------------------|
| testAutoRefundAfter3Purchases | 3回購入後に自動返却されて、各ラベルが正しく更新されるか     | 1. #yen1000Button クリック<br>2. #ColaButton クリック<br>3. #OrangeButton クリック<br>4. #AppleButton クリック | #AmountLabel : "0"<br>#ChangeLabel : "500"<br>#OutputLabel : "リンゴジュース" |
| testInsufficientFunds | 残高不足時に商品が購入されないで、投入金額が保持されるか     | 1. #yen100Button クリック<br>2. #CoffeeButton クリック                          | #AmountLabel : "100"                                                  |
| testIgnore5YenCoin | 無効な5円硬貨が投入された時に無視されるか            | 1. #yen50Button クリック<br>2. #yen5Button クリック                             | #AmountLabel : "50"                                                   |
| testNormalPurchase | 200円でコーラを購入して、40円お釣りが返却されるか      | 1. #yen100Button クリック<br>2. #yen100Button クリック<br>3. #ColaButton クリック   | #AmountLabel : "40"<br>#OutputLabel : "コーラ"                           |
| testRefund | 500円を投入して、返却ボタンを押すと正常にお釣りが帰ってくるか | 1. #yen500Button クリック<br>2. #RefundButton クリック                          | #AmountLabel : "0"<br>#ChangeLabel : "500"                            |