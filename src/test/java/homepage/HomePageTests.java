package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HomePageTests extends BaseTests {

    LoginPage loginPage;
    ProdutoPage produtoPage;
    ModalProdutoPage modalProdutoPage;

    @Test
    public void testContarProdutos_oitoProdutosDiferentes() {
        carregarPaginaInicial();
        assertThat(homePage.contarProdutos(), is(8));
    }

    @Test
    public void testValidarCarrinhoZerado() {

        int produtosNoCarrinho = homePage.obterQuantidadeNoCarrinho();

        assertThat(produtosNoCarrinho, is(0));
    }

    @Test
    public void testValidarDetalhesDoProduto_DescricaoEValor() {
        String nomeProduto_HomePage = homePage.obterNomeDoProduto(0);
        String precoProduto_HomePage = homePage.obterPrecoDoProduto(0);

        produtoPage = homePage.clicarProduto(0);

        String nomeProduto_ProdutoPage = produtoPage.obterNomeDoProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoDoProduto();

        assertThat(nomeProduto_HomePage, is(nomeProduto_ProdutoPage) );
        assertThat(precoProduto_HomePage, is(precoProduto_ProdutoPage));
    }

    @Test
    public void testLoginComSucesso_UsuarioLogado(){
        loginPage = homePage.clicarBotaoSignInDaHomePage();

        loginPage.preencherEmail("xico@email.com");
        loginPage.preencherPassword("987654321");
        loginPage.clicarBotaoSignIn();

        assertThat(homePage.estaLogado("Xico Santos"), is(true));

        carregarPaginaInicial();
    }

    @Test
    public void incluirProdutoNoCarrinho_IncluidoComSucesso(){

        String tamanhoProduto = "XL";
        String corProduto = "Black";
        int quantidadeDesejada = 2;

        if(!homePage.estaLogado("Xico Santos")){
            testLoginComSucesso_UsuarioLogado();
        }
        testValidarDetalhesDoProduto_DescricaoEValor();

        produtoPage.selecionarOpcaoDropDown(tamanhoProduto);

        produtoPage.SelecionarCorPreta();

        produtoPage.alterarQuantidadeDoProduto(quantidadeDesejada);

        modalProdutoPage = produtoPage.botaoAdicionarNoCarrinho();

        assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado()
                .contains("Product successfully added to your shopping cart"));

        assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));
        assertThat(modalProdutoPage.obterCorProduto(), is(corProduto));
        assertThat(modalProdutoPage.obterQuantidadeProduto(), is(Integer.toString(quantidadeDesejada)));




    }

}
