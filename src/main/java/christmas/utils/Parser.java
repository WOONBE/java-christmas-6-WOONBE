package christmas.utils;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
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


    public int [] stringListToIntArray(List<String> list) {
        return list.stream().mapToInt(Integer :: parseInt).toArray();
    }

    public String inputMoneyToDecimalFormat(int amount){
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(amount);
    }
}
