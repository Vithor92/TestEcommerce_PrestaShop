package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;

    private By totalComTaxInclude = By.cssSelector(".cart-total span:nth-child(2)");


    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public String obterTotalComTaxInclude(){
        return driver.findElement(totalComTaxInclude).getText();
    }

}
