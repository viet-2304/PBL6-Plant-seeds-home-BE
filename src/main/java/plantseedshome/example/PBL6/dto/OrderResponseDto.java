package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private String orderId;
    private String userId;
    private String userName;
    private String paymentMethod;
    private String orderStatus;
    private String address;
    private Date createDate;
    private Date updateDate;
    private int total;
}
