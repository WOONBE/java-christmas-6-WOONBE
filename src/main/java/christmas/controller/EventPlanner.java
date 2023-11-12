package christmas.controller;

import christmas.utils.Parser;
import christmas.validation.Validator;
import christmas.view.InputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {

    private final InputView inputView = new InputView();
    private final Parser parser = new Parser();

    private final Validator validator = new Validator();

    //시작문구
    //유저 객체에서 날짜 입력 받음, 검증
    //메뉴와 개수 입력 받음, 검증
    //미리보기 문구

    public int inputValidUserDay(){
        while (true){
            try {
                String inputStringDay = inputView.inputExpectDay();
                validator.isNumberCharInteger(inputStringDay);
                int userDay = parser.parseInputStringNumber(inputStringDay);
                validator.isValidNumberInRange(userDay);
                return userDay;
            }catch (IllegalArgumentException e){
                return inputValidUserDay();
            }
        }
    }
    public Map<String, Object> inputValidUserMenu(){
        while (true){
            try {
                String inputMenu = inputView.inputOrderMenu();
                List<String> list = parser.stringToArray(inputMenu);
                List<String> nameList = getStringList(list);
                int [] countList = parser.stringListToIntArray( parser.inputMenuToCountList(list));
                validateAllMenuInput(nameList, countList);
                return Map.of("nameList", nameList, "countList", countList);
            }
            catch (IllegalArgumentException e){
                return inputValidUserMenu();
            }
        }
    }


    private void validateAllMenuInput(List<String> nameList, int[] countList) {
        validator.isValidUserInputNameList(nameList);
        validator.isValidUserInputCountList(countList);
    }

    private List<String> getStringList(List<String> list) {
        validator.isValidInputForm(list);
        List<String> nameList = parser.inputMenuToNameList(list);
        return nameList;
    }

}
