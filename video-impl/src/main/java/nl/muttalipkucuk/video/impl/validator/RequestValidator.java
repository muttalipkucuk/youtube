package nl.muttalipkucuk.video.impl.validator;

import akka.NotUsed;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import nl.muttalipkucuk.video.api.VideoRequest;
import nl.muttalipkucuk.video.impl.error.DomainError;
import nl.muttalipkucuk.video.impl.error.IsEmptyError;
import nl.muttalipkucuk.video.impl.error.MaxLengthExceededError;

public class RequestValidator {

    private static Integer MAX_LENGTH_NAME = 255;

    public static Validation<Seq<DomainError>, VideoRequest.Create> validate(VideoRequest.Create request) {
        return Validation.combine(
            validateEmptiness(request.getName()),
            validateAllowedLength(request.getName())
        ).ap((notUsed, notUsed2) -> request);
    }

    public static Validation<Seq<DomainError>, VideoRequest.Upsert> validate(VideoRequest.Upsert request) {
        return Validation.valid(request);
    }

    private static Validation<DomainError, NotUsed> validateEmptiness(String value) {
        return Option.when(!value.isEmpty(), Validation.<DomainError, NotUsed>valid(NotUsed.getInstance()))
            .getOrElse(Validation.invalid(new IsEmptyError(value)));
    }

    private static Validation<DomainError, NotUsed> validateAllowedLength(String value) {
        int length = value.length();
        return Option.when(length <= MAX_LENGTH_NAME, Validation.<DomainError, NotUsed>valid(NotUsed.getInstance()))
            .getOrElse(Validation.invalid(new MaxLengthExceededError(length, MAX_LENGTH_NAME)));
    }
}
