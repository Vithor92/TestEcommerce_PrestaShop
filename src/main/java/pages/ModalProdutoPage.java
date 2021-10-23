package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalProdutoPage {

    private WebDriver driver;

    private By mensagemProdutoAdicionado = By.id("myModalLabel");
    private By descricaoProduto = By.className("product-name");
    private By precoProduto = By.cssSelector("div.modal-body p.product-price");
    private By listaValoresInformados = By.cssSelector("div.col-md-6:nth-child(2) span strong");


    public ModalProdutoPage(WebDriver driver){
        this.driver = driver;
    }

    public String obterMensagemProdutoAdicionado(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemProdutoAdicionado));

        return driver.findElement(mensagemProdutoAdicionado).getText();
    }

    public String obterTamanhoProduto(){
        return driver.findElements(listaValoresInformados).get(0).getText();
    }

    public String obterCorProduto(){
        return driver.findElements(listaValoresInformados).get(1).getText();
    }

    public String obterQuantidadeProduto(){
        return driver.findElements(listaValoresInformados).get(2).getText();
    }



}
