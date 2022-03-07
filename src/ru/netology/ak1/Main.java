package ru.netology.ak1;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println("Несовершеннолетние:");
        Long underAge = persons.stream()
                .limit(100)
                .filter(x -> x.getAge() > 0)
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(underAge);

        System.out.println("Призывники:");

        List<String> recruits = persons.stream()
                .limit(100)
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(recruits);

        System.out.println("Работоспособные:");
        System.out.println("-мужчины");
        List<String> maleWorkers = persons.stream()
                .limit(100)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 65)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(maleWorkers);

        System.out.println("-женщины");
        List<String> femaleWorkers = persons.stream()
                .limit(100)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getSex() == Sex.WOMAN)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 60)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(femaleWorkers);

    }
}
