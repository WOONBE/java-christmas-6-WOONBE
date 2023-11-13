package christmas;

import christmas.controller.EventPlanner;

public class Application {

    public static void main(String[] args) {
        String string = "시저샐러드-1,티본스테이크-1,레드와인-1,제로콜라-1,아이스크림-2";
        String string1 = "제로콜라-3, 레드와인-2, 샴페인-1";
        EventPlanner planner = new EventPlanner();
        planner.runPlanner();
    }
}
