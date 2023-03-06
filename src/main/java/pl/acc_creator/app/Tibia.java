package pl.acc_creator.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Tibia extends Interia implements Scrapper{

    WebElement email, password, nick, playButton;
    String nickChar = "test";

    public Tibia() {

    }

    @Override
    public void openBrowser(String url) {
        super.openBrowser(url);
    }

    @Override
    public void manageCookies(boolean logCookies) {
        Engine.driver.findElement(By.xpath("//span[text()='Accept All']")).click();
    }

    @Override
    public void findElements(boolean register) {
        if (register)
        email = Engine.driver.findElement(By.id("email"));
        password = Engine.driver.findElement(By.id("password1"));
        nick = Engine.driver.findElement(By.id("charactername"));
        playButton = Engine.driver.findElement(By.id("ButtonPlayNow"));
    }

    @Override
    public void sendData(boolean register) {
        email.sendKeys(Engine.email + "@interia.pl");
        password.sendKeys(Engine.password);
    }

    @Override
    public void setList() {

    }

    @Override
    public void createAcc() {
        playButton.click();
        System.out.println("Klikam 'Play Now'");
    }

    @Override
    public void login() {

    }

    @Override
    public void closeDriver() {
        super.closeDriver();
    }
}
