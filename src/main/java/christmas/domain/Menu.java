package christmas.domain;

public enum Menu {
    SOUP("appetizer","양송이스프", 6000),
    TAPAS("appetizer","타파스",5500),
    SALAD("appetizer", "시저샐러드", 8000),
    STEAK("mainDish","티본스테이크",55000),
    RIB("mainDish","바베큐립",54000),
    SEA_FOOD_PASTA("mainDish","해산물파스타",35000),
    CHRISTMAS_PASTA("mainDish","크리스마스파스타",25000),
    CAKE("dessert","초코케이크",15000),
    ICE_CREAM("dessert","아이스크림",5000),
    COKE("drink","제로콜라",3000),
    WINE("drink","레드와인",60000),
    CHAMPAGNE("drink","샴페인",25000);


    private String type;

    private String name;

    private int price;

    Menu(String type, String name, int price){
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


}
