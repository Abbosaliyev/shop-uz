package uz.apelsin.apelsintask.model.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/7/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OverpaidInvoiceReceiveModel {
    private Integer invoiceId;
    private Double repaidSumma;

}
