package cs.miu.edu.service;

import cs.miu.edu.domain.Order;
import cs.miu.edu.service.dto.OrderDTO;
import java.util.List;
import java.util.Optional;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 9:38 PM
 * @project webstore
 */
public interface OrderService {
    List<OrderDTO> findAllOrder();
    Optional<OrderDTO> finById(Integer orderId);
    OrderDTO saveOrder(OrderDTO orderDTO);
    boolean changeStatus(Integer orderId, String orderStatus);
    OrderDTO deleteOrder(Integer orderId);
}
