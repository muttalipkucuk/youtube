package nl.muttalipkucuk.video.impl.error;

public class IsEmptyError extends DomainError {
    public IsEmptyError(String value) {
        this.message = String.format("Value '%s' is empty", value);
    }
}
