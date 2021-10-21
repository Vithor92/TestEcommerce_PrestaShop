package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By email = By.name("email");
    private By password = By.name("password");
    private By botaoSignIn = By.id("submit-login");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void preencherEmail(String Email){
        driver.findElement(email).sendKeys(Email);
    }

    public void preencherPassword(String Password){
        driver.findElement(password).sendKeys(Password);
    }

    public HomePage clicarBotaoSignIn(){
        driver.findElement(botaoSignIn).click();
        return new HomePage(driver);
    }

}
