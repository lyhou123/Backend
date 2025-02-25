package co.istad.backend.features.category;

import co.istad.backend.features.category.dto.CategoryResponse;
import org.springframework.data.domain.Page;

/**
 * Service for category related operations.
 * @author lyhou
 * @version 1.0
 * @see CategoryServiceImpl
 */
public interface CategoryService {

    CategoryResponse createCategory(String name);

    CategoryResponse getCategory(String uuid);

    Page<CategoryResponse> getCategories(int page, int size);

    CategoryResponse updateCategory(String uuid, String name);

    void deleteCategory(String uuid);

}
