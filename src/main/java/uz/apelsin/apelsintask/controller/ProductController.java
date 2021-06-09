package uz.apelsin.apelsintask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.service.ProductService;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/7/2021
*/
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Get all products
     * @return ResponseEntity<?>
     */
    @GetMapping("/list")
    public ResponseEntity<?> getAllProduct(){
        BaseResponse apiResponse = productService.getAllProduct();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    /**
     * Get product by product id
     * @param product_id Integer product_id
     * @return ResponseEntity<?>
     */
    @GetMapping("/details")
    public ResponseEntity<?> getProductById(@RequestParam Integer product_id){
        BaseResponse apiResponse = productService.getProductById(product_id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


}
