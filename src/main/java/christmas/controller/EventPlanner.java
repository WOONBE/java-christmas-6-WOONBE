package christmas.controller;

import christmas.domain.User;
import christmas.service.UserService;
import christmas.utils.Parser;
import christmas.validation.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class EventPlanner {


    private Map<String, Object> userInputMenuCache;
    private int userDayCache = 0;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Parser parser = new Parser();
    private final UserService userService = new UserService();
    private final Validator validator = new Validator();

    private User user;


    public void runPlanner(){
        startPlanner();
        user = createUser();
        showPlannerResult(user);
    }

    private User createUser(){
        return new User(getUserDay(),getNameListFromUserInput(),getCountListFromUserInput());
    }

    private void startPlanner(){
        outputView.startMessage();
        inputView.beforeInputDay();
        inputUserDay();
        inputView.beforeInputMenu();
        inputUserMenu();
    }

    public void showPlannerResult(User user){
        outputView.printMonthAndDay(user.getDay());
        outputView.printOrderMenu(user.getNameList(),user.getCountList());
        outputView.printTotalAmount(user.getNameList(),user.getCountList());
        outputView.printSouvenirMenu(user.getNameList(),user.getCountList());
        outputView.printAllBenefits(user.getDay(),user.getNameList(),user.getCountList());
        outputView.printTotalBenefitAmount(user.getDay(),user.getNameList(),user.getCountList());
        outputView.printExpectPayAmount(user.getDay(),user.getNameList(),user.getCountList());
        outputView.printBadge(user.getDay(),user.getNameList(),user.getCountList());
    }



    private int inputUserDay(){
        if (userDayCache != 0) {
            return userDayCache;
        }
        while(true){
            try {
                String inputStringDay = inputView.inputExpectDay();
                return validateInputUserDay(inputStringDay);
            }catch (IllegalArgumentException e){
                return inputUserDay();
            }
        }
    }

    private int validateInputUserDay(String input) {
        int userDay = validateUserDay(input);
        userDayCache = userDay;
        return userDay;
    }

    private int getUserDay() {
        return inputUserDay();
    }

    private int validateUserDay(String inputStringDay) {
        validator.isNumberCharInteger(inputStringDay);
        int userDay = parser.parseInputStringNumber(inputStringDay);
        validator.isValidNumberInRange(userDay);
        return userDay;
    }


    private Map<String, Object> inputUserMenu(){
        if (userInputMenuCache != null) {
            return userInputMenuCache;
        }
        while (true){
            try {
                String inputMenu = inputView.inputOrderMenu();
                return validateUserInputMenu(inputMenu);
            }
            catch (IllegalArgumentException e){
                return inputUserMenu();
            }
        }
    }

    //이거 리팩토링 필요(input이랑 validate 분리) -> 1124 분리 완료
    private Map<String, Object> validateUserInputMenu(String input) {
        Map<String, Object> userInput = validateUserMenu(input);
        userInputMenuCache = userInput;
        return userInput;
    }

    private Map<String, Object> validateUserMenu(String inputMenu) {
        List<String> list = parser.stringToArray(inputMenu);
        List<String> nameList = getValidNameList(list);
        int [] countList = parser.stringListToIntArray( userService.inputMenuToCountList(list));
        validateAllInputMenu(nameList, countList);
        return Map.of("nameList", nameList, "countList", countList);
    }

    private List<String> getNameListFromUserInput() {
        Map<String, Object> userInput = inputUserMenu();
        List<String> nameList =  (List<String>) userInput.get("nameList");
        return nameList;
    }

    private int[] getCountListFromUserInput() {
        Map<String, Object> userInput = inputUserMenu();
        int[] countList = (int[]) userInput.get("countList");
        return countList;
    }

    private void validateAllInputMenu(List<String> nameList, int[] countList) {
        validator.isValidUserInputNameList(nameList);
        validator.isValidUserInputCountList(countList);
    }

    private List<String> getValidNameList(List<String> list) {
        validator.isValidInputForm(list);
        List<String> nameList = userService.inputMenuToNameList(list);
        return nameList;
    }

}
