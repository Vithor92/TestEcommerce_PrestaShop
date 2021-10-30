package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarrinhoPage {

    private WebDriver driver;

    private By nomeDoProduto = By.cssSelector(".product-line-info a");


    public CarrinhoPage(WebDriver driver){
        this.driver = driver;
    }

}
