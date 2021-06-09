package uz.apelsin.apelsintask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.apelsin.apelsintask.entity.*;
import uz.apelsin.apelsintask.model.receive.InvoiceReceiveModel;
import uz.apelsin.apelsintask.model.receive.OrderDetailsReceiveModel;
import uz.apelsin.apelsintask.model.receive.OrderReceiveModel;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.properties.ProjectProperties;
import uz.apelsin.apelsintask.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/7/2021
*/
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DetailRepository detailRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository,
                        DetailRepository detailRepository,
                        CustomerRepository customerRepository,
                        ProductRepository productRepository,
                        InvoiceRepository invoiceRepository) {
        this.orderRepository = orderRepository;
        this.detailRepository = detailRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public BaseResponse getOrderDetailById(Integer order_id) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(order_id);
        if (optionalOrder.isPresent()) {
            OrderEntity orderEntity = optionalOrder.get();
            List<DetailEntity> detailList = detailRepository.findAllByOrd_Id(order_id);
            OrderDetailsReceiveModel orderDetailsReceiveModel = new OrderDetailsReceiveModel();
            orderDetailsReceiveModel.setOrderId(orderEntity.getId());
            orderDetailsReceiveModel.setCustomerId(orderEntity.getCust().getId());
            orderDetailsReceiveModel.setDate(orderEntity.getDate());
            orderDetailsReceiveModel.setDetails(detailList);
            return new BaseResponse(true, "Order details.", orderDetailsReceiveModel);
        }
        return new BaseResponse(false, "Order with the specified ID was not found.", null);

    }

    public BaseResponse makeOrder(OrderReceiveModel orderReceiveModel) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(orderReceiveModel.getCustomerId());
        if (!optionalCustomer.isPresent()) {
            return new BaseResponse(false, "Customer with the specified ID was not found", null);

        }

        Optional<ProductEntity> optionalProduct = productRepository.findById(orderReceiveModel.getProductId());
        if (!optionalProduct.isPresent()) {
            return new BaseResponse(false, "Product with the specified ID was not found", null);
        }

        ProductEntity product = optionalProduct.get();


        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(LocalDateTime.now());
        orderEntity.setCust(optionalCustomer.get());
        orderEntity = orderRepository.save(orderEntity);

        DetailEntity detailEntity = new DetailEntity();
        detailEntity.setOrd(orderEntity);
        detailEntity.setPr(product);
        detailEntity.setQuantity(orderReceiveModel.getQuantity());
        detailEntity = detailRepository.save(detailEntity);

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setOrd(orderEntity);
        invoiceEntity.setAmount(product.getPrice().multiply(BigDecimal.valueOf(detailEntity.getQuantity())));
        invoiceEntity.setIssued(LocalDateTime.now());
        invoiceEntity.setDue(LocalDateTime.now().plusHours(ProjectProperties.TIME_OF_INVOICE_IN_HOURS));
        invoiceEntity = invoiceRepository.save(invoiceEntity);
        InvoiceReceiveModel invoiceReceiveModel = new InvoiceReceiveModel(invoiceEntity.getId());
        return new BaseResponse(true, "Order, Details and Invoice created", invoiceReceiveModel);

    }

}
