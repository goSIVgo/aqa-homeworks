package com.gosivgo.lesson6.university;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private String group;
    private int course;
    private Map<String, Integer> marks;

    public Student(String name, String group, int course) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.marks = new HashMap<>();
    }

    public void giveMark(String subject, int mark) {
        if (mark < 1 || mark > 5) {
            throw new IllegalArgumentException("Оценка по пятибалльной системе! " +
                    "Допустимые оценки: от 1 до 5  включительно");
        }
        marks.put(subject, mark);
    }

    public double getAverageMark() {
        if (marks.isEmpty()) return 0.0;

        int sum = 0;
        int count = 0;

        for (int mark : marks.values()) {
            sum += mark;
            count++;
        }
        return (double) sum / count;
    }

    public void transferToNextCourse() {
        this.course++;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public Map<String, Integer> getMarks() {
        return new HashMap<>(marks);
    }

    @Override
    public String toString() {
        return String.format("Студент: %s, Группа: %s, Курс: %d, Средний балл: %.1f", name, group, course, getAverageMark());

    }

}
