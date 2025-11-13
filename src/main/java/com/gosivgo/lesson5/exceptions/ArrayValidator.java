package com.gosivgo.lesson5.exceptions;

public class ArrayValidator {
    public int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Массив должен содержать 4 строки, содержит: " + array.length);
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Строка " + i + "должна содержать 4 столбца, содержит: " + array[i].length);
            }
        }
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Данные неверны " +
                            "в ячейке [" + i + "][" + j + "]: '" + array[i][j] + "'", i, j);
                }
            }
        }
        return sum;

    }

    public void demoArrayIndexOutOfBounds() {
        System.out.println("\n_________________ Генерация и обработка ArrayIndexOutOfBoundsException _____________");

        // Создаём пример любого двумерного массива
        int[][] exampleArray = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        try {

            System.out.print("exampleArray[3][0] = ");
            System.out.println(exampleArray[3][0]); // Упадет здесь

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано ArrayIndexOutOfBoundsException!");
            System.out.println("Сообщение: " + e.getMessage());
            System.out.println("Размер массива: " + exampleArray.length + " строк, " + exampleArray[0].length + " столбцов");
            System.out.println("Максимальный допустимый индекс строки: " + (exampleArray.length - 1));
            System.out.println("Максимальный допустимый индекс столбца: " + (exampleArray[0].length - 1));
        }
    }

         // Метод для обработки массива с единой обработкой исключений

        private static void processArray(ArrayValidator validator, String[][] array, String description) {
            try {
                int result = validator.sumArray(array);
                System.out.println(description + " - Сумма элементов: " + result);
            } catch (MyArraySizeException e) {
                System.out.println(description + ": "  + e.getMessage());
            } catch (MyArrayDataException e) {
                System.out.println(description + ": "  + e.getMessage());
                System.out.println("Позиция ошибки: строка " + e.getRow() + ", столбец " + e.getCol());
            }
        }

        public static void main(String[] args) {
            System.out.println("________ ДЕМОНСТРАЦИЯ РАБОТЫ С ИСКЛЮЧЕНИЯМИ МАССИВОВ ________\n");

            // Создаем экземпляр валидатора
            ArrayValidator validator = new ArrayValidator();

            // Создаем все тестовые массивы
            String[][] correctArray = {
                    {"1", "2", "3", "4"},
                    {"5", "6", "7", "8"},
                    {"9", "10", "11", "12"},
                    {"13", "14", "15", "16"}
            };

            String[][] invalidDataArray = {
                    {"1", "2", "3", "4"},
                    {"5", "6", "7", "8"},
                    {"9", "10", "ERROR", "12"}, // Ошибка здесь!
                    {"13", "14", "15", "16"}
            };

            String[][] wrongSizeArray = {
                    {"1", "2", "3"},
                    {"4", "5", "6"},
                    {"7", "8", "9"}
            };

            // Тестируем все массивы
            System.out.println("ТЕСТ 1: Корректный массив 4x4");
            processArray(validator, correctArray, "Корректный массив");

            System.out.println("\nТЕСТ 2: Массив с нечисловыми данными");
            processArray(validator, invalidDataArray, "Ошибка данных");

            System.out.println("\nТЕСТ 3: Массив неверного размера");
            processArray(validator, wrongSizeArray, "Ошибка размера");

            // Демонстрация ArrayIndexOutOfBoundsException
            validator.demoArrayIndexOutOfBounds();

        }
    }
