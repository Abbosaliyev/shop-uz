package uz.apelsin.apelsintask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.apelsin.apelsintask.entity.ProductEntity;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/7/2021
*/
@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get all products.
     *
     * @return BaseResponse
     */
    public BaseResponse getAllProduct() {
        List<ProductEntity> productList = productRepository.findAll();
        if (productList.isEmpty())
            return new BaseResponse(false, "Product list is empty", null);
        return new BaseResponse(true, "Product list", productList);
    }


    /**
     * Get product by product id.
     *
     * @param product_id Integer product_id
     * @return BaseResponse
     */
    public BaseResponse getProductById(Integer product_id) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(product_id);
        return optionalProduct.map(product -> new BaseResponse(true, "Product details.", product)).orElseGet(() -> new BaseResponse(false, "Product with the specified ID was not found.", null));
    }

}
