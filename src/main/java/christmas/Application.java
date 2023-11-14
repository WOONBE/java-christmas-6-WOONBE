package christmas;

import christmas.controller.EventPlanner;

public class Application {

    public static void main(String[] args) {
        EventPlanner planner = new EventPlanner();
        planner.runPlanner();
    }
}
