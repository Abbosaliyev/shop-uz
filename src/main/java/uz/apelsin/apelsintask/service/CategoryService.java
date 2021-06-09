package uz.apelsin.apelsintask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.apelsin.apelsintask.entity.CategoryEntity;
import uz.apelsin.apelsintask.model.response.base.BaseResponse;
import uz.apelsin.apelsintask.repository.CategoryRepository;
import uz.apelsin.apelsintask.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/8/2021
*/
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public BaseResponse getAllCategory() {
        List<CategoryEntity> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            return new BaseResponse(false, "Category list is empty", null);
        }
        return new BaseResponse(true, "Category list", categoryList);
    }

    public BaseResponse getCategoryByProductId(Integer product_id) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findByProductId(product_id);
        return optionalCategory.map(categoryEntity -> new BaseResponse(true, "Category details.", categoryEntity)).orElseGet(() -> new BaseResponse(false, "Product with the specified ID was not found.", null));

    }


}
