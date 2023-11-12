package christmas.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public int parseInputStringNumber(String input) {
        return Integer.parseInt(input);
    }

    public DayOfWeek parseNumberToDayOfWeek(int day){
        LocalDate date = LocalDate.of(2023, 12, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek;
    }

    public List<String> stringToArray(String string) {
        return Arrays.stream(string.split(",")).map(String :: trim).collect(Collectors.toList());
    }

    //추후에 다른 클래스로 리팩토링
    public List<String> inputMenuToNameList(List<String> list){
        List<String> menuNameList = new ArrayList<>();
        for(String string : list){
            menuNameList.add(string.split("-")[0]);
        }
        return menuNameList;
    }

    //추후에 다른 클래스로 리팩토링
    public List<String> inputMenuToCountList(List<String> list) {
        List<String> menuCountList = new ArrayList<>();
        for (String string : list) {
            menuCountList.add(string.split("-")[1]);
        }
        return menuCountList;
    }
    public int [] stringToIntArray(List<String> list) {
        return list.stream().mapToInt(Integer :: parseInt).toArray();
    }


}
