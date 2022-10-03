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
        SimpleDateFormat formatterSlash = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        for (int i = 0, j = 1; i < createCount; i++, j = j + 3) {
            if (array[j + 1].equals("м")) {
                allPeople.add(Person.createMale(array[j], formatterSlash.parse(array[j + 2])));
                System.out.println(i);
            }
            else {
                allPeople.add(Person.createFemale(array[j], formatterSlash.parse(array[j + 2])));
                System.out.println(i);
            }
        }
    }
    public static void updatePeoples(String[] array) throws ParseException {
        int updateCount = (array.length - 1) / 3;
        SimpleDateFormat formatterSlash = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Person person = null;
        for (int i = 0, j = 1; i < updateCount; i++, j = j + 4) {
            person = allPeople.get(Integer.parseInt(array[j]));
            person.setName(array[j + 1]);
            person.setSex(array[j + 2].equals("м") ? Sex.MALE : Sex.FEMALE);
            person.setBirthDate(formatterSlash.parse(array[j + 3]));
        }
    }
    public static void deletePeoples(String[] array) {
        int deleteCount = array.length - 1;
        for (int i = 0, j = 1; i < deleteCount; i++, j = j + 2) {
            allPeople.remove(Integer.parseInt(array[j]));
        }
    }

    public static void readPeoples(String[] array) {
        int readCount = array.length - 1;
        SimpleDateFormat formatterHyph = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Person person = null;
        for (int i = 0, j = 1; i < readCount; i++, j++) {
            person = allPeople.get(Integer.parseInt(array[j]));
            String sex = person.getSex().equals(Sex.MALE) ? "м" : "ж";
            System.out.println(person.getName() + " " + sex + " " + formatterHyph.format(person.getBirthDate()));
        }
    }
}
