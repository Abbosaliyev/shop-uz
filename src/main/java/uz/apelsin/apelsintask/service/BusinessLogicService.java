package uz.apelsin.apelsintask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.apelsin.apelsintask.entity.CustomerEntity;
import uz.apelsin.apelsintask.entity.InvoiceEntity;
import uz.apelsin.apelsintask.entity.OrderEntity;
import uz.apelsin.apelsintask.model.receive.*;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.repository.CustomerRepository;
import uz.apelsin.apelsintask.repository.DetailRepository;
import uz.apelsin.apelsintask.repository.InvoiceRepository;
import uz.apelsin.apelsintask.repository.OrderRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/9/2021
*/
@Service
public class BusinessLogicService {

    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final DetailRepository detailRepository;

    @Autowired
    public BusinessLogicService(InvoiceRepository invoiceRepository,
                                OrderRepository orderRepository,
                                CustomerRepository customerRepository,
                                DetailRepository detailRepository) {
        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.detailRepository = detailRepository;
    }


    public BaseResponse getExpiredInvoices() {
        List<InvoiceEntity> expiredInvoices = invoiceRepository.getExpiredInvoices();
        if (expiredInvoices.isEmpty()) {
            return new BaseResponse(false, "Not found expired invoices", null);
        }
        return new BaseResponse(true, "Expired invoice list", expiredInvoices);

    }

    public BaseResponse getWrongDateInvoices() {
        List<String[]> wrongDateInvoices = invoiceRepository.getWrongDateInvoices();
        if (wrongDateInvoices.isEmpty())
            return new BaseResponse(false, "Wrong date invoices not found.", null);
        List<WrongDateInvoicesReceiveModel> wrongDateInvoicesReceiveModelList = new ArrayList<>();
        wrongDateInvoices.forEach(strings -> wrongDateInvoicesReceiveModelList.add(new WrongDateInvoicesReceiveModel(Integer.parseInt(strings[0]), Timestamp.valueOf(strings[1]).toLocalDateTime(), Integer.parseInt(strings[2]), Timestamp.valueOf(strings[3]).toLocalDateTime())));
        return new BaseResponse(true, "Wrong date invoices.", wrongDateInvoicesReceiveModelList);
    }

    public BaseResponse getOrdersWithoutDetails() {
        LocalDateTime date = LocalDateTime.parse("2016-09-06T00:00:00");
        List<OrderEntity> ordersWithoutDetails = orderRepository.getOrdersWithoutDetails(date);
        if (ordersWithoutDetails.isEmpty())
            return new BaseResponse(false, "Orders without details not found.", null);
        return new BaseResponse(true, "Orders without details.", ordersWithoutDetails);

    }

    public BaseResponse getCustomersAndCustomersLastOrders() {
        List<String[]> customersList = customerRepository.getCustomersAndCustomersLastOrders();
        if (customersList.isEmpty())
            return new BaseResponse(true, "No customers found with the order", null);
        List<CustomerLastOrderReceiveModel> customers = new ArrayList<>();
        customersList.forEach(strings -> customers.add(new CustomerLastOrderReceiveModel(Integer.parseInt(strings[0]), strings[1], Timestamp.valueOf(strings[2]).toLocalDateTime())));
        return new BaseResponse(true, "Customers with order and last order date.", customers);
    }

    public BaseResponse getCustomersWithoutOrders() {
        List<CustomerEntity> customerList = customerRepository.getCustomersWithoutOrders();
        if (customerList.isEmpty())
            return new BaseResponse(false, "Customers without orders in 2016year not found.", null);
        return new BaseResponse(true, "Customers without orders in 2016year.", customerList);
    }

    public BaseResponse getOverpaidInvoices() {
        List<String[]> overpaidInvoices = invoiceRepository.getOverpaidInvoices();
        if (overpaidInvoices.isEmpty())
            return new BaseResponse(false, "Overpaid invoices not found.", null);
        List<OverpaidInvoiceReceiveModel> overpaidInvoiceReceiveModelList = new ArrayList<>();
        overpaidInvoices.forEach(strings -> overpaidInvoiceReceiveModelList.add(new OverpaidInvoiceReceiveModel(Integer.parseInt(strings[0]), Double.parseDouble(strings[1]))));
        return new BaseResponse(true, "Overpaid invoices.", overpaidInvoiceReceiveModelList);
    }

    public BaseResponse getHighDemandProducts() {
        List<String[]> highDemandProducts = detailRepository.getHighDemandProducts();
        if (highDemandProducts.isEmpty())
            return new BaseResponse(false, "Products ordered more than 10 times were not found.", null);
        List<HighDemandProductsReceiveModel> highDemandProductsReceiveModelList = new ArrayList<>();
        highDemandProducts.forEach(strings -> highDemandProductsReceiveModelList.add(new HighDemandProductsReceiveModel(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]))));
        return new BaseResponse(true, "High demand products.", highDemandProductsReceiveModelList);
    }

    public BaseResponse getBulkProducts() {
        List<String[]> bulkProducts = detailRepository.getBulkProducts();
        if (bulkProducts.isEmpty())
            return new BaseResponse(false, "Products normally ordered in bulk have not been found.", null);
        List<BulkProductsReceiveModel> bulkProductsReceiveModelList = new ArrayList<>();
        bulkProducts.forEach(strings -> bulkProductsReceiveModelList.add(new BulkProductsReceiveModel(Integer.parseInt(strings[0]), Double.parseDouble(strings[1]))));
        return new BaseResponse(true, "Bulk products.", bulkProductsReceiveModelList);
    }

    public BaseResponse getNumberOfProductsInYear() {
        List<String[]> numberOfProductsInYear = orderRepository.getNumberOfProductsInYear();
        if (numberOfProductsInYear.isEmpty())
            return new BaseResponse(false, "In 2016, the product was not sold.", null);
        List<NumberOfProductsInYearReceiveModel> numberOfProductsInYearReceiveModelList = new ArrayList<>();
        numberOfProductsInYear.forEach(strings -> numberOfProductsInYearReceiveModelList.add(new NumberOfProductsInYearReceiveModel(strings[0], Integer.parseInt(strings[1]))));
        return new BaseResponse(true, "Country and number of products sold in 2016.", numberOfProductsInYearReceiveModelList);
    }

    public BaseResponse getOrdersWithoutInvoices() {
        List<String[]> ordersWithoutInvoices = orderRepository.getOrdersWithoutInvoices();
        if (ordersWithoutInvoices.isEmpty())
            return new BaseResponse(false, "Orders without invoice not found.", null);
        List<OrdersWithoutInvoicesReceiveModel> ordersWithoutInvoicesReceiveModelList = new ArrayList<>();
        ordersWithoutInvoices.forEach(strings -> ordersWithoutInvoicesReceiveModelList.add(new OrdersWithoutInvoicesReceiveModel(Integer.parseInt(strings[0]), Timestamp.valueOf(strings[1]).toLocalDateTime(), Double.parseDouble(strings[2]))));
        return new BaseResponse(true, "Orders without invoice.", ordersWithoutInvoicesReceiveModelList);

    }

}
