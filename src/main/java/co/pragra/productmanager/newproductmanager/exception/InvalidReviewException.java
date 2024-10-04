package co.pragra.productmanager.newproductmanager.exception;

public class InvalidReviewException extends RuntimeException{

    public InvalidReviewException(String productMustNotBeNull) {
        super(productMustNotBeNull);
    }
}
