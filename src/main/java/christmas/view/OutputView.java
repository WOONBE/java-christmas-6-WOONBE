package christmas.view;

import christmas.service.UserService;
import christmas.utils.Parser;
import christmas.validation.Validator;

import java.util.List;

public class OutputView {

    private static final int NO_DISCOUNT = 0;

    Validator validator = new Validator();
    Parser parser = new Parser();

    UserService userService = new UserService();

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
        int totalAmount = userService.getTotalOrderAmount(list1,list2);
        String decimalTotalAmount = parser.inputMoneyToDecimalFormat(totalAmount);
        System.out.println(decimalTotalAmount+"원");
    }

    //리팩토링 필요
    public void printSouvenirMenu(List<String> list1, int [] list2){
        System.out.println("\n<증정 메뉴>");
        if(userService.souvenirService(list1,list2) != 25000 || userService.getTotalOrderAmount(list1,list2) < 10000){
            System.out.println("없음");
            return;
        }
        System.out.println("샴페인 1개");
    }

    //모든 혜택 내역 출력
    public void printAllBenefits(int day, List<String> nameList, int [] countList){
        System.out.println("\n<혜택 내역>");
        if(userService.getTotalBenefitAmount(day,nameList,countList) == NO_DISCOUNT || userService.getTotalOrderAmount(nameList,countList) < 10000){
            System.out.println("없음");
            return;
        }
        printDdayDiscount(day);
        printDayOfWeekDiscount(day,nameList,countList);
        printWeekendDiscount(day,nameList,countList);
        printSpecialDayDiscount(day);
        printSouvenirDiscount(nameList,countList);
    }
    public void printTotalBenefitAmount(int day, List<String> nameList, int [] countList){
        System.out.println("\n<총혜택 금액>");
        int totalBenefitAmount = userService.getTotalBenefitAmount(day,nameList,countList);
        String decimalTotalBenefitAmount = parser.inputMoneyToDecimalFormat(totalBenefitAmount);
        System.out.println(decimalTotalBenefitAmount+"원");
    }

    public void printExpectPayAmount(int day, List<String> nameList, int [] countList){
        System.out.println("\n<할인 후 예상 결제 금액>");
        int totalAmount = userService.getTotalOrderAmount(nameList,countList);
        int benefitAmount = userService.getTotalBenefitAmount(day,nameList,countList);
        int expectPayAmount = totalAmount - benefitAmount;
        String decimalExpectPayAmount = parser.inputMoneyToDecimalFormat(expectPayAmount);
        System.out.println(decimalExpectPayAmount+"원");
    }
    public void printBadge(int day, List<String> list1, int [] list2){
        System.out.println("\n<12월 이벤트 배지>");
        String badge = userService.giveBadge(day,list1,list2);
        System.out.println(badge);
    }

    //0인지 판별하는 로직 추가해야함(if로),추가 했음
    public void printDdayDiscount(int day){
        int dDayDiscount = userService.discountOnDday(day);
        if(dDayDiscount != NO_DISCOUNT) {
            String decimalDdayDiscount = parser.inputMoneyToDecimalFormat(dDayDiscount);
            System.out.println("크리스마스 디데이 할인: -" + decimalDdayDiscount + "원");
        }
    }
    public void printDayOfWeekDiscount(int day, List<String> nameList, int [] countList){
        int dayOfWeekDiscount = userService.discountOnDayOfWeek(day,nameList,countList);
        if(dayOfWeekDiscount != NO_DISCOUNT) {
            String decimalDayOfWeekDiscount = parser.inputMoneyToDecimalFormat(dayOfWeekDiscount);
            System.out.println("평일 할인: -" + decimalDayOfWeekDiscount + "원");
        }
    }
    public void printWeekendDiscount(int day, List<String> nameList, int [] countList){
        int weekendDiscount = userService.discountOnWeekend(day,nameList,countList);
        if(weekendDiscount != NO_DISCOUNT) {
            String decimalWeekendDiscount = parser.inputMoneyToDecimalFormat(weekendDiscount);
            System.out.println("주말 할인: -" + decimalWeekendDiscount + "원");
        }
    }
    public void printSpecialDayDiscount(int day){
        int specialDayDiscount = userService.discountOnSpecialDay(day);
        if(specialDayDiscount != NO_DISCOUNT) {
            String decimalSpecialDayDiscount = parser.inputMoneyToDecimalFormat(specialDayDiscount);
            System.out.println("특별 할인: -" + decimalSpecialDayDiscount + "원");
        }
    }

    public void printSouvenirDiscount(List<String> list1, int [] list2){
        int champagnePrice = userService.souvenirService(list1,list2);
        if(champagnePrice != NO_DISCOUNT) {
            String decimalChampagnePrice = parser.inputMoneyToDecimalFormat(champagnePrice);
            System.out.println("증정 이벤트: -" + decimalChampagnePrice + "원");
        }
    }




}
