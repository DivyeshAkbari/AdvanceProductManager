package co.pragra.productmanager.newproductmanager.rest;

import co.pragra.productmanager.newproductmanager.entity.Review;
import co.pragra.productmanager.newproductmanager.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService reviewService;

    @PostMapping("/api/product/{id}/review")
    public Review addReviww(@RequestBody Review review, @PathVariable Long id) {

        return reviewService.addReview(review, id);
    }

    @PutMapping("/api/product/review")
    public ResponseEntity<Review> updateReview(@RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReview(review));
    }
}
///api/review