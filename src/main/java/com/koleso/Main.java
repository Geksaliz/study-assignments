package com.koleso;

import com.koleso.java.stream.parallel.ParallelStreamCollectMapAdvanced;
import com.koleso.java.stream.parallel.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        ParallelStreamCollectMapAdvanced collector = new ParallelStreamCollectMapAdvanced();
        Map<String, Double> averageGrades = collector.collect(students);
        averageGrades.forEach((subject, avg) ->
                System.out.printf("Предмет: %s, средняя оценка: %.2f\n", subject, avg)
        );
    }
}
