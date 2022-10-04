package com.javarush.task.task17.task1711;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));
        allPeople.add(Person.createMale("Петров Петр", new Date()));
    }

    public static void main(String[] args) throws ParseException {
        switch (args[0]) {
            case "-c":
                createPeoples(args);
                break;
            case "-u":
                updatePeoples(args);
                break;
            case "-d":
                deletePeoples(args);
                break;
            case "-i":
                readPeoples(args);
                break;
        }
    }

    public static void createPeoples(String[] array) throws ParseException {
        int createCount = (array.length - 1) / 3;
        for (int i = 0, j = 1; i < createCount; i++, j = j + 3) {
            createPerson(array[j], array[j + 1], array[j + 2]);
        }
    }

    public static void createPerson(String name, String sex, String birthDate) throws ParseException {
        SimpleDateFormat formatterSlash = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Person person = null;
        if (sex.equals("м")) {
            person = Person.createMale(name, formatterSlash.parse(birthDate));
        }
        else {
            person = Person.createFemale(name, formatterSlash.parse(birthDate));
        }
        allPeople.add(person);
        System.out.println(allPeople.indexOf(person));
    }


    public static void updatePeoples(String[] array) throws ParseException {
        int updateCount = (array.length - 1) / 4;
        for (int i = 0, j = 1; i < updateCount; i++, j = j + 4) {
            updatePerson(Integer.parseInt(array[j]), array[j + 1], array[j + 2], array[j + 3]);
        }
    }

    public static void updatePerson(int id, String name, String sex, String birthDate) throws ParseException {
        SimpleDateFormat formatterSlash = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Person person = null;
        person = allPeople.get(id);
        person.setName(name);
        person.setSex(sex.equals("м") ? Sex.MALE : Sex.FEMALE);
        person.setBirthDate(formatterSlash.parse(birthDate));
    }

    public static void deletePeoples(String[] array) {
        int deleteCount = array.length - 1;
        for (int i = 0, j = 1; i < deleteCount; i++, j++) {
            deletePerson(Integer.parseInt(array[j]));
        }
    }
    public static void deletePerson(int id) {
        //по условию нужно логическое удаление, при обычном необходимо смещение индекса
        Person person = null;
        person = allPeople.get(id);
        person.setName(null);
        person.setSex(null);
        person.setBirthDate(null);
    }

    public static void readPeoples(String[] array) {
        int readCount = array.length - 1;
        for (int i = 0, j = 1; i < readCount; i++, j++) {
            readPerson(Integer.parseInt(array[j]));
        }
    }

    public static void readPerson(int id) {
        Person person = null;
        SimpleDateFormat formatterHyph = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        person = allPeople.get(id);
        String sex = person.getSex().equals(Sex.MALE) ? "м" : "ж";
        System.out.println(person.getName() + " " + sex + " " + formatterHyph.format(person.getBirthDate()));
    }
}
