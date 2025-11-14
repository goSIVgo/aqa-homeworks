package com.gosivgo.lesson6.university;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StudentManagement {

    //Отчисление студентов со средним баллом < 3
    public static void expelStudents(Set<Student> students) {
        students.removeIf(student -> student.getAverageMark() < 3.0);
    }

    //Перевод студентов на следующий курс если средний балл >= 3
    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageMark() >= 3.0) {
                student.transferToNextCourse();
            }
        }
    }

    // Вывод в консоль имён студентов указанного курса
    public static void printStudent(Set<Student> students, int course) {
        System.out.printf("%nСтуденты %d курса:%n", course);
        boolean found = false;

        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println("* " + student.getName() + " Группа: " + student.getGroup());
                found = true;
            }
        }
        if (!found) {
            System.out.printf("На %d курсе не найдено ни одного студента.%n", course);
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        Student student1 = new Student("Добров Василий Васильевич", "МИР-1", 1);
        student1.giveMark("Ядерная физика", 5);
        student1.giveMark("Социология", 5);
        student1.giveMark("Астробиология", 4);

        Student student2 = new Student("Мирная Ирина Викторовна", "МИР-2", 1);
        student2.giveMark("Ядерная физика", 3);
        student2.giveMark("Социология", 5);
        student2.giveMark("Астробиология", 4);

        Student student3 = new Student("Любимов Виктор Викторович", "МИР-3", 2);
        student3.giveMark("Ядерная физика", 5);
        student3.giveMark("Социология", 5);
        student3.giveMark("Астробиология", 5);

        Student student4 = new Student("Врагова Ада Адовна", "МИР-0", 3);
        student4.giveMark("Ядерная физика", 3);
        student4.giveMark("Социология", 2);
        student4.giveMark("Астробиология", 2);

        // Добавление студентов
        students.addAll(Arrays.asList(student1, student2, student3, student4));


        System.out.println("_____________ CТУДЕНТЫ _____________");
        for (Student student : students) {
            System.out.println(student);
        }

        //Отчисление студентов за неуспеваемость
        expelStudents(students);

        //Перевод студентов на следующий курс
        promoteStudents(students);

        //Вывод списка студентов без отчисленных
        System.out.println("_____________ CТУДЕНТЫ СДАВШИЕ СЕССИЮ  _____________");
        for (Student student : students) {
            System.out.println(student);
        }
        printStudent(students, 1);
        printStudent(students, 2);
        printStudent(students, 3);
    }
}
