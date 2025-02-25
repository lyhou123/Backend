package co.istad.backend.features.product;


import co.istad.backend.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByUuid(String uuid);

    Page<Product> findByCategoryName(String categoryName, PageRequest pageRequest);



}
