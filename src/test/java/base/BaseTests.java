package base;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTests {

    private static WebDriver driver;
    protected HomePage homePage;

    @BeforeAll
    public static void inicializar(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void carregarPaginaInicial(){
        driver.get("https://marcelodebittencourt.com/demoprestashop/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
    }

    public void capturarTela(String nomeTeste, String resultado) throws IOException {
        TakesScreenshot print = (TakesScreenshot) driver;
        File capturaDeTela = print.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(capturaDeTela, new File("resources/screenshots/" + nomeTeste + "_" + resultado + ".png"));
    }

    @AfterAll
    public static void finalizarNavegador(){
        driver.quit();
    }

}
