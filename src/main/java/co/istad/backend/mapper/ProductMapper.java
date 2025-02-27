package co.istad.backend.mapper;

import co.istad.backend.domain.Product;

import co.istad.backend.features.product.dto.ProductResponse;
import co.istad.backend.features.product.dto.UpdateProductRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")

public interface ProductMapper {



    @Mapping(target = "category", source = "category.name")
    ProductResponse mapToProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromRequest(@MappingTarget Product product, UpdateProductRequest updateProductRequest);

}
