package cs.miu.edu.dataaccess;

import cs.miu.edu.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 12:03 PM
 * @project webstore
 */
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findById(String productNumber);
}
