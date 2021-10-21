package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {

    private WebDriver driver;

    private By usuarioLogado = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]/span");

    public MyAccountPage(WebDriver driver){
        this.driver = driver;
    }
    public boolean estaLogado(String usuario){
        return usuario.contentEquals(driver.findElement(usuarioLogado).getText());
    }
}
