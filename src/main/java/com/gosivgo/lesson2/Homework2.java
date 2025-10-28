import java.util.Arrays;
import java.util.Random;

public class Homework2 {

    // 1. Печать трех слов
    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }


    // 2. Проверка суммы
    public static void checkSumSign() {
        Random random = new Random();
        int a = random.nextInt(201) - 100;
        int b = random.nextInt(201) - 100;

        System.out.println("a = " + a + ", b = " + b + ", сумма = " + (a + b));

        if ((a + b) >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }


    // 3. Определение цвета
    public static void printColor() {
        int value = 101;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }


    // 4. Сравнение чисел
    public static void compareNumbers() {
        int a = 8;
        int b = 8;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }


    // 5. Проверка суммы в диапазоне 10-20
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }


    // 6. Проверка знака числа
    public static void checkNumberSign(int number) {
        System.out.println(number >= 0 ? "Положительное число" : "Отрицательное число");
    }


    // 7. Проверка отрицательное ли число
    public static boolean isNegative(int number) {
        return number < 0;
    }


    // 8. Печать строки N раз
    public static void printStringMultipleTimes(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }


    // 9. Проверка високосного года
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }


    // 10. Инвертирование массива 0/1
    public static int[] invertArray(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length; i++) {
            result[i] = result[i] == 0 ? 1 : 0;
        }
        return result;
    }


    // 11. Заполнение массива 1..100
    public static int[] fillArray() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        return array;
    }


    // 12. Умножение чисел < 6 на 2
    public static int[] multiplyLessThanSix(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        for (int i = 0; i < result.length; i++) {
            if (result[i] < 6) {
                result[i] *= 2;
            }
        }
        return result;
    }


    // 13. Заполнение диагоналей единицами
    public static int[][] fillDiagonals(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - 1 - i] = 1;
        }
        return matrix;
    }


    // 14. Создание массива с одинаковыми значениями
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }


    public static void main(String[] args) {
        System.out.println("\n**********  Homework2  *********");

        System.out.println("\n1. Печать трех слов:");
        printThreeWords();

        System.out.println("\n2. Проверка суммы:");
        checkSumSign();

        System.out.println("\n3. Определение цвета:");
        printColor();

        System.out.println("\n4. Сравнение чисел:");
        compareNumbers();

        System.out.println("\n5. Проверка суммы в диапазоне [10-20]:");
        System.out.println("5 + 10 в диапазоне: " + isSumInRange(5, 10));

        System.out.println("\n6. Проверка знака числа:");
        checkNumberSign(-5);

        System.out.println("\n7. Проверка отрицательное ли число:");
        System.out.println("0 отрицательное: " + isNegative(0));

        System.out.println("\n8. Печать строки 5 раз:");
        printStringMultipleTimes("Hello, ASTON", 5);

        System.out.println("\n9. Проверка високосного года:");
        System.out.println("2000 високосный: " + isLeapYear(2000));

        System.out.println("\n10. Инвертирование массива 0/1:");
        int[] binaryArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Инвертированный: " + Arrays.toString(invertArray(binaryArray)));

        System.out.println("\n11. Заполнение массива 1..100:");
        System.out.println("Массив: " + Arrays.toString(fillArray()));

        System.out.println("\n12. Умножение чисел < 6 на 2:");
        int[] numbers = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("После умножения: " + Arrays.toString(multiplyLessThanSix(numbers)));

        System.out.println("\n13. Заполнение диагоналей единицами:");
        int[][] matrix = fillDiagonals(5);
        System.out.println("Матрица 5x5:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\n14. Создание массива с одинаковыми значениями:");
        System.out.println("Массив: " + Arrays.toString(createArray(6, 789)));


    }
}
