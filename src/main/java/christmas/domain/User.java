package christmas.domain;

import java.util.List;

public class User {

    int day;
    List<String> nameList;
    int [] countList;


    public User(int day, List<String> nameList, int[] countList) {
        this.day = day;
        this.nameList = nameList;
        this.countList = countList;
    }

    public int getDay() {
        return day;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public int[] getCountList() {
        return countList;
    }
}
