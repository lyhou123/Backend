package co.istad.backend.features.category;

import co.istad.backend.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);

    Optional<Category> findByUuid(String uuid);

    Boolean existsByName(String name);

}
