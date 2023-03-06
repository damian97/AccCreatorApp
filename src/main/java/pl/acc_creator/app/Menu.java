package pl.acc_creator.app;

import java.util.Scanner;

public class Menu {

    int language;
    Scanner sc;
    int menuSelect;
    PagesActions pa = new PagesActions();


    String[] plMenu = {
            "[0] Stworz konto na losowych danych",
            "[1] Stworz konto na podanych danych",
            "[2] Zmien lokalizacje do folderu do pliku z danymi",
            "[3] Stworz 20 postaci na danym acc",
            "[4] Koniec"
    };

    String[] engMenu = {
            "[0] Create an account with random data",
            "[1] Create an account with the given data",
            "[2] Change the location to the data file folder",
            "[3] Stworz 20 postaci na danym acc",
            "[4] End"
    };


    public void makeMenu() {

        sc = new Scanner(System.in);

        boolean menu = true;

        System.out.println("Hello. Please, select language. [English/Polish] [eng/pl] [0/1]");

        String language = sc.nextLine().toLowerCase();

        while (menu) {

            if (language.equals("english") || language.equals("eng") || language.equals("0")) {
                this.language = 0;
                break;
            } else if (language.equals("polish") || language.equals("pl") || language.equals("1")) {
                this.language = 1;
                break;
            } else {
                System.err.println("Please enter the correct data. [english/polish] [0/1] [eng/pl]");
                language = sc.nextLine().toLowerCase();
            }

        }

        languageMenu(this.language);

    }

    public void languageMenu(int language) {     // 0 = eng    1 = pl
        this.language = language;

        if (language == 0) {

            System.out.println("Hello. I created this program for you. The program will create an account on Interia.pl and Tibia.com. It can also confirm email and enter R-Key");
            System.out.println("Date to login will be saved to txt file (default) in MyDocuments.");
            System.out.println("Program moze tworzyc konta na losowych danych pobranych ze zbioru danych lub tworzyc na podstawie danych podanych przez uzytkownika.");
            System.out.println("Program moze tworzyc wiele kont, wystarczy wpisac ilosc np: 5 - wtedy utworzy 5 kont na interia i 5 kont w Tibii");


        } else {

            System.out.println("Witaj. Stworzylem ten program aby zakladal konta na Interia.pl, na Tibia.com, potrafi tez potwierdzic email oraz wprowadzi R-Key'a.");
            System.out.println("Dane do logowania zostaną zapisane do pliku txt domyslnie w Dokumentach.");
            System.out.println("Program moze tworzyc konta na losowych danych pobranych ze zbioru danych lub tworzyc na podstawie danych podanych przez uzytkownika.");
            System.out.println("Program moze tworzyc wiele kont, wystarczy wpisac ilosc np: 5 - wtedy utworzy 5 kont na interia i 5 kont w Tibii");
            if (Engine.testPhase) {
                System.out.println("Program jest w fazie testow.");
            }
            System.out.println("Program dziala w oparciu o Selenium (narzedzie do automatyzacji testow)");
            System.out.println();

            int menuLength = plMenu.length-1;

            System.out.println("Wybierz element z menu [0-" + menuLength + "]");

            for (int i = 0; i < plMenu.length; i++) {
                System.out.println(plMenu[i]);
            }

            String menuSelectS = sc.nextLine();
            boolean correctInput = false;

            while (!correctInput) {
                for (int i = 0; i <= menuSelectS.length() - 1; i++) {
                    if (menuSelectS.charAt(i) >= 48 && menuSelectS.charAt(i) <= 57) {
                        menuSelect = Integer.parseInt(menuSelectS);
                        correctInput = true;
                    } else {
                        System.err.println("Wprowadz liczby");
                        System.out.println("Wybierz element z menu [0-" + menuLength + "]");
                        menuSelectS = sc.nextLine();
                        break;
                    }
                    if (menuSelect < 0 || menuSelect > plMenu.length) {
                        System.out.println("Wprowadz poprawne dane [0-" + menuLength + "] wartosc musi byc cyfra");
                        menuSelectS = sc.nextLine();
                    }
                }
            }



            boolean menu = true;

            while (menu) {
                menu = false;
                switch (menuSelect) {

                    case 0:
                        System.out.println("Metoda na tworzenie konta na losowych danych");

                        pa.makeAccountInteria();
                        Engine.wait(3000);
                        pa.makeAccountTibia();
                        pa.confirmAccount();

                        ManageFile mf = new ManageFile();
                        mf.addAccountData();

                        System.out.println("Konto zostało załorzone, oraz potwierdzone, dane zostały zapisane do pliku");
                        System.out.println(Engine.documentsPath);

                        break;
                    case 1:
                        System.out.println("Metoda na tworzenie konta na podanych danych");
                        break;
                    case 2:
                        System.out.println("Metoda na zmiane pliku z danymi");

                        break;
                    case 4:
                        System.out.println("Koniec dzialania programu");
                        break;
                    case 3:
                        System.out.println("Tworzenie postaci");


                        break;
                    default:
                        System.out.println("Wprowadz poprawne dane [0-4]");
                        menuSelect = sc.nextInt();
                        menu = true;
                }
            }


        }

    }

}