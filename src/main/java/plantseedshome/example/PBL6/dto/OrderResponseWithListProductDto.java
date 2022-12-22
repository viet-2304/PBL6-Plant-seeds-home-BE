package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseWithListProductDto {
    private OrderResponseDto orderResponseDto;
    private List<ProductResponseWithOrderDto> listProduct;
}
