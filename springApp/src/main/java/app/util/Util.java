package app.util;

import app.constant.Constant;

public class Util {

    public static String returnMapValueObjectAsString(Object value) {
        return value == null ? Constant.EMPTY_STRING : value.toString();
    }
}
