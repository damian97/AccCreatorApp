package pl.acc_creator.app;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.Random;


public class Engine {

    static boolean choseSkinWindow = false;
    static boolean openFile = true;
    static boolean headless = false;



    static boolean testPhase = true;
    static boolean random = true;


    static int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static char[] lowerCase = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    static char[] upperCase = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    static char[] specialCharacters = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '.', '?'};



    static String[] firstNames = {"Adam", "Agnieszka", "Aleksandra", "Andrzej", "Aneta",
            "Barbara", "Bartosz", "Beata", "Bogdan", "Bozena",
            "Damian", "Danuta", "Dariusz", "Dawid", "Dorota",
            "Ewa", "Fabian", "Frantisek", "Grzegorz", "Halina",
            "Iga", "Iwona", "Jakub", "Jan", "Joanna",
            "Jozef", "Kamil", "Karol", "Katarzyna", "Krzysztof",
            "Krystyna", "Lech", "Ludwik", "Lukasz", "Marek",
            "Mariusz", "Marta", "Mateusz", "Michal", "Natalia"};



    static String[] secondNamesWithPl = {"Kowalski", "Malinowski", "Nowak", "Kowalczyk", "Wojciechowski",
            "Kwiatkowski", "Kaminski", "Krajewski", "Adamczyk", "Dudek",
            "Nowakowski", "Król", "Majewski", "Olszewski", "Stępień",
            "Jasiński", "Górski", "Woźniak", "Piotrowski", "Grabowski",
            "Kozłowski", "Jablonski", "Kwiecień", "Ziółkowski", "Majchrzak",
            "Głowacki", "Kucharski", "Wilk", "Jakubowski", "Kalinowski",
            "Kosowski", "Szewczyk", "Michalski", "Andrzejewski", "Kaczmarek",
            "Krupa", "Kozak", "Kania", "Mikolajczyk", "Szczepański"};


    static String[] secondNames = {"Nowak", "Kowalski", "Wisniewski", "Dabrowski", "Lewandowski", "Wojcik", "Kaminski", "Kowalczyk", "Zielinski", "Szymanski", "Wozniak", "Kaczmarek", "Krol", "Czarnecki", "Mazur", "Kubiak", "Wieczorek", "Piotrowski", "Pawlowski", "Kwiatkowski", "Krawczyk", "Nowakowski", "Dudek", "Adamczyk", "Sikora", "Tomaszewski", "Pietrzak", "Zajac", "Pawlik", "Michalski", "Szewczyk", "Wrobel", "Wasilewski", "Mroz", "Jankowski", "Baran", "Sadowski", "Zawadzki", "Olszewski", "Chmielewski"};



    static String firstName;
    static String secondName;
    static int gender;
    static String email;
    static String password;

    static char[] rKey;
    static String rKeyString;
    static String[] userDataTable;
    static String documentsPath = System.getProperty("user.home") + "\\Documents";
    static String fileName = "Login_Data_Tibia_Interia_by_Sryncu";


    static WebDriver driver;
    static ChromeOptions options = new ChromeOptions();

    public Engine() {
        WebDriverManager.chromedriver().setup();
        options.setHeadless(false);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

    }

    static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    static void openFileAfterCreate() {
        if (openFile) {
            try {
                Runtime.getRuntime().exec("explorer C:\\Users\\srync\\Documents\\Login_Data_Tibia_Interia_by_Sryncu.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    static void drawPerson() {


        if (Engine.random) {

            int randomFirstName = (int) (Math.random() * firstNames.length);
            int randomSecondName = (int) (Math.random() * firstNames.length);
            String randomDay = String.valueOf((int) (Math.random() * 31 + 1));        //zakres 1-31
            int randomMonth = (int) (Math.random() * 12 + 1);               //zakres 1-12
            Random rand = new Random();
            int lowerBound = 1965;
            int upperBound = 2007;
            int randomNum = rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;   //zakres 1965-2007
            String randomYear = String.valueOf(randomNum);


            firstName = firstNames[randomFirstName];
            secondName = secondNames[randomSecondName];
            Birthdate.day = randomDay;
            Birthdate.month = randomMonth;
            Birthdate.year = randomYear;
            gender = choseGender(firstName);
            email = blendMail();
            password = blendPassword();
        }

        makeDataUserTable();

    }


    static void showPersonInfo() {
        System.out.println(firstName);
        System.out.println(secondName);
        System.out.println(Birthdate.day);
        System.out.println(Birthdate.month);
        System.out.println(Birthdate.year);
        System.out.println(gender);
        System.out.println(email);
        System.out.println(password);
    }

    static void makeDataUserTable(){
        userDataTable = new String[9];

        userDataTable[0] = firstName;
        userDataTable[1] = secondName;
        userDataTable[2] = Birthdate.day;
        userDataTable[3] = String.valueOf(Birthdate.month);
        userDataTable[4] = Birthdate.year;
        userDataTable[5] = String.valueOf(gender);
        userDataTable[6] = email;
        userDataTable[7] = password;
        userDataTable[8] = password;
    }


    public static int choseGender(String fName) {

        int lenght = fName.length();
        if (fName.charAt(lenght-1) == 'a') {
            return 1;
        } else {
            return 0;
        }

    }


    static String blendMail() {

        String d = Birthdate.day;
        String m = String.valueOf(Birthdate.month);
        String y = Birthdate.year;
        String shortY = y.charAt(2) + String.valueOf(y.charAt(3));



        int random = (int) (Math.random() * 4);     // 4 mozliwosci od 0 do 3

        switch (random) {
            case 0 :
                return firstName + d + m + shortY; // zwraca imie i nazwisko plus data urodzenia (skrocony rok) jako email
            case 1 :
                return secondName + d + m; // zwraca imie i nazwisko plus data urodzenia (pelna) jako email
            case 2 :
                return secondName + y; // zwraca nazwisko i imie  plus data urodzenia (tylko rok) jako email
            case 3 :
                return firstName + y + d; // zwraca imie + rok + nazwisko + dzien jako email
            default:
                return firstName + m + d + "930"; // default
        }
    }



    static String blendPassword() {

        String pass = firstName + randChar(numbers) + randChar(numbers) + randChar(lowerCase) + randChar(upperCase) + randChar(specialCharacters) + randChar(specialCharacters) + randChar(upperCase);

        while (!testPass(pass)) {
            pass += randChar(lowerCase);
        }

        return pass;
    }

    static boolean testPass(String password) {

        if (password.length() >= 12) {
            return true;
        } else {
            return false;
        }

    }

    static int randChar(int[] table) {
        int number = (int) (Math.random() * table.length);

        return table[number];
    }

    static char randChar(char[] table) {
        int number = (int) (Math.random() * table.length);
        return table[number];
    }


    static char[] getKeyFromText(String text) {
        int indexDash = text.indexOf('-');
        int startIndex = indexDash-5;
        int stopIndex = indexDash+18;
        rKeyString = text.substring(startIndex, stopIndex);
        System.out.println(rKeyString);

        String klucz = "";
        for (int i = 0; i < rKeyString.length(); i++) {
            if (rKeyString.charAt(i) != '-') {
                klucz = klucz + rKeyString.charAt(i);
            }
        }
        System.out.println(klucz);

        return rKey = klucz.toCharArray();
    }


    static String[] getKeyFromTextToString(String text) {
        int indexDash = text.indexOf('-');
        int startIndex = indexDash-5;
        int stopIndex = indexDash+18;
        rKeyString = text.substring(startIndex, stopIndex);
        String[] table = rKeyString.split("-");
        return table;
    }



    static String getLinkFromText(String text) {
        String[] tablica = text.split("\n");
        String confLink = tablica[7];
        return confLink;
    }


}


class Birthdate {

    static String day;
    static int month;
    static String year;


}