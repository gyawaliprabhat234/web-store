package cs.miu.edu.web.controller;

import cs.miu.edu.service.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 1:52 PM
 * @project webstore
 */
@Data
@AllArgsConstructor
public class Products {
    private List<ProductDTO> products;
}
