package cs.miu.edu.frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Prabhat Gyawali
 * @created 14-Jul-2021 - 4:42 PM
 * @project webstore
 */
public class HomePage {
    private final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "linkToProducts")
    private WebElement products;

    @FindBy(id="linkToOrders")
    private WebElement orders;

    @FindBy(id="linkToAddProduct")
    private WebElement addProduct;

    @FindBy(id="linkToCart")
    private WebElement cart;

    public void clickProducts(){
        products.click();
    }
    public void clickOrders(){
        orders.click();
    }
    public AddProductPage clickAddProduct(){
        addProduct.click();
        return new AddProductPage(driver);
    }
    public void clickCart(){
        cart.click();
    }

    public void open() {
        this.driver.get("http://localhost:3000/");
    }

    public void close(){
        this.driver.close();
    }
}
