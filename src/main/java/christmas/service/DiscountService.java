package christmas.service;

import christmas.domain.Menu;
import christmas.utils.Parser;
import christmas.validation.Validator;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

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
        int dDayDiscount = NO_DISCOUNT;
        if(day <= 25){
            dDayDiscount = START_AMOUNT + (day - START_DAY) * DAY_AMOUNT;
            return dDayDiscount;
        }
        return dDayDiscount;
    }

    public  int discountOnDayOfWeek(int day, List<String> nameList, int [] countList){
        int dayOfWeekDiscount = NO_DISCOUNT;
        DayOfWeek dayOfWeek = parser.parseNumberToDayOfWeek(day);
        if(Arrays.stream(weekday).anyMatch(w -> w.equals(dayOfWeek))){
            //디저트 메뉴 할인 적용
            dayOfWeekDiscount = getDayOfWeekDiscount(nameList, countList);
        }
        return dayOfWeekDiscount;
    }

    public int getDayOfWeekDiscount(List<String> nameList, int[] countList) {
        String[] dessertNameList = Menu.getDessertMenu();
        int dayOfWeekDiscount = NO_DISCOUNT;
        for (int i = 0; i < nameList.size(); i++) {
            String menuName = nameList.get(i);
            int menuCount = countList[i];
            int menuDiscount = (int) Arrays.stream(dessertNameList)
                    .filter(name -> name.equals(menuName)) // 주어진 이름이 dessert에 속하는지 필터링
                    .count() * MENU_DISCOUNT_AMOUNT * menuCount; // 디저트 메뉴 개수에 따라 각각 2023원씩 할인 금액을 증가
            dayOfWeekDiscount += menuDiscount;
        }
        return dayOfWeekDiscount;
    }

    public int discountOnWeekend(int day, List<String> nameList, int [] countList) {
        int weekendDiscount = NO_DISCOUNT;
        //주말일 경우 메인 메뉴 각각 2023
        DayOfWeek dayOfWeek = parser.parseNumberToDayOfWeek(day);
        if(Arrays.stream(weekend).anyMatch(w -> w.equals(dayOfWeek))){
            //메인 메뉴 할인 적용
            weekendDiscount = getWeekendDiscount(nameList,countList);
        }
        return weekendDiscount;
    }
    public int getWeekendDiscount(List<String> nameList, int[] countList) {
        String[] mainDishNameList = Menu.getMainDishMenu();
        int weekendDiscount = NO_DISCOUNT;
        for (int i = 0; i < nameList.size(); i++) {
            String menuName = nameList.get(i);
            int menuCount = countList[i];
            int menuDiscount = (int) Arrays.stream(mainDishNameList)
                    .filter(name -> name.equals(menuName)) // 주어진 이름이 dessert에 속하는지 필터링
                    .count() * MENU_DISCOUNT_AMOUNT * menuCount; // 메인 메뉴 개수에 따라 각각 2023원씩 할인 금액을 증가
            weekendDiscount += menuDiscount;
        }
        return weekendDiscount;
    }

    public int discountOnSpecialDay(int day){
        //일요일 이거나 25일이면 총주문금액에서 1000할인
        int specialDiscount = NO_DISCOUNT;
        DayOfWeek dayOfWeek = parser.parseNumberToDayOfWeek(day);
        if(dayOfWeek.equals(DayOfWeek.SUNDAY) || day == 25){
            //총 주문금액 -1000
            specialDiscount += TOTAL_DISCOUNT_AMOUNT;
        }
        return specialDiscount;
    }

    public int souvenirService(List<String> list1, int [] list2){
        int totalAmount = validator.getTotalOrderAmount(list1,list2);
        int souvenirDiscount = NO_DISCOUNT;
        if(totalAmount >= MIN_TOTAL_AMOUNT){
            souvenirDiscount += CHAMPAGNE_PRICE;
        }
        return souvenirDiscount;
    }
    public int getTotalBenefitAmount(int day, List<String> nameList, int [] countList){
        int dDayDiscount = discountOnDday(day);
        int dayOfWeekDiscount = discountOnDayOfWeek(day,nameList,countList);
        int weekendDiscount = discountOnWeekend(day,nameList,countList);
        int specialDayDiscount = discountOnSpecialDay(day);
        int champagnePrice = souvenirService(nameList,countList);

        int totalBenefitAmount = dDayDiscount + dayOfWeekDiscount
                + weekendDiscount + specialDayDiscount + champagnePrice;
        return totalBenefitAmount;
    }








}
