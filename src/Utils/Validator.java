package Utils;

import Utils.Enums.InputType;

import java.util.List;
import java.util.Scanner;

public class Validator {


    public static boolean isEmpty(String str) {
        return str.isEmpty();
    }

    public static boolean isInteger(String str) {
        return str.matches("^[-+]?\\d+$");
    }

    public static boolean isDouble(String str) {
        return str.matches("^[+-]?([0-9]*[.])?[0-9]+$");
    }

    public static boolean isDate(String str) {
        return str.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean isWithinRange(String option, int min, int max) {
        return validateInteger(option) >= min && validateInteger(option) <= max;
    }

    public static int validateInteger(String str) {
        return isInteger(str) ? Integer.parseInt(str) : -1;
    }

    public static boolean isValidInput(String input, InputType type, int... range) {
        boolean isValidInput = true;

        switch (type) {
            case DATE:
                isValidInput = isDate(input);
                break;
            case INTEGER:
                isValidInput = isInteger(input);
                break;
            case DOUBLE:
                isValidInput = isDouble(input);
                break;
            case OPTION:
                isValidInput = isWithinRange(input, range[0], range[1]) && isInteger(input);
                break;
            default:
                isValidInput = !isEmpty(input);
        }

        return isValidInput;
    }

    public static String validateInput(String prompt, InputType type, int... range) {
        String input;
        do {
            System.out.print(prompt);
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

            if (!isValidInput(input, type, range)) {
                System.out.println("\nERROR: Invalid input. Please try again.\n");
            }

        } while (!isValidInput(input, type, range));

        return input;
    }

    public static boolean listIsEmpty(List<?> list) {
        if (list.isEmpty()) {
            System.out.println("\nERROR: No data available!\n");
            return true;
        }
        return false;
    }
}
