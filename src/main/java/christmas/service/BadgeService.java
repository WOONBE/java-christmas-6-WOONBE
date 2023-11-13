package christmas.service;

import christmas.validation.Validator;

import java.util.List;

public class BadgeService {

    private static final int SANTA_AMOUNT = 20000;
    private static final int TREE_AMOUNT = 10000;
    private static final int STAR_AMOUNT = 5000;

    private final Validator validator = new Validator();
    private final DiscountService discountService = new DiscountService();
    public String giveBadge(int day,List<String> list1, int [] list2){

        //이거 혜택금액이라서 무조건 고쳐야함(총주문 금액이 아님)
        int total = discountService.getTotalBenefitAmount(day,list1, list2);
        String badge = "";

        if(total >= SANTA_AMOUNT){
            return badge = "산타";
        }
        if(total >= TREE_AMOUNT){
            return badge = "트리";
        }
        if(total >= STAR_AMOUNT){
            return badge = "별";
        }
        return "없음";
    }
}
