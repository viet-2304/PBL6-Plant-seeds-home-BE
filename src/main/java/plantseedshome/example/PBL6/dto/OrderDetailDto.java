package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private String orderDetailId;
    private Date createDate;
    private Date updateDate;
    private String paymentMethod;
    private String orderStatus;
    private String address;
}
