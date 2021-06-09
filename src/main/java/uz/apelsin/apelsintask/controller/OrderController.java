package uz.apelsin.apelsintask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.apelsin.apelsintask.model.receive.OrderReceiveModel;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.service.OrderService;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/7/2021
*/
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Get order by order id.
     * @param order_id Integer order_id
     * @return ResponseEntity<?>
     */
    @GetMapping("/details")
    public ResponseEntity<?> getOrderDetailsById(@RequestParam Integer order_id){
        BaseResponse apiResponse = orderService.getOrderDetailById(order_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Make Order, Details and Invoice.
     * @param orderReceiveModel OrderDto orderDto
     * @return ResponseEntity<?>
     */
    @PostMapping
    public ResponseEntity<BaseResponse> makeOrder(@RequestBody OrderReceiveModel orderReceiveModel){
        BaseResponse apiResponse = orderService.makeOrder(orderReceiveModel);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
