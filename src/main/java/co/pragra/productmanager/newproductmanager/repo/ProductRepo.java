package co.pragra.productmanager.newproductmanager.repo;


import co.pragra.productmanager.newproductmanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM new_schema.table_product where product_id <:cost;",nativeQuery = true)
    List<Product> findCheaperProduct(double cost);
}
