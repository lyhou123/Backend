package co.istad.backend.features.category;

import co.istad.backend.domain.Category;
import co.istad.backend.features.category.dto.CategoryResponse;
import co.istad.backend.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponse createCategory(String name) {

        if (categoryRepository.existsByName(name))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already exists");
        }

        Category category = new Category();
        category.setUuid(UUID.randomUUID().toString());
        category.setName(name);

        return categoryMapper.mapToCategoryResponse(categoryRepository.save(category));

    }

    @Override
    public CategoryResponse getCategory(String uuid) {

        if (uuid.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID must not be empty");
        }

        Category category = categoryRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> getCategories(int page, int size) {

        if (page < 0 && size <0)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page and size must be greater than 0");
        }

        Sort sort = Sort.by(Sort.Direction.ASC, "createdAt");

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return categoryRepository.findAll(pageRequest).map(categoryMapper::mapToCategoryResponse);
    }

    @Override
    public CategoryResponse updateCategory(String uuid, String name) {

        if (uuid.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID must not be empty");
        }

        Category category = categoryRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        category.setName(name);

        categoryRepository.save(category);

        return categoryMapper.mapToCategoryResponse(category);
    }

    @Override
    public void deleteCategory(String uuid) {

        if (uuid.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID must not be empty");
        }

        Category category = categoryRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categoryRepository.delete(category);

    }
}
