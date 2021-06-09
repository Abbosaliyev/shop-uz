package uz.apelsin.apelsintask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.apelsin.apelsintask.model.receive.PaymentReceiveModel;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.service.PaymentService;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/8/2021
*/
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Get payment details by payment id.
     *
     * @param id Integer id
     * @return ResponseEntity<?>
     */

    @GetMapping("/details")
    public ResponseEntity<?> getPaymentDetailsById(@RequestParam Integer id) {
        BaseResponse baseResponse = paymentService.getPaymentDetailsById(id);
        return ResponseEntity.status(baseResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(baseResponse);

    }

    /**
     * Make Payment by Invoice id
     *
     * @param paymentReceiveModel PaymentDto paymentDto
     * @return ResponseEntity<?>
     */
    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody PaymentReceiveModel paymentReceiveModel) {
        BaseResponse baseResponse = paymentService.makePayment(paymentReceiveModel);
        return ResponseEntity.status(baseResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(baseResponse);
    }

}
