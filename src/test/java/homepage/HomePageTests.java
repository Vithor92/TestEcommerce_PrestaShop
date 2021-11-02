package homepage;

import base.BaseTests;
import org.junit.jupiter.api.Test;
import pages.*;
import util.Funcoes;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HomePageTests extends BaseTests {

    LoginPage loginPage;
    ProdutoPage produtoPage;
    ModalProdutoPage modalProdutoPage;
    String nomeProduto_HomePage;
    CarrinhoPage carrinhoPage;
    CheckoutPage checkoutPage;
    PedidoPage pedidoPage;

    String esperado_nomeProduto = "Hummingbird printed t-shirt";
    Double esperado_precoProduto = 19.12;
    String esperado_tamanhoDoProduto = "XL";
    String esperado_corDoProduto = "Black";
    String esperado_inputQuantidadeDoProduto = "2";
    Double esperado_subTotalDoProduto = 38.24;
    int esperado_numeroDeItensTotal = 2;
    Double esperado_subtotalPainelDeValores = 38.24;
    Double esperado_shippingTotal = 7.00;
    Double esperado_totalSemTaxInclude = 45.24;
    Double esperado_totalComTaxInclude = 45.24;
    Double esperado_totalTaxas = 0.00;
    String esperado_NomeDoCliente = "Xico Santos";

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
        nomeProduto_HomePage = homePage.obterNomeDoProduto(0);
        String precoProduto_HomePage = homePage.obterPrecoDoProduto(0);

        produtoPage = homePage.clicarProduto(0);

        String nomeProduto_ProdutoPage = produtoPage.obterNomeDoProduto();
        String precoProduto_ProdutoPage = produtoPage.obterPrecoDoProduto();

        assertThat(nomeProduto_HomePage, is(nomeProduto_ProdutoPage));
        assertThat(precoProduto_HomePage, is(precoProduto_ProdutoPage));
    }

    @Test
    public void testLoginComSucesso_UsuarioLogado() {
        loginPage = homePage.clicarBotaoSignInDaHomePage();

        loginPage.preencherEmail("xico@email.com");
        loginPage.preencherPassword("987654321");
        loginPage.clicarBotaoSignIn();

        assertThat(homePage.estaLogado("Xico Santos"), is(true));

        carregarPaginaInicial();
    }

    @Test
    public void incluirProdutoNoCarrinho_IncluidoComSucesso() {

        String tamanhoProduto = "XL";
        String corProduto = "Black";
        int quantidadeDesejada = 2;

        if (!homePage.estaLogado("Xico Santos")) {
            testLoginComSucesso_UsuarioLogado();
        }
        testValidarDetalhesDoProduto_DescricaoEValor();

        produtoPage.selecionarOpcaoDropDown(tamanhoProduto);

        produtoPage.SelecionarCorPreta();

        produtoPage.alterarQuantidadeDoProduto(quantidadeDesejada);

        modalProdutoPage = produtoPage.botaoAdicionarNoCarrinho();

        assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado()
                .contains("Product successfully added to your shopping cart"));

        assertThat(modalProdutoPage.obterDescricaoProduto()
                .toUpperCase(Locale.ROOT), is(nomeProduto_HomePage.toUpperCase(Locale.ROOT)));

        Double precoProduto = Funcoes.removeCifraoDevolveValorDouble(modalProdutoPage.obterPrecoProduto());

        assertThat(modalProdutoPage.obterTamanhoProduto(), is(tamanhoProduto));
        assertThat(modalProdutoPage.obterCorProduto(), is(corProduto));
        assertThat(modalProdutoPage.obterQuantidadeProduto(), is(Integer.toString(quantidadeDesejada)));

        Double subTotal = Funcoes.removeCifraoDevolveValorDouble(modalProdutoPage.obterSubTotal());

        Double subTotalCalculado = quantidadeDesejada * precoProduto;

        assertThat(subTotal, is(subTotalCalculado));

    }

     @Test
    public void irParaCarrinho_VerificarInformacoesNaPaginaCarrinho() {
        incluirProdutoNoCarrinho_IncluidoComSucesso();

        carrinhoPage = modalProdutoPage.clicarBotaoProcederParaCheckout();

        assertThat(carrinhoPage.obterNomeDoProduto(), is(esperado_nomeProduto));
        assertThat((Funcoes.removeCifraoDevolveValorDouble(carrinhoPage.obterPrecoDoProduto())),
                is(esperado_precoProduto));
        assertThat(carrinhoPage.obterTamanhoDoProduto(), is(esperado_tamanhoDoProduto));
        assertThat(carrinhoPage.obterCorDoProduto(), is(esperado_corDoProduto));
        assertThat(carrinhoPage.obterInputQuantidadeDoProduto(), is(esperado_inputQuantidadeDoProduto));
        assertThat((Funcoes.removeCifraoDevolveValorDouble(carrinhoPage.obterSubtotalDoProduto())),
                is(esperado_subTotalDoProduto));
        assertThat((Funcoes.removeTextoItensDevolveValorInteiro(carrinhoPage.obterNumeroDeItensTotal())),
                is(esperado_numeroDeItensTotal));
        assertThat((Funcoes.removeCifraoDevolveValorDouble(carrinhoPage.obterSubtotalPainelDeValores())),
                is(esperado_subtotalPainelDeValores));
        assertThat((Funcoes.removeCifraoDevolveValorDouble(carrinhoPage.obterShippingTotal())),
                is(esperado_shippingTotal));
        assertThat((Funcoes.removeCifraoDevolveValorDouble(carrinhoPage.obterTotalSemTaxInclude())),
                is (esperado_totalSemTaxInclude));
        assertThat((Funcoes.removeCifraoDevolveValorDouble(carrinhoPage.obterTotalComTaxInclude())),
                is (esperado_totalComTaxInclude));
        assertThat((Funcoes.removeCifraoDevolveValorDouble(carrinhoPage.obterTotalTaxas())),
                is(esperado_totalTaxas));

    }

    @Test
    public void irParaCheckout_VerificarFretePagamentoEndereco(){
        irParaCarrinho_VerificarInformacoesNaPaginaCarrinho();
        checkoutPage = carrinhoPage.clicarBotaoProceedToCheckout();

        assertThat(Funcoes.removeCifraoDevolveValorDouble(checkoutPage.obterTotalComTaxInclude()),
                is(esperado_totalComTaxInclude));
        assertTrue(checkoutPage.obterNomeDoCliente().contains(esperado_NomeDoCliente));

        checkoutPage.clicarBotaoContinueParaShipping();

        String valorShippingSemTexto = Funcoes.removeTextoDoValorShipping(checkoutPage.obterValorShipping());

        assertThat(Funcoes.removeCifraoDevolveValorDouble(valorShippingSemTexto),
                is(esperado_shippingTotal));

        checkoutPage.clicarBotaoContinueParaPayment();
        checkoutPage.selecionarEmPagamentoPayByCheck();

        String valorTotalDoPayCheckSemTexto = Funcoes.
                removeTextoDoValorPayByCheck(checkoutPage.obterValorDoPayByCheck());

        assertThat(Funcoes.removeCifraoDevolveValorDouble(valorTotalDoPayCheckSemTexto),
                is(esperado_totalComTaxInclude));

        checkoutPage.clicarEmAceitarTermsOfService();

        assertTrue(checkoutPage.seAceitarTermsOfServiceEstaSelecionado());

    }

    @Test
    public void finalizarPedido_EsperadoPedidoFinalizadoCorretamente(){
        irParaCheckout_VerificarFretePagamentoEndereco();
        pedidoPage = checkoutPage.finalizarPedidoBotaoConcluirOrder();

        assertTrue(pedidoPage.obterStatusDeConfirmacaoDaOrder().contains("YOUR ORDER IS CONFIRMED"));
        assertTrue(pedidoPage.emailDoUsuarioNaOrder().contains("xico@email.com"));
        assertThat(Funcoes.removeCifraoDevolveValorDouble(pedidoPage.subTotalDoProdutoNaOrder()),
                is(esperado_subTotalDoProduto));
        assertThat(Funcoes.removeCifraoDevolveValorDouble(pedidoPage.totalDoProdutoNaOrder()),
                is(esperado_totalComTaxInclude));
        assertTrue(pedidoPage.retornarMetodoDePagamento().contains("Payments by check"));



    }

}
