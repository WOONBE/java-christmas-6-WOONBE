package christmas.validation;

import christmas.domain.Menu;
import christmas.view.ErrorMessage;

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




}
