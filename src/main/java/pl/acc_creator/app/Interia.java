package pl.acc_creator.app;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.lang.reflect.Array;
import java.util.*;

public class Interia implements Scrapper{

    WebElement email, password, loginButton;
    WebElement month, gender, acceptAll, regButton;
    List<WebElement> textFields;
    String confirmLink;

    public Interia() {
        ChromeOptions optionsInteria = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsInteria.setHeadless(Engine.headless);
        optionsInteria.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        optionsInteria.addArguments("--disable-notifications");
        Engine.driver = new ChromeDriver(optionsInteria);

    }

    @Override
    public void openBrowser(String url) {
        Engine.driver.get(url);
        Engine.driver.manage().window().maximize();
    }

    @Override
    public void manageCookies(boolean logCookies) {
        String text = "Przejdź do serwisu";
        WebElement accept = Engine.driver.findElement(By.xpath("//button[text()='" + text + "']"));
        accept.click();
    }

    @Override
    public void findElements(boolean register) {
        if (!register) {
            email = Engine.driver.findElement(By.id("email"));
            password = Engine.driver.findElement(By.id("password"));
            loginButton = Engine.driver.findElement(By.xpath("//button[text()='Zaloguj się']"));
        } else {


            acceptAll = Engine.driver.findElement(By.xpath("//*[text()='Akceptuję i zaznaczam wszystkie poniższe zgody']"));
            regButton = Engine.driver.findElement(By.xpath("//button[text()='Załóż darmowe konto']"));
            List<WebElement> inputs = Engine.driver.findElements(By.tagName("input"));
            textFields = new ArrayList<>();

            for (int i = 0; i < inputs.size(); i++) {
                if (inputs.get(i).isDisplayed() || i == 5 || i == 7 || i == 9) {
                    textFields.add(inputs.get(i));
                }
                if (Engine.testPhase) {
                    System.out.println(i + " " + inputs.get(i).isDisplayed() + " okienko: " + inputs.get(i).getAccessibleName());
                }
            }

           if (Engine.testPhase) {
               for (int i = 0; i < textFields.size(); i++) {
                   System.out.println(i + " " + textFields.get(i).isDisplayed() + " okienko: " + textFields.get(i).getAccessibleName());
               }
           }


        }

    }

    @Override
    public void sendData(boolean register) {

        if (!register) {
            email.sendKeys(Engine.email);
            password.sendKeys(Engine.password);

        } else {


            for (int i = 0; i < Engine.userDataTable.length; i++) {
                if (i != 3 && i != 5 && i != 7) {
                    if (Engine.testPhase) {
                        System.out.println("Do pola " + i + " wpisałem dane: " + Engine.userDataTable[i]);
                    }
                    textFields.get(i).sendKeys(Engine.userDataTable[i]);
                    if (i == 8) {
                        if (Engine.testPhase) {
                            System.out.println("Do pola " + (i + 1) + " wpisałem dane: " + Engine.userDataTable[i]);
                        }
                        textFields.get(i+1).sendKeys(Engine.userDataTable[i]);
                    }
                } else {
                    if (Engine.testPhase) {
                        System.out.println("Pole nie jest tekstowe");
                    }
                }
            }



        }

    }

    @Override
    public void setList() {

        month = Engine.driver.findElement(By.xpath("//*[text()='Miesiąc']"));
        month.click();
        String selectMonth = getMonthString();
        month = Engine.driver.findElement(By.xpath("//*[text()='" + selectMonth + "']"));
        month.click();



        gender = Engine.driver.findElement(By.xpath("//*[text()='Płeć']"));
        gender.click();
        String mOrF;
        if (Engine.gender == 0) {
            mOrF = "Mężczyzna";
        } else {
            mOrF = "Kobieta";
        }
        gender = Engine.driver.findElement(By.xpath("//*[text()='" + mOrF + "']"));
        gender.click();


        acceptAll.click();

    }

    @Override
    public void createAcc() {

        regButton.click();

    }

    @Override
    public void login() {
        loginButton.click();
    }

    @Override
    public void closeDriver() {
        Engine.driver.quit();
    }


    public void choseSkin() {


//        List<WebElement> links = Engine.driver.findElements(By.tagName("role"));
//        System.out.println(links.size());
//        for (int i = 0; i < links.size(); i++) {
//            System.out.println("Element: " + i + links.get(i).isDisplayed());
//            System.out.println("Element: " + i + links.get(i).isEnabled());
//            System.out.println("Element: " + i + links.get(i).getAccessibleName());
//            System.out.println("Element: " + i + links.get(i).getText());
//            System.out.println();
//        }


        Engine.driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[3]/div/div[2]/div/div[1]")).click();
        Engine.wait(2000);
    }

    public void findConfirmMessage() {
        String title = "noreply@tibia.com";
        String linkPart = "https://www.tibia.com/account";
        Engine.driver.findElement(By.xpath("//*[@title='" + title + "']")).click();
        Engine.wait(1000);
//        WebElement detailButton = Engine.driver.findElement(By.xpath("//button[text()='Anuluj']")).click();
//        WebElement confirmMess = Engine.driver.findElement(By.partialLinkText(linkPart));
//        WebElement confirmMess = Engine.driver.findElement(By.linkText("https://www.tibia.com/account/?subtopic=createaccount&step=confirmaccount&email=c3RlZmFuaWEubWFyaXNva2FAaW50ZXJpYS5wbA==&confirmationkey=1g169mgp94fm1fe165j4"));
//

        // lokalizowanie iframe

        Engine.driver.switchTo().frame(1);

//        List<WebElement> links = Engine.driver.findElements(By.tagName("span"));
//        System.out.println(links.size());
//        for (int i = 0; i < links.size(); i++) {
//            System.out.println("Element: " + i + links.get(i).isDisplayed());
//            System.out.println("Element: " + i + links.get(i).isEnabled());
//            System.out.println("Element: " + i + links.get(i).getAccessibleName());
//            System.out.println("Element: " + i + links.get(i).getText());
//            System.out.println();
//        }
        System.out.println(Engine.driver.getTitle());
        WebElement e = Engine.driver.findElement(By.xpath("//*[text()='Welcome to Tibia!\n" +
                "\n" +
                "Thank you for registering for Tibia.\n" +
                "\n" +
                "To be able to fully experience all features of a Tibia free account,\n" +
                "you need to confirm your account. To do so, please click on the\n" +
                "following link:\n" +
                "    ']"));


        String textContainLink = e.getText();
        confirmLink = Engine.getLinkFromText(textContainLink);

        System.out.println(confirmLink);



        //  //      /   //              mozna przechwycic link z wiadomosci i otowrzyc nowego drivera z linkiem

        // po ifamre



        ///////////////// KONIEC METODY!!!!


//        Scanner skaner = new Scanner(System.in);
//        System.out.println("jestes juz na tibia.com");
//        System.out.println("Czy mam lokalizować r keya?");
//        String test = skaner.nextLine();
//        Engine.wait(4000);


//            WebElement tableCell = Engine.driver.findElement(By.xpath("//*[@id=\"createaccount\"]/div[5]/div/div/div[2]/table/tbody/tr/td/div/table/tbody/tr/td/center/font/b/text()[2]"));
//        System.out.println(tableCell.getText());

//        WebElement confirmKey = Engine.driver.findElement(By.xpath("//span[text()='Confirm']"));
//                Engine.wait(1000);
//        confirmKey.click();


//        System.out.println(Engine.driver.getTitle());

        // przejscie na nowe okno w driverze


//
//        String currentWindow = Engine.driver.getWindowHandle();
//
//        Set<String> windowNames = Engine.driver.getWindowHandles();
//        for (String window : windowNames) {
//            if (!window.equals(currentWindow)) {
//                        Engine.driver.switchTo().window(window);
//            }
//        }
//
////        WebElement confirmKey = Engine.driver.findElement(By.xpath("//span[text()='Confirm']"));
////                Engine.wait(1000);
////        confirmKey.click();
//                Engine.wait(1000);
//        System.out.println(Engine.driver.getTitle());
//
//
//                List<WebElement> tables = Engine.driver.findElements(By.tagName("table"));
//                String textContainsKey = tables.get(1).getText();
//        System.out.println(textContainsKey);



//        System.out.println(test11.size());
//        for (int i = 0; i < test11.size(); i++) {
//            System.out.println("Element: " + i + test11.get(i).getText());
//            System.out.println();
//        }



//        WebElement tableCell = Engine.driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]"));
//        System.out.println(tableCell.getText());


//        Engine.wait(1000);
//        confirmMess.click();

    }


    public void confirmKey() {

        Engine.driver.findElement(By.xpath("//span[text()='Accept All']")).click();
        Engine.wait(1000);
        WebElement tableCell = Engine.driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]"));

        String[] partsKey = Engine.getKeyFromTextToString(tableCell.getText());

        Engine.wait(1000);

        List<WebElement> confirmKeys = Engine.driver.findElements(By.tagName("input"));

        confirmKeys.get(8).click();

        Engine.wait(1000);

        String name = "key";
        WebElement[] keyField = new WebElement[4];
        for (int i = 0; i < keyField.length; i++) {
            keyField[i] = Engine.driver.findElement(By.name("key" + (i + 1)));
            keyField[i].sendKeys(partsKey[i]);
        }

        Engine.wait(2000);

        confirmKeys = Engine.driver.findElements(By.tagName("input"));

        confirmKeys.get(14).click();

        Engine.wait(3000);
    }



    public String getMonthString() {
        String numberOfMonth = String.valueOf(Birthdate.month);
        switch (numberOfMonth) {
            case "1" :
                return "Styczeń";
            case "2" :
                return "Luty";
            case "3" :
                return "Marzec";
            case "4" :
                return "Kwiecień";
            case "5" :
                return "Maj";
            case "6" :
                return "Czerwiec";
            case "7" :
                return "Lipiec";
            case "8" :
                return "Sierpień";
            case "9" :
                return "Wrzesień";
            case "10" :
                return "Październik";
            case "11" :
                return "Listopad";
            case "12" :
                return "Grudzień";
            default:
                return "Marzec";
        }
    }



}
