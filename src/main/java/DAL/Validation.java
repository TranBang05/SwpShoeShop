package DAL;

public class Validation {
    public static String checkEmpty(String s) {
        if (s.isEmpty()) {
            return "* Please fill out this field!";
        }

        return "";
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String checkNum(String s) {
        if (s.isEmpty()) {
            return "* Please enter a number!";
        }

        if (!isNumeric(s) || s.contains(" ")) {
            return "* Input must be a number!";
        }

        return "";
    }
}
