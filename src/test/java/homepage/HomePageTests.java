package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.ProdutoPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomePageTests extends BaseTests {

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

        ProdutoPage produtoPage = homePage.clicarProduto(0);

        String nomeProduto_ProdutoPage = produtoPage.obterNomeDoProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoDoProduto();

        assertThat(nomeProduto_HomePage, is(nomeProduto_ProdutoPage) );
        assertThat(precoProduto_HomePage, is(precoProduto_ProdutoPage));
    }

}
