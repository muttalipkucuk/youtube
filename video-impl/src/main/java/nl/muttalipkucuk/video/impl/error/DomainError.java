package nl.muttalipkucuk.video.impl.error;

import com.google.errorprone.annotations.Immutable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Immutable
@Builder
@NoArgsConstructor
public class DomainError {
    protected String message;
    protected Throwable throwable;
}
