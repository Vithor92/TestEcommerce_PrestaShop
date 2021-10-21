package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomePage {

    private WebDriver driver;

    List<WebElement> listaProdutos = new ArrayList<>();

    private By produtos = By.className("product-description");
    private By produtosNoCarrinho = By.className("cart-products-count");
    private By descricaoProduto = By.cssSelector(".product-description a");
    private By precoProduto = By.className("price");
    private By botaoSignIn = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span");
    private By usuarioLogado = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]/span");

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

    public int obterQuantidadeNoCarrinho(){
        String quantidadeNoCarrinho = driver.findElement(produtosNoCarrinho).getText();
        quantidadeNoCarrinho = quantidadeNoCarrinho.replace("(", "");
        quantidadeNoCarrinho = quantidadeNoCarrinho.replace(")", "");

        int qtdNoCarrinho = Integer.parseInt(quantidadeNoCarrinho);
        return qtdNoCarrinho;
    }

    public String obterNomeDoProduto(int indice){
        return driver.findElements(descricaoProduto).get(indice).getText().toUpperCase(Locale.ROOT);
    }

    public String obterPrecoDoProduto(int indice){
        return driver.findElements(precoProduto).get(indice).getText();
    }

    public ProdutoPage clicarProduto(int indice){
        driver.findElements(descricaoProduto).get(indice).click();
        return new ProdutoPage(driver);
    }

    public LoginPage clicarBotaoSignIn(){
        driver.findElement(botaoSignIn).click();
        return new LoginPage(driver);
    }

    public boolean estaLogado(String usuario){
        return usuario.contentEquals(driver.findElement(usuarioLogado).getText());
    }

}
