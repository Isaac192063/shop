package com.primer_parcial.shop.serviceImp;

import com.primer_parcial.shop.exceptions.AlreadyExistsException;
import com.primer_parcial.shop.exceptions.NotFoundException;
import com.primer_parcial.shop.model.Category;
import com.primer_parcial.shop.model.enums.ErrorMessage;
import com.primer_parcial.shop.repository.CategoryRepository;
import com.primer_parcial.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        Optional<Category> categoryFindByName = categoryRepository.findByName(category.getName());
        if(categoryFindByName.isPresent()){
            throw new AlreadyExistsException(ErrorMessage.CATEGORY_NAME_EXISTS.getMessage());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
        }
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id,Category newCategory) {
        Optional<Category> candidateCategory = categoryRepository.findById(id);

        if(candidateCategory.isEmpty()){
            throw new NotFoundException(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
        }

        Optional<Category> categoryFindByName =
                categoryRepository.findByNameAndIdNot(newCategory.getName(), id);

        if(categoryFindByName.isPresent()){
            throw new AlreadyExistsException(ErrorMessage.CATEGORY_NAME_EXISTS.getMessage());
        }

        Category category = candidateCategory.get();

        category.setName(newCategory.getName());
        category.setDescription(newCategory.getDescription());
        return  categoryRepository.save(category);
    }
}
