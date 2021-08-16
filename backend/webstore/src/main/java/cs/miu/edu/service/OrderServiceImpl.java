package cs.miu.edu.service;

import cs.miu.edu.dataaccess.OrderRepository;
import cs.miu.edu.dataaccess.ProductRepository;
import cs.miu.edu.domain.Order;
import cs.miu.edu.domain.OrderStatus;
import cs.miu.edu.domain.OrderedProduct;
import cs.miu.edu.domain.Product;
import cs.miu.edu.service.adapter.OrderAdapter;
import cs.miu.edu.service.adapter.OrderProductAdapter;
import cs.miu.edu.service.adapter.ProductAdapter;
import cs.miu.edu.service.dto.OrderDTO;
import cs.miu.edu.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 10:21 PM
 * @project webstore
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    @Override
    public List<OrderDTO> findAllOrder() {
        return orderRepository.findAll().stream()
                .map(order -> OrderAdapter.getOrderDTO(order))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> finById(Integer orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent())
            return Optional.of(OrderAdapter.getOrderDTO(order.get()));
        return Optional.empty();
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = OrderAdapter.getOrder(orderDTO);
        Integer orderId = nextSequenceService.getNextSequence("customSequences");
        order.setOrderId(orderId);
        order.setStatus(OrderStatus.PLACED);
        orderDTO.getOrderedProducts().forEach(orderedProductDTO ->
        {
            Product toBeUpdatedProduct = ProductAdapter.getProduct(orderedProductDTO.getProduct());
            toBeUpdatedProduct.setNumberInStock(toBeUpdatedProduct.getNumberInStock() - orderedProductDTO.getQuantity());
            productRepository.save(toBeUpdatedProduct);
        });
        OrderDTO savedOrder = OrderAdapter.getOrderDTO(orderRepository.save(order));
        return savedOrder;
    }

    @Override
    public boolean changeStatus(Integer orderId, String orderStatus) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.get().setStatus(OrderStatus.getOrderStatus(orderStatus));
        return orderRepository.save(order.get()) != null;
    }

    @Override
    public OrderDTO deleteOrder(Integer orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        orderRepository.delete(order.get());
        return OrderAdapter.getOrderDTO(order.get());
    }
}
