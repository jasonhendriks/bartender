package ca.hendriks.bartender.common.exception;

public class UnexpectedBartenderException extends RuntimeException {

    public UnexpectedBartenderException(final Exception e) {
        super(e);
    }

}
