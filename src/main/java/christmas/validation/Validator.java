package christmas.validation;

import christmas.domain.Menu;
import christmas.view.ErrorMessage;

import java.util.Arrays;
import java.util.List;

import static christmas.domain.Menu.getMenuNames;

public class Validator {

    private Menu menu;

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 31;

    public boolean isNumberCharInteger(String string) {
        if (!string.chars().allMatch(Character::isDigit)) {
            ErrorMessage.inputDayErrorMessage();
            throw new IllegalArgumentException();
        }
        return true;
    }
    public boolean isValidNumberInRange(int number){
        int start = START_NUMBER;
        int end = END_NUMBER;
        if(number < start || number > end){
            ErrorMessage.inputDayErrorMessage();
            throw new IllegalArgumentException();
        }
        return true;
    }
    //메뉴 검증
    //1. 없는 메뉴일시
    public boolean isValidMenuName(List<String> nameList){
        String[] menuNameList = getMenuNames();
        for(String name : nameList){
            if (Arrays.stream(menuNameList).noneMatch(menuName -> menuName.equals(name))){
                ErrorMessage.inputMenuErrorMessage();
                throw new IllegalArgumentException();
            }
        }return true;
    }





}
