package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarrinhoPage {

    private WebDriver driver;

    private By nomeDoProduto = By.cssSelector(".product-line-info a");
    private By precoDoProduto = By.cssSelector("span.price");
    private By tamanhoDoProduto = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[3]/span[2]");
    private By corDoProduto = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[4]/span[2]");
    private By input_quantidadeDoProduto = By.cssSelector("input.js-cart-line-product-quantity");
    private By subTotalDoProduto = By.cssSelector("span.product-price strong");
    private By numeroDeItensTotal = By.cssSelector("#cart-subtotal-products span.label ");
    private By subTotalPainelDeValores = By.cssSelector("#cart-subtotal-products span.value");
    private By shippingTotal = By.cssSelector("#cart-subtotal-shipping span.value ");
    private By totalSemTaxInclude = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[1]/span[2]");
    private By totalComTaxInclude = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[2]/span[2]");
    private By totalTaxas = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[3]/span[2]");
    private By botaoProceedToCheckout = By.cssSelector(".text-sm-center a.btn");


    public CarrinhoPage(WebDriver driver){
        this.driver = driver;
    }

    public String obterNomeDoProduto(){
        return driver.findElement(nomeDoProduto).getText();
    }

    public String obterPrecoDoProduto(){
        return driver.findElement(precoDoProduto).getText();
    }

    public String obterTamanhoDoProduto(){
        return driver.findElement(tamanhoDoProduto).getText();
    }

    public String obterCorDoProduto(){
        return driver.findElement(corDoProduto).getText();
    }

    public String obterInputQuantidadeDoProduto(){
        return driver.findElement(input_quantidadeDoProduto).getAttribute("value");
    }

    public String obterSubtotalDoProduto(){
        return driver.findElement(subTotalDoProduto).getText();
    }

    public String obterNumeroDeItensTotal(){
        return driver.findElement(numeroDeItensTotal).getText();
    }

    public String obterSubtotalPainelDeValores(){
        return driver.findElement(subTotalPainelDeValores).getText();
    }

    public String obterShippingTotal(){
        return driver.findElement(shippingTotal).getText();
    }

    public String obterTotalSemTaxInclude(){
        return driver.findElement(totalSemTaxInclude).getText();
    }

    public String obterTotalComTaxInclude(){
        return driver.findElement(totalComTaxInclude).getText();
    }

    public String obterTotalTaxas(){
        return driver.findElement(totalTaxas).getText();
    }

    public CheckoutPage clicarBotaoProceedToCheckout(){
        driver.findElement(botaoProceedToCheckout).click();
        return new CheckoutPage(driver);
    }


}
