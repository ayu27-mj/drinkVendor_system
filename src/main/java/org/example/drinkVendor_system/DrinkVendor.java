package org.example.drinkVendor_system;

public class DrinkVendor {

    private Item items[]={
            new Item(1,"コーラ",160,"@../../../img/coke.png"),
            new Item(2,"オレンジジュース",160,"@../../../img/orange.png"),
            new Item(3,"リンゴジュース",180,"@../../../img/apple.png"),
            new Item(4,"コーヒー",180,"@../../../img/coffe.png"),
            new Item(5,"ココア",180,"@../../../img/kokoa.png"),
            new Item(6,"水",110,"@../../../img/mizu.png"),
            new Item(7,"炭酸水",120,"@../../../img/tansan.png"),
            new Item(8,"緑茶",160,"@../../../img/green.png"),
            new Item(9,"紅茶",150,"@../../../img/tea.png")
    };
    private int sumMoney=0;
    private int purchase=0;

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item items[]) {
        this.items = items;
    }

    public int getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }

    public Item serveDrink(int drinkNumber){
        if(sumMoney>= items[drinkNumber].getPrice()){
            purchase++;
            sumMoney-=items[drinkNumber].getPrice();
            return items[drinkNumber];
        }else{
            return null;
        }
    }
}
