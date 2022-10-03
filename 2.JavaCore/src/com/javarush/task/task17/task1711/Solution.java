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
        SimpleDateFormat formatterSlash = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat formatterHyph = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Person person = null;
        int createCount = (args.length - 1) / 3;
        int updateCount = (args.length - 1) / 3;
        int readCount = args.length - 1;

        switch (args[0]) {
            case "-c":
                for (int i = 0, j = 1; i < createCount; i++, j = j + 3) {
                    if (args[j+1].equals("м")) {
                        allPeople.add(Person.createMale(args[j], formatterSlash.parse(args[j + 2])));
                        System.out.println(i);
                    }
                    else {
                        allPeople.add(Person.createFemale(args[j], formatterSlash.parse(args[j + 2])));
                        System.out.println(i);
                    }
                }
                break;
            case "-u":
                for (int i = 0, j = 1; i < updateCount; i++, j = j + 4) {
                    person = allPeople.get(Integer.parseInt(args[j]));
                    person.setName(args[j + 1]);
                    person.setSex(args[j + 2].equals("м") ? Sex.MALE : Sex.FEMALE);
                    person.setBirthDate(formatterSlash.parse(args[j + 3]));
                }
                break;
            case "-d":
                for (int i = 0, j = 1; i < updateCount; i++, j = j + 2) {
                    allPeople.remove(Integer.parseInt(args[j]));
                }
                break;
            case "-i":
                for (int i = 0, j = 1; i < readCount; i++, j++) {
                    person = allPeople.get(Integer.parseInt(args[j]));
                    String sex = person.getSex().equals(Sex.MALE) ? "м" : "ж";
                    System.out.println(person.getName() + " " + sex + " " + formatterHyph.format(person.getBirthDate()));
                }
                break;
        }
    }
}
