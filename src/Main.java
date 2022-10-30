import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputExpression = sc.nextLine();

        System.out.println(calc(inputExpression));
    }
    public static String calc(String input) {
        String result;
        int operand1, operand2;
        int value;
        boolean isRoman = false;
        Converter oper;
        String[] elementsOfExpression = input.split(" ");

        if (elementsOfExpression.length != 3) return "Входные значения не удовлетворяют условию";

        CheckForRomanDigit check1 = new CheckForRomanDigit(elementsOfExpression[0]);
        CheckForRomanDigit check2 = new CheckForRomanDigit(elementsOfExpression[2]);

        if (check1.isRoman() && check2.isRoman()) {
            oper = new Converter(elementsOfExpression[0]);
            operand1 = oper.getArabianDigit();
            oper = new Converter(elementsOfExpression[2]);
            operand2 = oper.getArabianDigit();
            if (operand1>0 && operand2>0 && operand1<11 && operand2<11) isRoman = true;
            else return "Входные значения не удовлетворяют условию";
        }
        else if (elementsOfExpression[0].matches("[1-9]||10") &&
                elementsOfExpression[2].matches("[1-9]||10")) {
            operand1 = Integer.parseInt(elementsOfExpression[0]);
            operand2 = Integer.parseInt(elementsOfExpression[2]);
        }
        else return "Входные значения не удовлетворяют условию";

        value = calculate(operand1, operand2, elementsOfExpression[1]);

        if (isRoman) {
            if (value > 0) {
                oper = new Converter(String.valueOf(value));
                result = oper.getRomanDigit();
            }
            else return "Входные значения не удовлетворяют условию";
        }
        else result = String.valueOf(value);

        return result;
    }

    static class RomanDigit {
        String[] romanDigits = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
                "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
                "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
                "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
                "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII",
                "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
                "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI",
                "XCVII", "XCVIII", "XCIX", "C"
        };
        String romanDigit;
        RomanDigit(String value){
            romanDigit = value;
        }
    }

    static class CheckForRomanDigit extends RomanDigit {
        CheckForRomanDigit(String value) {
            super(value);
        }
        boolean isRoman() {
            for (String digit: romanDigits) {
                if (digit.equals(romanDigit)) return true;
            }
            return false;
        }
    }

    static class Converter extends RomanDigit {
        Converter(String value) {
            super(value);
        }
        int getArabianDigit() {
            int index = 0;
            for (int i=0; i<=romanDigits.length; i++) {
                if (romanDigit.equals(romanDigits[i])) {
                    index = i;
                    break;
                }
            }
            return index;
        }
        String getRomanDigit() {
            String totalValue = null;
            for (int i=0; i<=romanDigits.length; i++) {
                if (Integer.parseInt(romanDigit) == i) {
                    totalValue = romanDigits[i];
                    break;
                }
            }
            return totalValue;
        }
    }

    static int calculate(int operand1, int operand2, String operator) {
        int value = switch (operator) {
            case ("+") -> operand1 + operand2;
            case ("-") -> operand1 - operand2;
            case ("*") -> operand1 * operand2;
            case ("/") -> operand1 / operand2;
            default -> 0;
        };
        return value;
    }
}