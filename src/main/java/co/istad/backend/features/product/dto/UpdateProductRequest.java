package co.istad.backend.features.product.dto;

public record UpdateProductRequest(
        String title,
        String description,
        String price,
        String thumbnail
) {
}
