package christmas.view;

import java.util.List;

public class OutputView {

    public void startMessage(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
    
    public void printMonthAndDay(int day){
        System.out.printf("\n12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", day);

    }

    //메뉴와 개수 print
    public void printOrderMenu(List<String> list1, int [] list2){
        System.out.println("<주문 메뉴>");
        for (int i = 0; i < list1.size(); i++){
            String menuName = list1.get(i);
            int menuCount = list2[i];
            System.out.printf("%s %d개\n",menuName,menuCount);
        }

    }


}
