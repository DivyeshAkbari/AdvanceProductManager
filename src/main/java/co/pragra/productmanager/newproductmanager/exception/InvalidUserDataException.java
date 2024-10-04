package co.pragra.productmanager.newproductmanager.exception;

public class InvalidUserDataException extends RuntimeException {

    public InvalidUserDataException(String userCanNotBeNull) {
        super(userCanNotBeNull);
    }
}
