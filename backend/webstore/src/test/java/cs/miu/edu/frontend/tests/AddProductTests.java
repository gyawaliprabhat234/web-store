package cs.miu.edu.frontend.tests;

import cs.miu.edu.frontend.pages.AddProductPage;
import cs.miu.edu.frontend.pages.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * @author Prabhat Gyawali
 * @created 14-Jul-2021 - 4:58 PM
 * @project webstore
 */
public class AddProductTests {
    private static AddProductPage addProductPage;
    private static HomePage homePage;
    WebDriver driver;



    @Before
    public  void Before() {
        System.setProperty("webdriver.chrome.driver", "F:\\Driver\\Chrome\\chromedriver.exe");
        // create chrome instance
        driver= new ChromeDriver();
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
    public void addProductTest() throws InterruptedException {
        addProductPage = homePage.clickAddProduct();
        addProductPage.enterData("T1", "Test Product1", "3", "34", "Test Description");
        addProductPage.verifyStatus("Successfully created");
    }

//    @Test
//    public void UnsuccessfulLogin() throws InterruptedException {
//        createAccountPage.enterUsernameAndPassword("user1", "12345");
//        loginPage = createAccountPage.clickRegister();
//        loginPage.enterUsernameAndPassword("user1", "1234");
//        loginPage.clickLoginButton();
//        assertThat(loginPage.getErrorMsg(),containsString("Wrong"));
//    }
//    @Test
//    public void successfulLogin() throws InterruptedException {
//        createAccountPage.enterUsernameAndPassword("user1", "12345");
//        loginPage = createAccountPage.clickRegister();
//        loginPage.enterUsernameAndPassword("user1", "12345");
//        loginPage.clickLoginButton();
//        assertThat(driver.getCurrentUrl(),containsString("welcome"));
//    }
}
