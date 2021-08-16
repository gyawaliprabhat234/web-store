package cs.miu.edu.web.controller;

import cs.miu.edu.service.ProductService;
import cs.miu.edu.service.dto.ProductDTO;
import cs.miu.edu.web.exceptionhandler.CustomResponseMessage;
import cs.miu.edu.web.exceptionhandler.CustomValidationException;
import cs.miu.edu.web.exceptionhandler.ResourceConflictException;
import cs.miu.edu.web.exceptionhandler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.util.Optional;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 11:59 AM
 * @project webstore
 */

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private Validator validator;

    @PostMapping
    public ResponseEntity<?> handlePost(@RequestParam("productNumber") String productNumber,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "price", required = false) Double price,
                                        @RequestParam(value = "numberInStock", required = false) Integer numberInStock,
                                        @RequestParam(value = "description", required = false) String description,
                                        @RequestParam("operation") String operation) throws ResourceConflictException, ResourceNotFoundException, CustomValidationException {

        String errorMessage = "";
        switch (operation) {
            case "addnew":
                if (productService.findProductByNumber(productNumber).isPresent())
                    throw new ResourceConflictException("Product with productNumber(" + productNumber + ")is already exist");
                return this.saveProduct(new ProductDTO(productNumber, name, price, description, numberInStock));

            case "increaseStock":
                this.findProductIfNotFoundThrowException(productNumber);
                if(numberInStock ==null || numberInStock < 1) {
                    errorMessage = "Added number of stock must be greater than 0";
                    break;
                }
                return new ResponseEntity<>(productService.increaseQuantity(productNumber, numberInStock), HttpStatus.OK);

            case "decreaseStock":
                ProductDTO productDTO = this.findProductIfNotFoundThrowException(productNumber);
                if(productDTO.getNumberInStock() < numberInStock){
                    errorMessage = "Not sufficient stock to decrease";
                    break;
                }
                return new ResponseEntity<>(productService.decreaseQuantity(productNumber, numberInStock), HttpStatus.OK);

            default:
                errorMessage = "Operation is not valid";
                break;

        }

        return new ResponseEntity<>(new CustomResponseMessage(errorMessage, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);


    }
    @DeleteMapping("/{productNumber}")
    public ResponseEntity<?> removeProduct(@PathVariable String productNumber) throws ResourceNotFoundException {
        this.findProductIfNotFoundThrowException(productNumber);
        ProductDTO deletedProduct = productService.removeProduct(productNumber);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllProducts() throws ResourceNotFoundException {
        Products products = new Products(productService.findAllProducts());
        if (products.getProducts().isEmpty())
            throw new ResourceNotFoundException("No products found");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{productNumber}")
    public ResponseEntity<?> updateProductDetails(@PathVariable String productNumber, @RequestBody ProductDTO productDTO) throws ResourceNotFoundException, CustomValidationException {
        this.findProductIfNotFoundThrowException(productNumber);
        productDTO.setProductNumber(productNumber);
        return this.saveProduct(productDTO);
    }

    @GetMapping("/{productNumber}")
    public ResponseEntity<?> findProductById(@PathVariable String productNumber) throws ResourceNotFoundException {
        ProductDTO product = this.findProductIfNotFoundThrowException(productNumber);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private ProductDTO findProductIfNotFoundThrowException(String productNumber) throws ResourceNotFoundException {
        Optional<ProductDTO> product = productService.findProductByNumber(productNumber);
        if (product.isEmpty())
            throw new ResourceNotFoundException("Product with productNumber(" + productNumber + ") is not found");
        return product.get();

    }

    private ResponseEntity<?> saveProduct(ProductDTO productDTO) throws CustomValidationException {
        CustomValidationException.ifNotValidThrowValidationException(validator, productDTO);
        ProductDTO product = productService.saveProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
