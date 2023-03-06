package pl.acc_creator.app;


public interface Scrapper {

    void openBrowser(String url);
    void manageCookies(boolean registryCookies);
    void findElements(boolean register);
    void sendData(boolean register);
    void setList();
    void createAcc();
    void login();
    void closeDriver();

}
