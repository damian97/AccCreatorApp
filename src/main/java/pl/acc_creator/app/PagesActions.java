package pl.acc_creator.app;

public class PagesActions {


    public void makeAccountInteria() {

        Engine.drawPerson();
        Engine.showPersonInfo();

        Interia interia = new Interia();
        interia.openBrowser("https://konto-pocztowe.interia.pl/#/nowe-konto/darmowe");
        interia.manageCookies(false);
        interia.findElements(true);
        interia.sendData(true);
        interia.setList();
        Engine.wait(1000);
        interia.createAcc();
        Engine.wait(10000);
        interia.closeDriver();

    }

    Interia interia;
    public void loginInteria(boolean closeDriver) {


        interia = new Interia();
        interia.openBrowser("https://poczta.interia.pl/logowanie/");
        interia.manageCookies(true);
        interia.findElements(false);
        interia.sendData(false);
        Engine.wait(1000);
        interia.login();


        Engine.wait(2000);
        if (closeDriver) {
            interia.closeDriver();
        }

    }


    public void makeAccountTibia() {
        Tibia tibia = new Tibia();
        tibia.openBrowser("https://www.tibia.com");
        tibia.manageCookies(false);
        tibia.findElements(true);
        tibia.sendData(true);
        Engine.wait(1000);
        tibia.createAcc();
        Engine.wait(5000);
        tibia.closeDriver();

    }



    public void confirmAccount() {

        loginInteria(false);

        // kod do znajdowania emaila i potwierdzania
        Engine.wait(2000);


        interia.choseSkin();

        Engine.wait(2000);

        interia.findConfirmMessage();

        Engine.wait(2000);

        interia.openBrowser(interia.confirmLink);

        interia.confirmKey();
        

        interia.closeDriver();
    }



}
