package co.istad.backend.features.product;

import co.istad.backend.features.product.dto.ProductRequest;
import co.istad.backend.features.product.dto.ProductResponse;
import co.istad.backend.features.product.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;

/**
 * ProductService is an interface for product service.
 * @version 1.0
 * @author lyhou
 * @see ProductServiceImpl
 */
public interface ProductService {

    /**
     * Create a product.
     * @param request the product request
     * @return the product response
     */
    ProductResponse createProduct(ProductRequest request);


    /**
     * Update a product.
     * @param uuid the product uuid
     * @param updateProductRequest the product request
     * @return the product response
     */
    ProductResponse updateProduct(String uuid, UpdateProductRequest updateProductRequest);

    /**
     * Get products.
     * @param page the page number
     * @param size the page size
     * @return the product page
     */
    Page<ProductResponse> getProducts(int page, int size);

    /**
     * Get a product.
     * @param uuid the product uuid
     * @return the product response
     */
    ProductResponse getProduct(String uuid);

    /**
     * Delete a product.
     * @param uuid the product uuid
     */
    void deleteProduct(String uuid);

}
