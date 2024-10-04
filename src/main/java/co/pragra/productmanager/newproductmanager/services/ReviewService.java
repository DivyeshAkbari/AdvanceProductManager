package co.pragra.productmanager.newproductmanager.services;

import co.pragra.productmanager.newproductmanager.entity.Product;
import co.pragra.productmanager.newproductmanager.entity.Review;
import co.pragra.productmanager.newproductmanager.entity.User;
import co.pragra.productmanager.newproductmanager.exception.InvalidProductException;
import co.pragra.productmanager.newproductmanager.exception.InvalidReviewException;
import co.pragra.productmanager.newproductmanager.repo.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ProductService productService;

    private final ReviewRepo reviewRepo;

    private final UserService userService;

    public Review addReview(Review review,Long productId) {
        if(null==review)
        {
            throw new RuntimeException("review is null");
        }
        if(null!=productId) {
            Optional<Product> productOptional = productService.findById(productId);
            if(productOptional.isPresent())
            {
               if(review.getUser()!=null)
               {
                   Optional<User> userOptional = userService.findById(review.getUser().getId());

                   if(userOptional.isPresent())
                   {
                       review.setUser(userOptional.get());
                   }
                   Review savedReview= reviewRepo.save(review);
                   Product dbProduct = productOptional.get();
                   List<Review> reviews = dbProduct.getReviews();
                   reviews.add(savedReview);
                   dbProduct.setReviews(reviews);
                   productService.updateProduct(dbProduct);
                   return savedReview;
               }
            }
        } else
        {
            throw new InvalidProductException("Invalid product Id "+productId);
        }
        return null;
    }

    public Review updateReview(Review review) {

        if(null==review || review.getDescription().isEmpty() || review.getDescription().length()<=20)
        {
            throw new InvalidReviewException("Review Can not be null or empty");
        }

        Optional<Review> reviewOptional = reviewRepo.findById(review.getId());
        Review dbReview = reviewOptional.orElseThrow(() -> new InvalidReviewException("Review With this id  can not be found "+review.getId()));
        dbReview.setDescription(review.getDescription());
        return reviewRepo.save(dbReview);
    }
}
