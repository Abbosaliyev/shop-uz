package uz.apelsin.apelsintask.model.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.apelsin.apelsintask.entity.DetailEntity;

import java.time.LocalDateTime;
import java.util.List;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/7/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsReceiveModel {

    private Integer orderId;
    private Integer customerId;
    private LocalDateTime date;
    private List<DetailEntity> details;

}
