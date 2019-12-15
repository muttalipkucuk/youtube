package nl.muttalipkucuk.video.impl.error;

public class MaxLengthExceededError extends DomainError {
    public MaxLengthExceededError(Integer actualLength, Integer maxAllowedLength) {
        this.message = String.format("Maximum length of %s is exceeded. Actual length: %s", actualLength, maxAllowedLength);
    }
}
