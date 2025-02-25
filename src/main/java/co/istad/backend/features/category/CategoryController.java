package co.istad.backend.features.category;

import co.istad.backend.base.BaseResponse;
import co.istad.backend.features.category.dto.CategoryRequest;
import co.istad.backend.features.category.dto.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor

public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryResponse> getCategories(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "25") int size) {

        return categoryService.getCategories(page, size);

    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CategoryResponse> getCategory(@PathVariable String uuid) {

       return BaseResponse.<CategoryResponse>builder()
                .status(HttpStatus.OK.value())
               .timestamp(LocalDateTime.now())
                .message("Category retrieved successfully")
                .data(categoryService.getCategory(uuid))
                .build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CategoryResponse> createCategory(@Valid  @RequestBody CategoryRequest request) {

        return BaseResponse.<CategoryResponse>builder()
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .message("Category created successfully")
                .data(categoryService.createCategory(request.name()))
                .build();

    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CategoryResponse> updateCategory(@PathVariable String uuid, @RequestBody CategoryRequest request) {

        return BaseResponse.<CategoryResponse>builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Category updated successfully")
                .data(categoryService.updateCategory(uuid, request.name()))
                .build();

    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse<Void> deleteCategory(@PathVariable String uuid) {

        categoryService.deleteCategory(uuid);

        return BaseResponse.<Void>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .timestamp(LocalDateTime.now())
                .message("Category deleted successfully")
                .build();

    }




}
