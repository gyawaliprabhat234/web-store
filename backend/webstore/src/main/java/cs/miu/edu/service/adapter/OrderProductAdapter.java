package cs.miu.edu.service.adapter;

import cs.miu.edu.domain.OrderedProduct;
import cs.miu.edu.service.dto.OrderedProductDTO;

/**
 * @author Prabhat Gyawali
 * @created 13-Jul-2021 - 2:25 PM
 * @project webstore
 */
public class OrderProductAdapter {
    public static OrderedProduct getOrderProduct(OrderedProductDTO orderedProductDTO){
        OrderedProduct orderedProduct = new OrderedProduct(ProductAdapter.getProduct(orderedProductDTO.getProduct()),
                orderedProductDTO.getQuantity());
        return orderedProduct;
    }

    public static OrderedProductDTO getOrderProductDTO(OrderedProduct orderedProduct){
        OrderedProductDTO orderedProductDTO = new OrderedProductDTO(ProductAdapter.getProductDTO(orderedProduct.getProduct()),
                orderedProduct.getQuantity());
        return orderedProductDTO;
    }
}
