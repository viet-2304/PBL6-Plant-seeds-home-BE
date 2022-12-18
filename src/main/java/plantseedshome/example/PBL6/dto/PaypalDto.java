package plantseedshome.example.PBL6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaypalDto {
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

}
