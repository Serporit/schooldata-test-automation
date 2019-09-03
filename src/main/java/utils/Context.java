package utils;

import org.apache.commons.lang.RandomStringUtils;

public class Context {
    private static String listName;

    public static String getListName() {
        if (listName == null) {
            listName = generateListName();
        }
        return listName;
    }

    public static String generateListName() {
        return "AutoTestList" + RandomStringUtils.randomNumeric(5);
    }

    public static void clear() {
        listName = null;
    }
}
