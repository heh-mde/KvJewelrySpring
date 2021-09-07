package ua.hehmde.kvjewelry.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.hehmde.kvjewelry.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p.id  FROM Product p")
    List<Long> findIdByVendorcodeNotNullOrderByCreatedDesc(Pageable pageable);

    @EntityGraph(value = "product-metals-stones")
    List<Product> findByIdIn(List<Long> ids);
}
