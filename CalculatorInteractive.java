import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class CalculatorInteractive {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в калькулятор!");

        // Запрос первого числа
        System.out.print("Введите число: ");
        double num1 = scanner.nextDouble();

        // Запрос операции
        System.out.print("Введите операцию (+, -, *, /, ^, sqrt, square): ");
        String operation = scanner.next();

        // Получаем операцию из калькулятора
        DoubleBinaryOperator binaryOperator = calc.getBinaryOperation(operation);
        DoubleUnaryOperator unaryOperator = calc.getUnaryOperation(operation);
        double result;
        if (binaryOperator != null) {
            // Если операция бинарная, запросите второе число
            System.out.print("Введите второе число: ");
            double num2 = scanner.nextDouble();
            result = binaryOperator.applyAsDouble(num1, num2);
        } else if (unaryOperator != null) {
            // Если операция унарная, выполните операцию с одним числом
            result = unaryOperator.applyAsDouble(num1);
        } else {
            System.out.println("Неверная операция!");
            return;
        }

        System.out.println("Результат: " + result);

        scanner.close();
    }
}

class Calculator {
    private Map<String, DoubleBinaryOperator> binaryOperations = new HashMap<>();
    private Map<String, DoubleUnaryOperator> unaryOperations = new HashMap<>();

    public Calculator() {
        fillBinaryOperations();
        fillUnaryOperations();
    }

    private void fillBinaryOperations() {
        binaryOperations.put("+", (a, b) -> a + b);
        binaryOperations.put("-", (a, b) -> a - b);
        binaryOperations.put("*", (a, b) -> a * b);
        binaryOperations.put("/", (a, b) -> a / b);
        binaryOperations.put("^", Math::pow); // Возведение в степень
    }

    private void fillUnaryOperations() {
        unaryOperations.put("sqrt", Math::sqrt); // Квадратный корень
        unaryOperations.put("square", a -> a * a); // Квадрат
    }

    public DoubleBinaryOperator getBinaryOperation(String op) {
        return binaryOperations.get(op);
    }

    public DoubleUnaryOperator getUnaryOperation(String op) {
        return unaryOperations.get(op);
    }
}