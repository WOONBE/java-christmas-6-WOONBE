package christmas.service;

public class BadgeService {

    private static final int SANTA_AMOUNT = 20000;
    private static final int TREE_AMOUNT = 10000;
    private static final int STAR_AMOUNT = 5000;

    public String  giveBadge(int total){
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
