package uz.apelsin.apelsintask.model.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/7/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WrongDateInvoicesReceiveModel {

    private Integer invoiceId;
    private LocalDateTime invoiceIssued;
    private Integer orderId;
    private LocalDateTime orderDate;
}
