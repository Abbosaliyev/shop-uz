package uz.apelsin.apelsintask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.apelsin.apelsintask.entity.InvoiceEntity;
import uz.apelsin.apelsintask.entity.PaymentEntity;
import uz.apelsin.apelsintask.model.receive.PaymentReceiveModel;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.repository.InvoiceRepository;
import uz.apelsin.apelsintask.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/8/2021
*/
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public BaseResponse makePayment(PaymentReceiveModel paymentReceiveModel) {
        Optional<InvoiceEntity> optionalInvoice = invoiceRepository.findById(paymentReceiveModel.getInvoiceId());
        if (optionalInvoice.isPresent()) {
            InvoiceEntity invoiceEntity = optionalInvoice.get();

            PaymentEntity paymentEntity = new PaymentEntity();
            paymentEntity.setAmount(invoiceEntity.getAmount());
            paymentEntity.setTime(LocalDateTime.now());
            paymentEntity.setInv(invoiceEntity);
            paymentEntity = paymentRepository.save(paymentEntity);
            return new BaseResponse(true, "Payment details", paymentEntity);
        }
        return new BaseResponse(false, "Invoice with the specified ID was not found", null);

    }

    public BaseResponse getPaymentDetailsById(Integer id) {
        Optional<PaymentEntity> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.map(paymentEntity -> new BaseResponse(true, "Payment details", paymentEntity)).orElseGet(() -> new BaseResponse(false, "Payment with the specified ID was not found", null));

    }


}
