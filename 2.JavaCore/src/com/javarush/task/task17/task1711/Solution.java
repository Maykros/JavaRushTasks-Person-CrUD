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
        int operationCUCount = (args.length - 1) / 3;
        int operationRDCount = args.length - 1;

        switch (args[0]) {
            case "-c":
                for (int i = 0, j = 1; i < operationCUCount; i++, j = j + 3) {
                    if (args[j+1].equals("м")) {
                        allPeople.add(Person.createMale(args[j], formatterSlash.parse(args[j+2])));
                        System.out.println(i);
                    }
                    else {
                        allPeople.add(Person.createFemale(args[j], formatterSlash.parse(args[j + 2])));
                        System.out.println(i);
                    }
                }
                break;
            case "-u":

                break;
            case "-d":

                break;
            case "-i":

                break;
        }
    }
}
