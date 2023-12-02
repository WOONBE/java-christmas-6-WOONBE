package christmas;

import christmas.controller.EventPlanner;
import christmas.domain.Menu;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        EventPlanner planner = new EventPlanner();
        planner.runPlanner();
    }
}
