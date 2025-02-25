package co.istad.backend.features.product;

import co.istad.backend.base.BaseResponse;
import co.istad.backend.features.product.dto.ProductRequest;
import co.istad.backend.features.product.dto.ProductResponse;
import co.istad.backend.features.product.dto.UpdateProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductResponse> getProducts(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "25") int size) {
        return productService.getProducts(page, size);

    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ProductResponse> getProduct(@PathVariable String uuid) {

        return BaseResponse.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(productService.getProduct(uuid))
                .message("Product has been found successfully.")
                .build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {

        return BaseResponse.<ProductResponse>builder()
                .status(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .data(productService.createProduct(productRequest))
                .message("Product has been created successfully.")
                .build();

    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ProductResponse> updateProduct(@PathVariable String uuid, @RequestBody UpdateProductRequest updateProductRequest)
    {
        return BaseResponse.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(productService.updateProduct(uuid,updateProductRequest))
                .message("Product has been updated successfully.")
                .build();
    }


    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse<ProductResponse> deleteProduct(@PathVariable String uuid) {

        productService.deleteProduct(uuid);

        return BaseResponse.<ProductResponse>builder()
                .status(HttpStatus.NO_CONTENT.value())
                .timestamp(LocalDateTime.now())
                .message("Product has been deleted successfully.")
                .build();
    }


}
