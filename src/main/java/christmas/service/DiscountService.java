package christmas.service;

import christmas.utils.Parser;
import christmas.validation.Validator;

import java.time.DayOfWeek;

public class DiscountService {

    private final Parser parser = new Parser();
    private final Validator validator = new Validator();
    private static final int START_AMOUNT = 1000;
    private static final int START_DAY = 1;
    private static final int NO_DISCOUNT = 0;

    private static final int DAY_AMOUNT = 100;

    private static final int MENU_DISCOUNT_AMOUNT = 2023;

    private static final int TOTAL_DISCOUNT_AMOUNT = 1000;

    private static final int MIN_TOTAL_AMOUNT = 120000;
    private static final int CHAMPAGNE_PRICE = 25000;

    private final DayOfWeek[] weekday = {DayOfWeek.SUNDAY, DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY};

    private final DayOfWeek [] weekend = {DayOfWeek.FRIDAY,DayOfWeek.SATURDAY};



    public int discountOnDday(int day){
        int dDaydiscount = NO_DISCOUNT;
        if(day <= 25){
            dDaydiscount = START_AMOUNT + (day - START_DAY) * DAY_AMOUNT;
            return dDaydiscount;
        }
        return dDaydiscount;
    }




}
