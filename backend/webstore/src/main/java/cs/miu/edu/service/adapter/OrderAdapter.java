package cs.miu.edu.service.adapter;

import cs.miu.edu.domain.Order;
import cs.miu.edu.service.dto.OrderDTO;

import java.util.stream.Collectors;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 10:25 PM
 * @project webstore
 */
public class OrderAdapter {
    public static Order getOrder(OrderDTO orderDTO){
        Order order = new Order(orderDTO.getOrderId(),
                UserAdapter.getUser(orderDTO.getOwner()),
                PaymentAdapter.getPayment(orderDTO.getPayment()),
                orderDTO.getOrderedProducts().stream().map(orderedProduct-> OrderProductAdapter.getOrderProduct(orderedProduct)).collect(Collectors.toList()),
                orderDTO.getStatus());
        return order;
    }
    public static OrderDTO getOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO(order.getOrderId(),
                UserAdapter.getUserDTO(order.getOwner()),
                PaymentAdapter.getPaymentDTO(order.getPayment()),
                order.getOrderedProducts().stream().map(product-> OrderProductAdapter.getOrderProductDTO(product)).collect(Collectors.toList()),
                order.getStatus());
        return orderDTO;
    }
}
