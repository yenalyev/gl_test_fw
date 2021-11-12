package utilities.helper;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {
    public static List<String> stringIntoListBySeparator(String input, String separator){
        List<String> list = new ArrayList<>();
        if (input==null || separator==null){
            return new ArrayList<>();
        }
        String[] split = input.split(separator);
        for (String s: split){
            list.add(s.toLowerCase().trim());
        }
        return list;
    }
}
