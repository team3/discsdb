package ua.edu.sumdu.lab3.controller;

import ua.edu.sumdu.lab3.exceptions.DataValidException;

public class DataValidator {
    
    public static int NUMERIC_PARAM = 0;
    public static int ALPHA_PARAM = 1;
    
    public static boolean checkParam(int type, String paramValue) {
        String regexp;
        if((paramValue == null) || 
                " ".equals(paramValue) ||
                "".equals(paramValue))
        {
            return false;
        }
        if (type == NUMERIC_PARAM) {
            regexp = "[0-9]+";
           
        } else  if(type == ALPHA_PARAM) {
            regexp = "[a-zA-Z]+";
        } else {
            regexp = ".+";
        }
        return paramValue.matches(regexp);
    }
    
    private static void isValidText(String name,int size) throws DataValidException {
        if(name == null || "".equals(name) || " ".equals(name)) {
            throw new DataValidException("Empty string is not enabled here!");
        }
        if(name.length() >= size) {
            throw new DataValidException("Too long value are given. Max length is 200 symbols.");
        }
    }
    
    public static void isValidName(String name) throws DataValidException {
        isValidText(name,200);
    }
    
    public static void isValidGenre(String name) throws DataValidException {
        isValidText(name,500);
    }
    
    public static void isValidLink(String link) throws DataValidException {
        isValidText(link,200);
        if(!link.matches("^(http|https|ftp)://[a-zA-Z0-9.]+/+([a-zA-Z0-9.?&_=-]/?)+$")) {
            throw new DataValidException("Bad url. Valid direct link needed.");
        }
    }
    
    public static void isValidText(String text) throws DataValidException {
        isValidText(text,2000);
    }
    
    public static void isValidType(String type) throws DataValidException {
        isValidText(type,100);
    }
    
    public static void isValidDate(String date) throws DataValidException {
        isValidText(date,9);
        if(!date.matches("^((0[1-9])|([1-2][0-9])|30|31)[.]((0[1-9])|(1[0-2]))[.][0-9]{2}$")) {
            throw new DataValidException("Bad date format. Need dd.MM.YY using only numbers.");
        }
    }
}
