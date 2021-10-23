package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProdutoPage {

    private WebDriver driver;

    private By nomeProduto = By.className("h1");
    private By precoProduto = By.cssSelector(".current-price span:nth-child(1)");
    private By tamanhoDoProduto = By.id("group_1");
    private By selecionarCorPretaDoProduto = By.xpath("//*[@id=\"group_2\"]/li[2]/label/input");
    private By quantidadeDoProduto = By.id("quantity_wanted");
    private By botaoAddCarrinho = By.cssSelector("div>button");

    public ProdutoPage(WebDriver driver){
        this.driver = driver;
    }

    public String obterNomeDoProduto(){
        return driver.findElement(nomeProduto).getText();

    }

    public String obterPrecoDoProduto() {
        return driver.findElement(precoProduto).getText();
    }

    public void selecionarOpcaoDropDown(String opcao){
        encontrarOTamanhoNoDropdownSize().selectByVisibleText(opcao);
    }

    public List<String> obterOpçõesSelecionadas(){
        List<WebElement> elementosSelecionados =
        encontrarOTamanhoNoDropdownSize().getAllSelectedOptions();

        List<String> listaOpçõesSelecionadas = new ArrayList<>();
        for (WebElement elemento : elementosSelecionados){
            listaOpçõesSelecionadas.add(elemento.getText());
        }
        return listaOpçõesSelecionadas;
    }

    public Select encontrarOTamanhoNoDropdownSize(){
        return new Select(driver.findElement(tamanhoDoProduto));
    }

    public void SelecionarCorPreta (){
        driver.findElement(selecionarCorPretaDoProduto).click();
    }

    public void alterarQuantidadeDoProduto(int quantidade){
        driver.findElement(quantidadeDoProduto).clear();
        driver.findElement(quantidadeDoProduto).sendKeys(Integer.toString(quantidade));
    }

    public ModalProdutoPage botaoAdicionarNoCarrinho(){
        driver.findElement(botaoAddCarrinho).click();
        return new ModalProdutoPage(driver);
    }

}
