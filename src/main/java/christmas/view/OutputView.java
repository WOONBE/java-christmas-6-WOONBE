package christmas.view;

import christmas.service.BadgeService;
import christmas.service.DiscountService;
import christmas.utils.Parser;
import christmas.validation.Validator;

import java.util.List;

public class OutputView {

    private static final int NO_DISCOUNT = 0;

    Validator validator = new Validator();
    Parser parser = new Parser();

    DiscountService discountService = new DiscountService();

    BadgeService badgeService = new BadgeService();

    public void startMessage(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
    
    public void printMonthAndDay(int day){
        System.out.printf("\n12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", day);

    }

    //메뉴와 개수 print
    public void printOrderMenu(List<String> list1, int [] list2){
        System.out.println("\n<주문 메뉴>");
        for (int i = 0; i < list1.size(); i++){
            String menuName = list1.get(i);
            int menuCount = list2[i];
            System.out.printf("%s %d개\n",menuName,menuCount);
        }
    }
    public void printTotalAmount(List<String> list1, int [] list2){
        System.out.println("\n<할인 전 총주문 금액>");
        int totalAmount = validator.getTotalOrderAmount(list1,list2);
        String decimalTotalAmount = parser.inputMoneyToDecimalFormat(totalAmount);
        System.out.println(decimalTotalAmount+"원");
    }

    //리팩토링 필요
    public void printSouvenirMenu(List<String> list1, int [] list2){
        System.out.println("\n<증정 메뉴>");
        if(discountService.souvenirService(list1,list2) != 25000){
            System.out.println("없음");
            return;
        }
        System.out.println("샴페인 1개");
    }

    //모든 혜택 내역 출력
    public void printAllBenefits(int day, List<String> nameList, int [] countList){
        System.out.println("\n<혜택 내역>");
        if(discountService.getTotalBenefitAmount(day,nameList,countList) == NO_DISCOUNT){
            System.out.println("없음");
            return;
        }
        printDdayDiscount(day);
        printDayOfWeekDiscount(day,nameList,countList);
        printWeekendDiscount(day,nameList,countList);
        printSpecialDayDiscount(day);
        printSouvenirDiscount(nameList,countList);
    }
    public void printBadge(List<String> list1, int [] list2){
        System.out.println("\n<12월 이벤트 배지>");
        String badge = badgeService.giveBadge(list1,list2);
        System.out.println(badge);
    }

    //0인지 판별하는 로직 추가해야함(if로)
    public void printDdayDiscount(int day){
        int dDayDiscount = discountService.discountOnDday(day);
        if(dDayDiscount != NO_DISCOUNT) {
            String decimalDdayDiscount = parser.inputMoneyToDecimalFormat(dDayDiscount);
            System.out.println("크리스마스 디데이 할인: -" + decimalDdayDiscount + "원");
        }
    }
    public void printDayOfWeekDiscount(int day, List<String> nameList, int [] countList){
        int dayOfWeekDiscount = discountService.discountOnDayOfWeek(day,nameList,countList);
        if(dayOfWeekDiscount != NO_DISCOUNT) {
            String decimalDayOfWeekDiscount = parser.inputMoneyToDecimalFormat(dayOfWeekDiscount);
            System.out.println("평일 할인: -" + decimalDayOfWeekDiscount + "원");
        }
    }
    public void printWeekendDiscount(int day, List<String> nameList, int [] countList){
        int weekendDiscount = discountService.discountOnWeekend(day,nameList,countList);
        if(weekendDiscount != NO_DISCOUNT) {
            String decimalWeekendDiscount = parser.inputMoneyToDecimalFormat(weekendDiscount);
            System.out.println("주말 할인: -" + decimalWeekendDiscount + "원");
        }
    }
    public void printSpecialDayDiscount(int day){
        int specialDayDiscount = discountService.discountOnSpecialDay(day);
        if(specialDayDiscount != NO_DISCOUNT) {
            String decimalSpecialDayDiscount = parser.inputMoneyToDecimalFormat(specialDayDiscount);
            System.out.println("특별 할인: -" + decimalSpecialDayDiscount + "원");
        }
    }

    public void printSouvenirDiscount(List<String> list1, int [] list2){
        int champagnePrice = discountService.souvenirService(list1,list2);
        if(champagnePrice != NO_DISCOUNT) {
            String decimalChampagnePrice = parser.inputMoneyToDecimalFormat(champagnePrice);
            System.out.println("증정 이벤트: -" + decimalChampagnePrice + "원");
        }
    }




}
