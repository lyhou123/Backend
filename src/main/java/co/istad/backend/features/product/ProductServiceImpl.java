package co.istad.backend.features.product;

import co.istad.backend.domain.Category;
import co.istad.backend.domain.Product;
import co.istad.backend.domain.User;
import co.istad.backend.features.category.CategoryRepository;
import co.istad.backend.features.product.dto.ProductRequest;
import co.istad.backend.features.product.dto.ProductResponse;
import co.istad.backend.features.product.dto.UpdateProductRequest;
import co.istad.backend.features.user.UserRepository;
import co.istad.backend.mapper.ProductMapper;
import co.istad.backend.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    @Override
    public ProductResponse createProduct(@AuthenticationPrincipal CustomUserDetails customUserDetails, ProductRequest request) {

        String uuid = customUserDetails.getUserUuid();

        User user = userRepository.findUserByUuid(uuid);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Product product = new Product();
        product.setUuid(UUID.randomUUID().toString());
        product.setTitle(request.title());
        product.setDescription(request.description());
        product.setPrice(Float.valueOf(request.price()));

        Category category = categoryRepository.findByName(request.categoryName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        product.setCategory(category);
        product.setUser(user);
        product.setThumbnail(request.thumbnail());

        return productMapper.mapToProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(String uuid, UpdateProductRequest updateProductRequest) {

        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));


        productMapper.updateProductFromRequest(product, updateProductRequest);

        return productMapper.mapToProductResponse(productRepository.save(product));

    }

    @Override
    public Page<ProductResponse> getProducts(int page, int size) {

        if (page < 0 || size < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid page or size");
        }

        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return productRepository.findAll(pageRequest).map(productMapper::mapToProductResponse);

    }

    @Override
    public Page<ProductResponse> getProductsByCategory(String categoryName, int page, int size) {

        if (page < 0 || size < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid page or size");
        }

        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return productRepository.findByCategoryName(category.getName(), pageRequest).map(productMapper::mapToProductResponse);


    }

    @Override
    public ProductResponse getProduct(String uuid) {

        if (uuid.isEmpty() || uuid.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid uuid");
        }

        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return productMapper.mapToProductResponse(product);

    }

    @Override
    public void deleteProduct(String uuid) {

        if (uuid.isEmpty() || uuid.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid uuid");
        }

        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        productRepository.delete(product);

    }

    @Override
    public Integer countProducts() {

        if (productRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found");
        }

        return productRepository.findAll().size();

    }
}
