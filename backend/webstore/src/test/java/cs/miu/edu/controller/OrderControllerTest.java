package cs.miu.edu.controller;

import cs.miu.edu.service.dto.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;


import java.time.LocalDate;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author Prabhat Gyawali
 * @created 13-Jul-2021 - 11:26 AM
 * @project webstore
 */
public class OrderControllerTest {
    //initializing some dummy data for testing
    ProductDTO productDTO = new ProductDTO("A47", "TestProduct", 45.0, "testDescription", 10);
    UserDTO userDTO = new UserDTO("Prabhat", "pgyawali@miu.edu", "123456789", "1000 Nth", "Fairfield", "52557");
    PaymentDTO paymentDTO = new PaymentDTO("VISA", "378282246310005", LocalDate.of(2022, 01, 01), "123");
    OrderDTO orderDTO = new OrderDTO(null, userDTO, paymentDTO, Arrays.asList(new OrderedProductDTO(productDTO, 8)), null);

    @BeforeClass
    public static void setUp() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    public void addTheProduct() {
        given()
                .when()
                .post("/products?productNumber=A47&name=TestProduct&price=45&numberInStock=10&description=testdescription&operation=addnew")
                .then()
                .statusCode(200);
    }

    public Integer placeOrder() {
        return given()
                .when()
                .contentType("application/json")
                .body(orderDTO)
                .post("/orders")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("orderId");
    }

    public void cleanProduct() {
        given()
                .when()
                .delete("/products/A47")
                .then()
                .statusCode(200);
    }

    public void cleanOrder(Integer orderId) {
        given()
                .when()
                .delete("/orders/" + orderId)
                .then()
                .statusCode(200);
    }

    @Test
    public void testOrderPlaced() {

        //add the product
        this.addTheProduct();

        //place the order
        Integer orderId = this.placeOrder();

        //get and verify order
        given()
                .when()
                .get("/orders/" + orderId)
                .then()
                .statusCode(200)
                .body("orderedProducts.quantity", hasItems(8))
                .body("status", equalTo("PLACED"))
                .body("owner.name", equalTo(userDTO.getName()))
                .body("owner.email", equalTo(userDTO.getEmail()))
                .body("owner.phone", equalTo(userDTO.getPhone()))
                .body("owner.street", equalTo(userDTO.getStreet()))
                .body("owner.city", equalTo(userDTO.getCity()))
                .body("payment.creditCardType", equalTo(paymentDTO.getCreditCardType()))
                .body("payment.number", equalTo(paymentDTO.getNumber()))
                .body("payment.validDate", equalTo(paymentDTO.getValidDate().toString()))
                .body("payment.validationCode", equalTo(paymentDTO.getValidationCode()))
                .body("orderedProducts.product.productNumber", hasItems(productDTO.getProductNumber()));

        //clean up the data
        this.cleanOrder(orderId);
        this.cleanProduct();
    }

    @Test
    public void testFindAllOrderPlaced() {

//        //add the product
        this.addTheProduct();

        //place the order
        Integer orderId = this.placeOrder();
        //get and verify order
        given()
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .body("orders.status", hasItems("PLACED"))
                .body("orders.owner.name", hasItems(userDTO.getName()))
                .body("orders.owner.email", hasItems(userDTO.getEmail()))
                .body("orders.owner.phone", hasItems(userDTO.getPhone()))
                .body("orders.owner.street", hasItems(userDTO.getStreet()))
                .body("orders.owner.city", hasItems(userDTO.getCity()))
                .body("orders.payment.creditCardType", hasItems(paymentDTO.getCreditCardType()))
                .body("orders.payment.number", hasItems(paymentDTO.getNumber()))
                .body("orders.payment.validDate", hasItems(paymentDTO.getValidDate().toString()))
                .body("orders.payment.validationCode", hasItems(paymentDTO.getValidationCode()));

        //clean up the data
        this.cleanOrder(orderId);
        this.cleanProduct();
    }

    @Test
    public void testDeleteOrderPlaced() {

        //add the product
        this.addTheProduct();

        //place the order
        Integer orderId = this.placeOrder();

        //delete the order
        this.cleanOrder(orderId);

        //get and verify order not exist
        given()
                .when()
                .get("/orders/" + orderId)
                .then()
                .statusCode(404)
                .body("message", containsString("not found"));

        //clean up the data
        this.cleanProduct();
    }

    @Test
    public void testStatusChangeOfOrderPlaced() {

        //add the product
        this.addTheProduct();

        //place the order
        Integer orderId = this.placeOrder();
        //change the status
        given()
                .when()
                .put("/orders/" + orderId + "/status?status=SHIPPED")
                .then()
                .statusCode(200);


        //get and verify status is changed
        given()
                .when()
                .get("/orders/" + orderId)
                .then()
                .statusCode(200)
                .body("status", equalTo("SHIPPED"));

        //clean up the data
        this.cleanOrder(orderId);
        this.cleanProduct();

    }
}

