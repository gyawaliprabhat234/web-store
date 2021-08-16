package cs.miu.edu.frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Prabhat Gyawali
 * @created 14-Jul-2021 - 9:00 PM
 * @project webstore
 */
public class CartPage {
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "getCountItemInCart")
    private WebElement countItemInCart;

    public void verifyItems(String count) throws InterruptedException {
        Thread.sleep(1000);
        assertEquals(count, countItemInCart.getText());
    }

    public void open(String id) {
        this.driver.get("http://localhost:3000/product/"+id);
    }

    public void close(){
        this.driver.close();
    }
}
