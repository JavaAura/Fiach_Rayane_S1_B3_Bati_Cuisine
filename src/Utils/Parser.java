package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Parser {
    // Default date format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String str) {
        return parseDate(str, DATE_FORMATTER);
    }

    // Parse date with a custom format
    public static LocalDate parseDate(String str, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("\nERROR: Date parsing failed. Expected format: " + formatter.toString() + "\n");
        }
        return null; // Return null on failure to parse
    }

    public static int parseInt(String str){
        try{
            return Integer.parseInt(str);
        } catch(NumberFormatException e) {
            System.err.println("\nERROR: Integer parsing failed\n");
        }
        return -1;
    }

    public static Optional<Double> parseDouble(String str){
        try{
            return Optional.of(Double.parseDouble(str));
        } catch(NumberFormatException e) {
            System.err.println("\nERROR: Double parsing failed\n");
        }
        return Optional.empty();
    }
}
