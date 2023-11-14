package christmas.validation;

import christmas.domain.Menu;
import christmas.view.ErrorMessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static christmas.domain.Menu.getMenuNames;

public class Validator {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 31;

    //day input시 사용
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

    public List<String> isValidUserInputNameList(List<String> nameList){
        isValidMenuName(nameList);
        isDuplicateMenu(nameList);
        isOrderAllDrink(nameList);
        return nameList;
    }

    public int [] isValidUserInputCountList(int [] countList){
        isValidOrderCount(countList);
        isValidTotalOrderCount(countList);
        return countList;
    }
    //메뉴 검증
    //1. 없는 메뉴일시(nameList)
    public boolean isValidMenuName(List<String> nameList){
        String[] menuNameList = getMenuNames();
        for(String name : nameList){
            if (Arrays.stream(menuNameList).noneMatch(menuName -> menuName.equals(name))){
                ErrorMessage.inputMenuErrorMessage();
                throw new IllegalArgumentException();
            }
        }return true;
    }

    //2. 개수 1개 이상이 아닐시(intCountList에서)
    public boolean isValidOrderCount(int [] list){
        for(Integer count : list){
            if(count < 1){
                ErrorMessage.inputMenuErrorMessage();
                throw new IllegalArgumentException();
            }
        }return true;
    }

    //3. 입력 형식이 다른 경우 (음식-개수)/2개 리스트로 반환전
    public boolean isValidInputForm(List<String> list){
        for(String input : list){
            if(!input.contains("-")|| !input.matches(".*\\d.*")){
                ErrorMessage.inputMenuErrorMessage();
                throw new IllegalArgumentException();
            }
        }return true;
    }
    //4. 중복되는 메뉴가 있는 경우(nameList)
    public boolean isDuplicateMenu(List<String> nameList){
        HashSet<String> set = new HashSet<>(nameList);
        if(nameList.size() != set.size()){
            ErrorMessage.inputMenuErrorMessage();
            throw new IllegalArgumentException();
        }
        return true;
    }
    //메뉴는 20개가 넘어가면 안된다
    public boolean isValidTotalOrderCount(int [] list){
        int totalCount = 0;
        for(int count : list){
            totalCount += count;
        }
        if(totalCount > 20){
            ErrorMessage.inputMenuErrorMessage();
            throw new IllegalArgumentException();
        }
        return true;
    }

    //음료만 주문시 주문 불가, 나중에 메서드 따로 분리
    public boolean isOrderAllDrink(List<String> nameList){
        String [] drinkNameList = Menu.getDrinkMenu();
        if (Arrays.stream(nameList.toArray()).allMatch(Arrays.asList(drinkNameList)::contains)){
            ErrorMessage.inputMenuErrorMessage();
            throw new IllegalArgumentException();
        }return true;
    }




}
