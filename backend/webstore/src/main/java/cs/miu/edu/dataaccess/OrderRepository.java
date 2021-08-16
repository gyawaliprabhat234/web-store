package cs.miu.edu.dataaccess;

import cs.miu.edu.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 9:26 PM
 * @project webstore
 */
public interface OrderRepository extends MongoRepository<Order, Integer> {
}
