package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    List<WebElement> listaProdutos = new ArrayList<>();

    private By produtos = By.className("product-description");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public int contarProdutos(){
        carregarListaProdutos();
        return listaProdutos.size();
    }

    private void carregarListaProdutos(){
        listaProdutos = driver.findElements(produtos);
    }

}
