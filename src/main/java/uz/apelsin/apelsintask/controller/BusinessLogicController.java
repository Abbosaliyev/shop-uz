package uz.apelsin.apelsintask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.service.BusinessLogicService;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/9/2021
*/
@RestController
@RequestMapping("/api/business")
public class BusinessLogicController {

    private final BusinessLogicService businessLogicService;

    @Autowired
    public BusinessLogicController(BusinessLogicService businessLogicService) {
        this.businessLogicService = businessLogicService;
    }

    /**
     * 1.Invoices issued after their due date. Return all attributes.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/expired_invoices")
    public ResponseEntity<?> getExpiredInvoices() {
        BaseResponse apiResponse = businessLogicService.getExpiredInvoices();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);

    }

    /**
     * 2.Invoices that were issued before the date in which the order they refer to was placed.
     * Return the ID of the invoice, the date it was issued, the ID of the order associated with it
     * and the date the order was placed.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/wrong_date_invoices")
    public ResponseEntity<?> getWrongDateInvoices() {
        BaseResponse apiResponse = businessLogicService.getWrongDateInvoices();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * 3.Orders that do not have a detail and were placed before 6 September 2016. Return all attributes.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/orders_without_details")
    public ResponseEntity<?> getOrdersWithoutDetails() {
        BaseResponse apiResponse = businessLogicService.getOrdersWithoutDetails();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * 4.Customers who have not placed any orders in 2016. Return all attributes.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/customers_without_orders")
    public ResponseEntity<?> getCustomersWithoutOrders() {
        BaseResponse apiResponse = businessLogicService.getCustomersWithoutOrders();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * 5.ID and name of customers and the date of their last order. For customers who did not
     * place any orders, no rows must be returned. For each customer who placed more than
     * one order on the date of their most recent order, only one row must be returned.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/customers_last_orders")
    public ResponseEntity<?> getCustomersAndCustomersLastOrders() {
        BaseResponse apiResponse = businessLogicService.getCustomersAndCustomersLastOrders();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * 6.Invoices that have been overpaid. Observe that there may be more than one payment referring
     * to the same invoice. Return the invoice number and the amount that should be reimbursed.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/overpaid_invoices")
    public ResponseEntity<?> getOverpaidInvoices() {
        BaseResponse apiResponse = businessLogicService.getOverpaidInvoices();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * 7.Products that were ordered more than 10 times in total, by taking into account the
     * quantities in which they appear in the order details. Return the product code and the
     * total number of times it was ordered.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/high_demand_products")
    public ResponseEntity<?> getHighDemandProducts() {
        BaseResponse apiResponse = businessLogicService.getHighDemandProducts();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * 8.Products that are usually ordered in bulk: whenever one of these products is ordered,
     * it is ordered in a quantity that on average is equal to or greater than 8.
     * For each such product, return product code and price.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/bulk_products")
    public ResponseEntity<?> getBulkProducts() {
        BaseResponse apiResponse = businessLogicService.getBulkProducts();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * 9.Total number of orders placed in 2016 by customers of each country. If all customers from a
     * specific country did not place any orders in 2016, the country will not appear in the output.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/number_of_products_in_year")
    public ResponseEntity<?> getNumberOfProductsInYear() {
        BaseResponse apiResponse = businessLogicService.getNumberOfProductsInYear();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * 10.For each order without invoice, list its ID, the date it was placed and the total price of the
     * products in its detail, taking into account the quantity of each ordered product and its unit
     * price. Orders without detail must not be included in the answers.
     *
     * @return ResponseEntity<?>
     */
    @GetMapping("/orders_without_invoices")
    public ResponseEntity<?> getOrdersWithoutInvoices() {
        BaseResponse apiResponse = businessLogicService.getOrdersWithoutInvoices();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


}
