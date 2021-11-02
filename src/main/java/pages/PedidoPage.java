package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PedidoPage {

    private WebDriver driver;

    private By confirmacaoDaOrder = By.cssSelector("#content-hook_order_confirmation h3");
    private By emailDaOrder = By.cssSelector(".col-md-12 > p:nth-child(2)");
    private By subTotalDoProduto = By.cssSelector("#order-items .row .col-xs-4:nth-child(3)");
    private By totalDoProduto = By.cssSelector(".total-value td:nth-child(2)");
    private By metodoDePagamento = By.cssSelector("#order-details > ul >li:nth-child(2)");

    public PedidoPage (WebDriver driver){
        this.driver = driver;
    }

    public String obterStatusDeConfirmacaoDaOrder(){
        return driver.findElement(confirmacaoDaOrder).getText();
    }

    public String emailDoUsuarioNaOrder(){
        return driver.findElement(emailDaOrder).getText();
    }

    public String subTotalDoProdutoNaOrder(){
        return driver.findElement(subTotalDoProduto).getText();
    }

    public String totalDoProdutoNaOrder(){
        return driver.findElement(totalDoProduto).getText();
    }

    public String retornarMetodoDePagamento(){
        return driver.findElement(metodoDePagamento).getText();
    }
}
