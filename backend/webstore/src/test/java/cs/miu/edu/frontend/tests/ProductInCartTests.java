package cs.miu.edu.frontend.tests;

import cs.miu.edu.frontend.pages.AddProductPage;
import cs.miu.edu.frontend.pages.CartPage;
import cs.miu.edu.frontend.pages.HomePage;
import cs.miu.edu.frontend.pages.ProductPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Prabhat Gyawali
 * @created 14-Jul-2021 - 8:48 PM
 * @project webstore
 */
public class ProductInCartTests {

    private static AddProductPage addProductPage;
    private static HomePage homePage;
    private static ProductPage productPage;
    private static CartPage cartPage;
    WebDriver driver;

    @Before
    public void Before() {
        System.setProperty("webdriver.chrome.driver", "F:\\Driver\\Chrome\\chromedriver.exe");
        // create chrome instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        homePage.open();
    }

    @After
    public void After() {
        homePage.close();
    }

    //Your registration completed
    @Test
    public void testProductInCart() throws InterruptedException {
        addProductPage = homePage.clickAddProduct();
        addProductPage.enterData("T11", "Test Product11", "3", "34", "Test Description");
        addProductPage.verifyStatus("Successfully created");

        addProductPage.enterData("T12", "Test Product12", "3", "34", "Test Description");
        addProductPage.verifyStatus("Successfully created");

        productPage = new ProductPage(driver);
        productPage.open("T11");
        Thread.sleep(3000);
        cartPage = productPage.clickAddToCartBtn();
//        productPage.open("T12");
//        cartPage = productPage.clickAddToCartBtn();
        cartPage.verifyItems("1");

        // addProductPage.verifyStatus("Successfully created");
    }
}
