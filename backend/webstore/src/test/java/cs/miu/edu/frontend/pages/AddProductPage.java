package cs.miu.edu.frontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Prabhat Gyawali
 * @created 14-Jul-2021 - 4:43 PM
 * @project webstore
 */
public class AddProductPage {
    private final WebDriver driver;

    public AddProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(name = "productNumber")
    private WebElement productNumber;

    @FindBy(name="name")
    private WebElement name;

    @FindBy(name="numberInStock")
    private WebElement numberInStock;

    @FindBy(name="price")
    private WebElement price;

    @FindBy(id="description")
    private WebElement description;

    @FindBy(id="btnDelete_D1")
    private WebElement deleteProductButton;


    @FindBy(css="button#addProduct")
    private WebElement addButton;

    @FindBy(css="#swal2-html-container")
    private WebElement swalStatus;

    @FindBy(css="body > div.swal2-container.swal2-center.swal2-backdrop-show > div > div.swal2-actions > button.swal2-confirm.swal2-styled")
    private WebElement swalButton;



    public void enterData(String productNumber, String name, String numberInStock, String price, String description){
        this.name.clear();
        this.name.sendKeys(name);
        this.productNumber.clear();
        this.productNumber.sendKeys(productNumber);
        this.numberInStock.clear();
        this.numberInStock.sendKeys(numberInStock);
        this.price.clear();
        this.price.sendKeys(price);
        this.description.clear();
        this.description.sendKeys(description);
    }

    public void verifyStatus(String message) throws InterruptedException {
        addButton.click();
        Thread.sleep(3000);
        assertEquals(swalStatus.getText(), (message));
        swalButton.click();
    }
    public void verifyDelete(String message) throws InterruptedException {
        deleteProductButton.click();
        Thread.sleep(3000);
        assertEquals(swalStatus.getText(), (message));
        swalButton.click();
    }

    public void clickAddProduct(){
       addButton.click();
    }

    public void open() {
        this.driver.get("http://localhost:3000/");
    }

    public void close(){
        this.driver.close();
    }
}
