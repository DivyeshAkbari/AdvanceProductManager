package co.pragra.productmanager.newproductmanager.exception;

public class InvalidProductException extends RuntimeException {
    public InvalidProductException(String productMustNotBeNull) {
        super(productMustNotBeNull);
    }
}
