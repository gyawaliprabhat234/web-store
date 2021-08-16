package cs.miu.edu.service.adapter;

import cs.miu.edu.domain.Product;
import cs.miu.edu.service.dto.ProductDTO;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 12:10 PM
 * @project webstore
 */
public  class ProductAdapter {
    public static ProductDTO getProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO(product.getProductNumber(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getNumberInStock());
        return productDTO;

    }
    public static Product getProduct(ProductDTO productDTO){
        Product product = new Product(productDTO.getProductNumber(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getDescription(),
                productDTO.getNumberInStock());
        return product;
    }
}
