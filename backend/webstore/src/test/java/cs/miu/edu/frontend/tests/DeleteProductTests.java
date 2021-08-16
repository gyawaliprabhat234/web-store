package cs.miu.edu.frontend.tests;

import cs.miu.edu.frontend.pages.AddProductPage;
import cs.miu.edu.frontend.pages.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Prabhat Gyawali
 * @created 14-Jul-2021 - 9:44 PM
 * @project webstore
 */
public class DeleteProductTests {
    private static AddProductPage addProductPage;
    private static HomePage homePage;
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
    public void deleteProductTest() throws InterruptedException {
        addProductPage = homePage.clickAddProduct();
        addProductPage.enterData("D1", "Test Product D1", "3", "34", "Test Description");
        addProductPage.verifyStatus("Successfully created");
        addProductPage.verifyDelete("Successfully deleted");


    }

}
