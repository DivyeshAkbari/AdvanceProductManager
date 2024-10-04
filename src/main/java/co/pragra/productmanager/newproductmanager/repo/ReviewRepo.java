package co.pragra.productmanager.newproductmanager.repo;

import co.pragra.productmanager.newproductmanager.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {


}
