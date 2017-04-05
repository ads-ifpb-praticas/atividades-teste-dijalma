package br.com.dijalmasilva.system;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 04/04/17 - 21:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FilmeTest {

    private static WebDriver driver;
    @LocalServerPort
    private int port;

    @BeforeClass
    public static void setUpTest() {
        System.setProperty("wevdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
    }

    @Before
    public void setUp() {
        driver.get("localhost:" + port);
    }

    @After
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDownTest() {
        driver.quit();
    }

    @Test
    public void saveFilme() throws InterruptedException {
        WebElement element = driver.findElement(By.id("bt-cadastro"));
        element.click();
        element = driver.findElement(By.id("fTitulo"));
        element.sendKeys("Tarzan 2017");
        element = driver.findElement(By.id("fGenero"));
        element.sendKeys("Aventura");
        element = driver.findElement(By.id("fDuracao"));
        element.sendKeys("80");
        element = driver.findElement(By.id("bt-saveFilme"));
        element.click();
        Thread.sleep(1000);
        element = driver.findElement(By.id("result"));
        assertEquals("Filme cadastrado com sucesso!", element.getText());
    }
}
