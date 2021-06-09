package uz.apelsin.apelsintask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.service.CategoryService;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/8/2021
*/
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Get all categories.
     * @return ResponseEntity<?>
     */

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategory() {
        BaseResponse baseResponse = categoryService.getAllCategory();
        return ResponseEntity.status(baseResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(baseResponse);
    }

    /**
     * Get category by product id.
     * @param product_Id Integer product_id
     * @return ResponseEntity<?>
     */

    @GetMapping
    public ResponseEntity<?> getCategory(@RequestParam Integer product_Id) {
        BaseResponse baseResponse = categoryService.getCategoryByProductId(product_Id);
        return ResponseEntity.status(baseResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(baseResponse);

    }


}
