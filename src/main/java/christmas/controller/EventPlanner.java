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

    //시작문구
    //유저 객체에서 날짜 입력 받음, 검증
    //메뉴와 개수 입력 받음, 검증
    //미리보기 문구



    public void runPlanner(){
        startPlanner();
        user = createUser();
        showPlannerResult(user);
    }

    public User createUser(){
        return new User(getUserDay(),getNameListFromUserInput(),getCountListFromUserInput());
    }

    public void startPlanner(){
        outputView.startMessage();
        inputView.beforeInputDay();
        inputValidUserDay();
        inputView.beforeInputMenu();
        inputValidUserMenu();
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



    public int inputValidUserDay(){
        if (userDayCache != 0) {
            return userDayCache;
        }
        while(true){
            try {
                return inputAndValidateUserDay();
            }catch (IllegalArgumentException e){
                return inputValidUserDay();
            }
        }
    }

    private int inputAndValidateUserDay() {
        String inputStringDay = inputView.inputExpectDay();
        int userDay = validateInputUserDay(inputStringDay);
        userDayCache = userDay;
        return userDay;
    }

    public int getUserDay() {
        return inputValidUserDay();
    }

    private int validateInputUserDay(String inputStringDay) {
        validator.isNumberCharInteger(inputStringDay);
        int userDay = parser.parseInputStringNumber(inputStringDay);
        validator.isValidNumberInRange(userDay);
        return userDay;
    }


    public Map<String, Object> inputValidUserMenu(){
        if (userInputMenuCache != null) {
            return userInputMenuCache;
        }
        while (true){
            try {
                return inputAndValidateUserMenu();
            }
            catch (IllegalArgumentException e){
                return inputValidUserMenu();
            }
        }
    }

    private Map<String, Object> inputAndValidateUserMenu() {
        String inputMenu = inputView.inputOrderMenu();
        Map<String, Object> userInput = validateUserInputMenu(inputMenu);
        userInputMenuCache = userInput;
        return userInput;
    }

    private Map<String, Object> validateUserInputMenu(String inputMenu) {
        List<String> list = parser.stringToArray(inputMenu);
        List<String> nameList = getValidStringList(list);
        int [] countList = parser.stringListToIntArray( userService.inputMenuToCountList(list));
        validateAllMenuInput(nameList, countList);
        return Map.of("nameList", nameList, "countList", countList);
    }

    public List<String> getNameListFromUserInput() {
        Map<String, Object> userInput = inputValidUserMenu();
        List<String> nameList =  (List<String>) userInput.get("nameList");
        return nameList;
    }

    public int[] getCountListFromUserInput() {
        Map<String, Object> userInput = inputValidUserMenu();
        int[] countList = (int[]) userInput.get("countList");
        return countList;
    }

    private void validateAllMenuInput(List<String> nameList, int[] countList) {
        validator.isValidUserInputNameList(nameList);
        validator.isValidUserInputCountList(countList);
    }

    private List<String> getValidStringList(List<String> list) {
        validator.isValidInputForm(list);
        List<String> nameList = userService.inputMenuToNameList(list);
        return nameList;
    }

}
