package cs.miu.edu.service;

import cs.miu.edu.dataaccess.ProductRepository;
import cs.miu.edu.domain.Product;
import cs.miu.edu.service.adapter.ProductAdapter;
import cs.miu.edu.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 12:16 PM
 * @project webstore
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO){
        Product savedProduct = productRepository.save(ProductAdapter.getProduct(productDTO));
        return ProductAdapter.getProductDTO(savedProduct);
    }

    @Override
    public Optional<ProductDTO> findProductByNumber(String productNumber){
        Optional<Product> product = productRepository.findById(productNumber);
        if(product.isPresent())
            return Optional.of(ProductAdapter.getProductDTO(product.get()));
        return Optional.empty();
    }

    @Override
    public ProductDTO removeProduct(String productNumber){
        Optional<Product> product = productRepository.findById(productNumber);
        productRepository.delete(product.get());
        return ProductAdapter.getProductDTO(product.get());
    }

    public ProductDTO increaseQuantity(String productNumber, Integer numOfAddedStock){
        Optional<Product> product = productRepository.findById(productNumber);
        product.get().setNumberInStock(product.get().getNumberInStock() + numOfAddedStock);
        return ProductAdapter.getProductDTO(productRepository.save(product.get()));
    }

    public ProductDTO decreaseQuantity(String productNumber, Integer numOfAddedStock){
        return this.increaseQuantity(productNumber, -1 * numOfAddedStock);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO){
        Product updatedProduct = productRepository.save(ProductAdapter.getProduct(productDTO));
        return ProductAdapter.getProductDTO(updatedProduct);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product-> ProductAdapter.getProductDTO(product))
                .collect(Collectors.toList());
    }
}
