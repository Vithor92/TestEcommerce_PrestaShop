package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;

    private By totalComTaxInclude = By.cssSelector(".cart-total span:nth-child(2)");
    private By nomeDoCliente = By.cssSelector("div.address");
    private By botaoContinueParaShipping = By.name("confirm-addresses");
    private By valorShipping = By.className("carrier-price");
    private By botaoContinueParaPayment = By.name("confirmDeliveryOption");
    private By pagamentoPayByCheck = By.id("payment-option-1");
    private By valorDoPayByCheck = By.cssSelector("#payment-option-1-additional-information > section > dl > dd:nth-child(2)");
    private By aceitarTermsOfService = By.id("conditions_to_approve[terms-and-conditions]");
    private By concluirOrder = By.cssSelector("#payment-confirmation");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public String obterTotalComTaxInclude(){
        return driver.findElement(totalComTaxInclude).getText();
    }

    public String obterNomeDoCliente(){
        return driver.findElement(nomeDoCliente).getText();
    }

    public void clicarBotaoContinueParaShipping(){
        driver.findElement(botaoContinueParaShipping).click();
    }

    public String obterValorShipping(){
        return driver.findElement(valorShipping).getText();
    }

    public void clicarBotaoContinueParaPayment(){
        driver.findElement(botaoContinueParaPayment).click();
    }

    public void selecionarEmPagamentoPayByCheck(){
        driver.findElement(pagamentoPayByCheck).click();
    }

    public String obterValorDoPayByCheck(){
        return driver.findElement(valorDoPayByCheck).getText();
    }

    public void clicarEmAceitarTermsOfService(){
        driver.findElement(aceitarTermsOfService).click();
    }

    public boolean seAceitarTermsOfServiceEstaSelecionado(){
        return driver.findElement(aceitarTermsOfService).isSelected();
    }

    public PedidoPage finalizarPedidoBotaoConcluirOrder(){
        driver.findElement(concluirOrder).click();
        return new PedidoPage(driver);
    }


}
