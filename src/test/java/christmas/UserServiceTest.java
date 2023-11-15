package christmas;


import christmas.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private UserService userService = new UserService();



    @DisplayName("디데이 할인의 정상 작동 여부 검증")
    @Test
    void dDayDiscountTest(){

        //1.정상 작동여부 검증
        assertThat(userService.discountOnDday(25)).isEqualTo(3400);

        //2.1일에서 25일의 범위 안이 아니면 할인금이 0원인지 검증
        assertThat(userService.discountOnDday(30)).isEqualTo(0);

    }

    @DisplayName("평일 할인의 정상 작동 여부 검증")
    @Test
    void weekdayDiscountTest(){
        List<String> nameList = List.of("시저샐러드", "초코케이크", "아이스크림");
        int [] countList = {1,2,1};

        //1. 평일(일요일~목요일)일 경우 디저트 메뉴에 할인 적용
        assertThat(userService.discountOnDayOfWeek(18,nameList,countList))
                .isEqualTo(6069);

        //2. 평일이 아닐 경우 할인금이 0원인지 검증
        assertThat(userService.discountOnDayOfWeek(23,nameList,countList))
                .isEqualTo(0);

        //3. 25일 이후로도 적용되는지 검증
        assertThat(userService.discountOnDayOfWeek(26,nameList,countList))
                .isEqualTo(6069);
    }

    @DisplayName("주말 할인의 정상 작동 여부 검증")
    @Test
    void weekendDiscountTest(){
        List<String> nameList = List.of("시저샐러드", "티본스테이크", "초코케이크", "아이스크림");
        int [] countList = {1,3,2,1};

        //1. 주말(금,토)일 경우 메인 메뉴에 할인 적용
        assertThat(userService.discountOnWeekend(22,nameList,countList))
                .isEqualTo(6069);

        //2. 주말이 아닐 경우 할인금이 0원인지 검증
        assertThat(userService.discountOnWeekend(21,nameList,countList))
                .isEqualTo(0);

        //3. 25일 이후로도 적용되는지 검증
        assertThat(userService.discountOnWeekend(29,nameList,countList))
                .isEqualTo(6069);
    }

    @DisplayName("특별 할인의 정상 작동 여부 검증")
    @Test
    void specialDayDiscountTest(){
        //이벤트 달력에 별이 있는 날(일요일, 크리스마스)이면 할인금이 1000원 적용
        assertThat(userService.discountOnSpecialDay(25)).isEqualTo(1000);
        assertThat(userService.discountOnSpecialDay(24)).isEqualTo(1000);
    }

    @DisplayName("증정 이벤트의 정상 작동 여부 검증")
    @Test
    void souvenirEventTest(){
        //1. 주문 금액이 120000 이상인 경우 샴페인 1병 지급
        List<String> nameList = List.of("시저샐러드", "티본스테이크", "초코케이크", "아이스크림");
        int [] countList = {1,3,2,1};

        assertThat(userService.souvenirService(nameList,countList)).isEqualTo(25000);

        //2. 주문 금액이 120000 미만인 경우 샴페인을 지급하지 않음
        List<String> nameList1 = List.of("시저샐러드", "티본스테이크", "초코케이크", "아이스크림");
        int [] countList1 = {1,1,1,1};
        assertThat(userService.souvenirService(nameList1,countList1)).isEqualTo(0);

    }


}