package cs.miu.edu.frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Prabhat Gyawali
 * @created 14-Jul-2021 - 8:52 PM
 * @project webstore
 */
public class ProductPage {
    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "addToCart")
    private WebElement addToCartBtn;

    public CartPage clickAddToCartBtn(){
        this.addToCartBtn.click();
        return new CartPage(driver);
    }

    public void open(String id) {
        this.driver.get("http://localhost:3000/product/"+id);
    }

    public void close(){
        this.driver.close();
    }
}
