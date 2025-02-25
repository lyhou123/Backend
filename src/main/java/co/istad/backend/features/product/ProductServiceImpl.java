package co.istad.backend.features.product;

import co.istad.backend.domain.Product;
import co.istad.backend.features.product.dto.ProductRequest;
import co.istad.backend.features.product.dto.ProductResponse;
import co.istad.backend.features.product.dto.UpdateProductRequest;
import co.istad.backend.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        Product product = productMapper.mapToProduct(request);

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
    public ProductResponse getProduct(String uuid) {

        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return productMapper.mapToProductResponse(product);

    }

    @Override
    public void deleteProduct(String uuid) {

        Product product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        productRepository.delete(product);

    }
}
