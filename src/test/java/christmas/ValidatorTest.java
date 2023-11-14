package christmas;

import christmas.utils.Parser;
import christmas.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private final Validator validator = new Validator();
    private final Parser parser = new Parser();

    @DisplayName("입력한 요일이 범위내의 숫자이면서 숫자형식인지 검증")
    @Test
    void validateInputDayTest(){

        String inputStringDay1 = "3";
        String inputStringDay2 = "a";
        String inputStringDay3 = "32";

        //1.범위 안의 숫자면서 숫자 형식일 경우
        validator.isNumberCharInteger(inputStringDay1);
        int userDay1 = parser.parseInputStringNumber(inputStringDay1);
        assertTrue(validator.isValidNumberInRange(userDay1));

        //2.숫자 형식이 아닐 경우 예외 발생
        assertThatThrownBy(() -> {
            validator.isNumberCharInteger(inputStringDay2);
            int userDay2 = parser.parseInputStringNumber(inputStringDay2);
            validator.isValidNumberInRange(userDay2);
        }).isInstanceOf(IllegalArgumentException.class);

        //3.숫자가 범위 밖일 경우 예외 발생
        assertThatThrownBy(() -> {
            validator.isNumberCharInteger(inputStringDay3);
            int userDay3 = parser.parseInputStringNumber(inputStringDay3);
            validator.isValidNumberInRange(userDay3);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("입력받은 메뉴 이름이 유효한 이름인지 검증")
    @Test
    void validateMenuNameTest(){


        //1. 없는 메뉴명을 입력시 예외 발생
        assertThatThrownBy(() -> {
            validator.isValidMenuName(List.of("샐러드","레드와인","제로콜라","아이스크림"));
        }).isInstanceOf(IllegalArgumentException.class);

        //2. 중복되는 메뉴 입력시 예외 발생
        assertThatThrownBy(() -> {
            validator.isDuplicateMenu(List.of("시저샐러드","시저샐러드","레드와인","제로콜라"));
        }).isInstanceOf(IllegalArgumentException.class);

        //3. 주문 메뉴가 모두 음료일때 예외 발생
        assertThatThrownBy(() -> {
            validator.isOrderAllDrink(List.of("레드와인","제로콜라"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력받은 메뉴의 주문 개수가 유효한지 검증")
    @Test
    void validateMenuCountTest(){

        //1. 주문 메뉴의 개수가 하나라도 1미만이면 예외 발생
        int [] count1 = {1,0,2,3,5};
        assertThatThrownBy(() -> {
            validator.isValidOrderCount(count1);
        }).isInstanceOf(IllegalArgumentException.class);


        //2. 총주문 개수가 20 이하가 아닐 경우 예외 발생
        int [] count2 = {1,2,3,20};
        assertThatThrownBy(() -> {
            validator.isValidTotalOrderCount(count2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력받은 메뉴의 형식이 유효한지 검증")
    @Test
    void validateInputMenuFormTest() {


        //1.입력 형식에 '-'가 없을 경우 예외 발생
        assertThatThrownBy(() -> {
            validator.isValidInputForm(List.of("시저샐러드-1", "레드와인-2", "제로콜라2"));
        }).isInstanceOf(IllegalArgumentException.class);

        //2.입력 형식에 '-'뒤에 숫자가 없을 경우 예외 발생
        assertThatThrownBy(() -> {
            validator.isValidInputForm(List.of("시저샐러드-1", "레드와인-2", "제로콜라-a"));
        }).isInstanceOf(IllegalArgumentException.class);

    }
}