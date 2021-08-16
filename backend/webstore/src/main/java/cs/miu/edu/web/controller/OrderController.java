package cs.miu.edu.web.controller;

import cs.miu.edu.domain.Order;
import cs.miu.edu.domain.OrderedProduct;
import cs.miu.edu.domain.Product;
import cs.miu.edu.service.OrderService;
import cs.miu.edu.service.ProductService;
import cs.miu.edu.service.dto.OrderDTO;
import cs.miu.edu.service.dto.OrderedProductDTO;
import cs.miu.edu.service.dto.ProductDTO;
import cs.miu.edu.web.exceptionhandler.CustomResponseMessage;
import cs.miu.edu.web.exceptionhandler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 4:17 PM
 * @project webstore
 */

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody @Valid OrderDTO orderDTO) throws ResourceNotFoundException {
        List<OrderedProductDTO> verifiedProductInOrder = verifyProductIfNotValidThrowException(orderDTO.getOrderedProducts());
        orderDTO.setOrderedProducts(verifiedProductInOrder);
        OrderDTO placedOrder = orderService.saveOrder(orderDTO);
        return new ResponseEntity<>(placedOrder, HttpStatus.OK);
    }

    private List<OrderedProductDTO>  verifyProductIfNotValidThrowException(List<OrderedProductDTO> orderedProducts) throws ResourceNotFoundException {
        List<OrderedProductDTO> orderedProductDTOS = new ArrayList<>();
        for(OrderedProductDTO orderedProduct: orderedProducts) {
            Optional<ProductDTO> product = productService.findProductByNumber(orderedProduct.getProduct().getProductNumber());
            if (product.isEmpty())
                throw new ResourceNotFoundException("Order cannot placed product("+orderedProduct.getProduct().getProductNumber()+") does not exist");
            Integer remainingStock = product.get().getNumberInStock() - orderedProduct.getQuantity();
            if (remainingStock < 0)
                throw new ResourceNotFoundException("Not sufficient product("+product.get().getProductNumber()+") in stock. You can order maximum of "+product.get().getNumberInStock()+" products");
            orderedProductDTOS.add(new OrderedProductDTO(product.get(), orderedProduct.getQuantity()));
        }
        return orderedProductDTOS;
    }

    @GetMapping
    public ResponseEntity<?> findAllOrders() throws ResourceNotFoundException {
        List<OrderDTO> orderList = orderService.findAllOrder();
        if (orderList.isEmpty()) throw new ResourceNotFoundException("No Records Found");
        return new ResponseEntity<>(new Orders(orderList), HttpStatus.OK);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Integer orderId,
                                               @RequestParam("status") String status) throws ResourceNotFoundException {
        CustomResponseMessage message;
        this.findOrderIfNotFoundThrowException(orderId);
        if (orderService.changeStatus(orderId, status))
            message = new CustomResponseMessage("Status changed", HttpStatus.OK);
        else message = new CustomResponseMessage("Operation Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(message, message.getStatus());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findOrderById(@PathVariable Integer orderId) throws ResourceNotFoundException {
        OrderDTO orderDTO = this.findOrderIfNotFoundThrowException(orderId);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer orderId) throws ResourceNotFoundException {
        OrderDTO orderDTO = this.findOrderIfNotFoundThrowException(orderId);
        OrderDTO deletedOrder = this.orderService.deleteOrder(orderId);
        return new ResponseEntity<>(deletedOrder, HttpStatus.OK);
    }

    private OrderDTO findOrderIfNotFoundThrowException(Integer orderId) throws ResourceNotFoundException {
        Optional<OrderDTO> orderDTO = orderService.finById(orderId);
        if (orderDTO.isEmpty())
            throw new ResourceNotFoundException("Order with orderId(" + orderId + ") is not found");
        return orderDTO.get();
    }
}
