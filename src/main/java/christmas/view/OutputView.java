package christmas.view;

import christmas.service.BadgeService;
import christmas.service.DiscountService;
import christmas.utils.Parser;
import christmas.validation.Validator;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

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
    public void printSouvenir(List<String> list1, int [] list2){
        System.out.println("\n<증정 메뉴>");
        if(discountService.souvenirService(list1,list2) != 25000){
            System.out.println("없음");
            return;
        }
        System.out.println("샴페인 1개");
    }
    public void printBadge(List<String> list1, int [] list2){
        System.out.println("\n<12월 이벤트 배지>");
        String badge = badgeService.giveBadge(list1,list2);
        System.out.println(badge);
    }




}
