import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Person implements Comparable<Person> {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        if (this.age.equals(o.age)) {
            return o.name.compareTo(this.name);
        } else {
            return this.age.compareTo(o.age);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "Name='" + name + '\'' +
                ", Age=" + age +
                '}';
    }
}

public class main {
    public static void main(String[] args) {

        // 배열 초기화
        Person[] personArray = {
                new Person("다", 20),
                new Person("나", 40),
                new Person("가", 20),
                new Person("라", 5)
        };

        System.out.println("[정렬 전]");
        for (Person person : personArray) {
            System.out.println(person.toString());
        }

        Arrays.sort(personArray);
        System.out.println("\n[정렬 후]");
        for (Person person : personArray) {
            System.out.println(person.toString());
        }

        System.out.println("\n" + Arrays.toString(personArray));
    }
}