package christmas.domain;

import java.util.Arrays;

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

    //입력받은 이름과 같은 메뉴면 그 메뉴의 가격 리턴
    public static int getMenuPrice(String input){
        return Arrays.stream(values())
                .filter(menu -> input.equals(menu.getName()))
                .mapToInt(Menu::getPrice)
                .findFirst()
                .orElse(-1);
    }

    public static String[] getMenuNames() {
        return Arrays.stream(Menu.values())
                .map(Menu::getName)
                .toArray(String[]::new);
    }

    public static String[] getAppetizerMenu() {
        return Arrays.stream(values())
                .filter(menu -> "appetizer".equals(menu.getType()))
                .map(Menu::getName)
                .toArray(String[]::new);
    }
    public static String[] getMainDishMenu() {
        return Arrays.stream(values())
                .filter(menu -> "mainDish".equals(menu.getType()))
                .map(Menu::getName)
                .toArray(String[]::new);
    }

    public static String[] getDessertMenu() {
        return Arrays.stream(values())
                .filter(menu -> "dessert".equals(menu.getType()))
                .map(Menu::getName)
                .toArray(String[]::new);
    }

    public static String[] getDrinkMenu() {
        return Arrays.stream(values())
                .filter(menu -> "drink".equals(menu.getType()))
                .map(Menu::getName)
                .toArray(String[]::new);
    }


}
