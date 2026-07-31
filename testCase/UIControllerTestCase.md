# UIControllerテストケース
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

### pushDrinkBtn() - 押したボタンと表示される商品は同じか
| 対象                         | 期待                      | 結果 |
|:---------------------------|:------------------------|:---|
| OrangeButton| outputLabelに"オレンジジュース"と表示 | outputLabelに"オレンジジュース"と表示  |

### refund() - 呼ばれたとき総投入金額がクリアされChangeLabelに反映されるか
| 期待                   | 結果                 |
|:---------------------|:-------------------|
| ChangeLabelに"400"と表示 | ChangeLabelに"400"と表示|
| setSumMoney(0)が呼ばれた  | setSumMoney(0)が呼ばれた|