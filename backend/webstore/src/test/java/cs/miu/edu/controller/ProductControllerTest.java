package cs.miu.edu.controller;

import cs.miu.edu.service.dto.ProductDTO;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author Prabhat Gyawali
 * @created 13-Jul-2021 - 10:48 AM
 * @project webstore
 */
public class ProductControllerTest {
    @BeforeClass
    public static void setUp(){
        RestAssured.port=Integer.valueOf(8080);
        RestAssured.baseURI="http://localhost";
        RestAssured.basePath ="";
    }

    @Test
    public void testCreateProduct(){
        //add the data
        given()
                .when()
                .post("/products?productNumber=A47&name=TestProduct&price=45&numberInStock=10&description=testdescription&operation=addnew")
                .then()
                .statusCode(200);

        //get and verify the data
        given().
                when()
                .get("/products/A47")
                .then()
                .statusCode(200)
                .and()
                .body("productNumber", equalTo("A47"))
                .body("name", equalTo("TestProduct"))
                .body("numberInStock", equalTo(10))
                .body("price", equalTo(45f))
                .body("description", equalTo("testdescription"));

        //clean up the data
        given()
                .when()
                .delete("/products/A47")
                .then()
                .statusCode(200);
    }

    @Test
    public void testFindAllProduct(){
        //add the data
        given()
                .when()
                .post("/products?productNumber=A47&name=TestProduct1&price=451&numberInStock=10&description=testdescription1&operation=addnew")
                .then()
                .statusCode(200);
        given()
                .when()
                .post("/products?productNumber=A48&name=TestProduct2&price=452&numberInStock=10&description=testdescription2&operation=addnew")
                .then()
                .statusCode(200);
        given()
                .when()
                .post("/products?productNumber=A49&name=TestProduct3&price=453&numberInStock=10&description=testdescription3&operation=addnew")
                .then()
                .statusCode(200);

//        get and verify the data
        given().
                when()
                .get("/products")
                .then()
                .statusCode(200)
                .and()
                .body("products.productNumber", hasItems("A47", "A48", "A49"))
                .body("products.name", hasItems("TestProduct1","TestProduct2","TestProduct3"))
                .body("products.numberInStock", hasItems(10))
                .body("products.price", hasItems(451f, 452f, 453f))
                .body("products.description", hasItems("testdescription1", "testdescription2", "testdescription3"));

        //clean up the data
        given()
                .when()
                .delete("/products/A47")
                .then()
                .statusCode(200);
        given()
                .when()
                .delete("/products/A48")
                .then()
                .statusCode(200);
        given()
                .when()
                .delete("/products/A49")
                .then()
                .statusCode(200);
    }




    @Test
    public void testUpdateProductDetails(){
        //add the data
        given()
                .when()
                .post("/products?productNumber=A47&name=TestProduct&price=45&numberInStock=10&description=testdescription&operation=addnew")
                .then()
                .statusCode(200);
        //increase the product in stock

        ProductDTO productDTO = new ProductDTO("A47", "ChangedProduct", 35.0, "changedDescription",14 );
        given()
                .when()
                .contentType("application/json")
                .body(productDTO)
                .put("/products/A47")
                .then()
                .statusCode(200);

        //get and verify the data
        given().
                when()
                .get("/products/A47")
                .then()
                .statusCode(200)
                .and()
                .body("productNumber", equalTo("A47"))
                .body("name", equalTo("ChangedProduct"))
                .body("numberInStock", equalTo(14))
                .body("price", equalTo(35f))
                .body("description", equalTo("changedDescription"));

        //clean up the data
        given()
                .when()
                .delete("/products/A47")
                .then()
                .statusCode(200);
    }

    @Test
    public void testIncreaseProductInStock(){
        //add the data
        given()
                .when()
                .post("/products?productNumber=A47&name=TestProduct&price=45&numberInStock=10&description=testdescription&operation=addnew")
                .then()
                .statusCode(200);
        //increase the product in stock
        given()
                .when()
                .post("/products?productNumber=A47&numberInStock=10&operation=increaseStock")
                .then()
                .statusCode(200);

        //get and verify the data
        given().
                when()
                .get("/products/A47")
                .then()
                .statusCode(200)
                .and()
                .body("productNumber", equalTo("A47"))
                .body("name", equalTo("TestProduct"))
                .body("numberInStock", equalTo(20))
                .body("price", equalTo(45f))
                .body("description", equalTo("testdescription"));

        //clean up the data
        given()
                .when()
                .delete("/products/A47")
                .then()
                .statusCode(200);
    }


    @Test
    public void testDecreaseProductInStock(){
        //add the data
        given()
                .when()
                .post("/products?productNumber=A47&name=TestProduct&price=45&numberInStock=10&description=testdescription&operation=addnew")
                .then()
                .statusCode(200);
        //increase the product in stock
        given()
                .when()
                .post("/products?productNumber=A47&numberInStock=8&operation=decreaseStock")
                .then()
                .statusCode(200);

        //get and verify the data
        given().
                when()
                .get("/products/A47")
                .then()
                .statusCode(200)
                .and()
                .body("productNumber", equalTo("A47"))
                .body("name", equalTo("TestProduct"))
                .body("numberInStock", equalTo(2))
                .body("price", equalTo(45f))
                .body("description", equalTo("testdescription"));

        //clean up the data
        given()
                .when()
                .delete("/products/A47")
                .then()
                .statusCode(200);
    }


    @Test
    public void testDeleteProduct(){
        //add the data
        given()
                .when()
                .post("/products?productNumber=A47&name=TestProduct&price=45&numberInStock=10&description=testdescription&operation=addnew")
                .then()
                .statusCode(200);
        //deletethe data
        given()
                .when()
                .delete("/products/A47")
                .then()
                .statusCode(200);

        //get and verify the data
        given().
                when()
                .get("/products/A47")
                .then()
                .statusCode(404)
                .and()
                .body("message", containsString("not found"));
    }


}
