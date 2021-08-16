package cs.miu.edu.service;

import cs.miu.edu.service.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 12:05 PM
 * @project webstore
 */
public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);
    Optional<ProductDTO> findProductByNumber(String productNumber);
    ProductDTO removeProduct(String productNumber);
    ProductDTO updateProduct(ProductDTO productDTO);
    List<ProductDTO> findAllProducts();
    ProductDTO increaseQuantity(String productNumber, Integer numOfAddedStock);
    ProductDTO decreaseQuantity(String productNumber, Integer numOfAddedStock);
}
