package co.istad.backend.mapper;

import co.istad.backend.domain.Category;
import co.istad.backend.features.category.dto.CategoryRequest;
import co.istad.backend.features.category.dto.CategoryResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category mapToCategory(CategoryRequest request);

    CategoryResponse mapToCategoryResponse(Category category);

}
