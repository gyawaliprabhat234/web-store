package cs.miu.edu.web.controller;

import cs.miu.edu.service.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 6:07 PM
 * @project webstore
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private List<OrderDTO> orders;
}
