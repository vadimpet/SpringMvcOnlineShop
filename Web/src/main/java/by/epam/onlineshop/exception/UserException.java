package by.epam.onlineshop.exception;

public class UserException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "No user found with current params";

    public UserException() {
        super(DEFAULT_MESSAGE);
    }

    public UserException(String errorMessage){
        super(errorMessage);
    }
}
