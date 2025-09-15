import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите первое число: ");
        double firstOperand = readOperand();

        System.out.print("Введите операцию (+, -, *, /): ");
        char operator = readOperation();

        System.out.print("Введите второе число: ");
        double secondOperand = readOperand();

        try {
            double result = calculate(firstOperand, secondOperand, operator);
            System.out.println("Результат: " + result);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static double readOperand() {
        while (true) {
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Ошибка. Попробуйте снова.");
                scanner.next();
            }
        }
    }

    public static char readOperation() {
        while (true) {
            String input = scanner.next();
            if (input.length() == 1) {
                char op = input.charAt(0);
                if (op == '+' || op == '-' || op == '*' || op == '/') {
                    return op;
                }
            }
            System.out.println("Ошибка: неподдерживаемая операция. Доступны: +, -, *, /");
            System.out.print("Введите операцию (+, -, *, /): ");
        }
    }

    public static double calculate(double a, double b, char operation) {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0.0) {
                    throw new ArithmeticException("Деление на ноль невозможно!");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operation);
        }
    }
}
