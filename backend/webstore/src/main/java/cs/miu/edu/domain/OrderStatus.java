package cs.miu.edu.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 9:16 PM
 * @project webstore
 */
public enum OrderStatus {
    PLACED, SHIPPED, DELIVERED;

    public static OrderStatus getOrderStatus(String orderStatus){
        return Arrays.stream(OrderStatus.values())
                .filter(x-> x.toString().equals(orderStatus))
                .findAny().get();
    }
    public static List<String> getAllStatus(){
        return Arrays.stream(OrderStatus.values()).map(OrderStatus::toString).collect(Collectors.toList());
    }
}
