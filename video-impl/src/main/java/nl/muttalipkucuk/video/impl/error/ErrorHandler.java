package nl.muttalipkucuk.video.impl.error;

import com.lightbend.lagom.javadsl.api.deser.ExceptionMessage;
import com.lightbend.lagom.javadsl.api.transport.TransportErrorCode;
import com.lightbend.lagom.javadsl.api.transport.TransportException;
import io.vavr.collection.HashMap;
import io.vavr.collection.Seq;
import play.mvc.Http;

public class ErrorHandler {

    // TODO: Define default
    private static final TransportException DEFAULT_EXCEPTION = new TransportException(
        TransportErrorCode.InternalServerError, new ExceptionMessage("Unknown", ""));

    private static final HashMap<Class<? extends DomainError>, Integer> httpStatusCodeByDomainError = HashMap.of(
        IsEmptyError.class, Http.Status.BAD_REQUEST,
        MaxLengthExceededError.class, Http.Status.UNAUTHORIZED  // TODO Delete (used for testing)
    );

    public static TransportException handle(Seq<DomainError> errors) {
        System.out.println("Error(s) occurred: " + errors.map(DomainError::getMessage).mkString(", "));
        return DEFAULT_EXCEPTION;
//
//        errors
//            .map(DomainError::getClass)
//            .map(httpStatusCodeByDomainError::get)
//            .zip(errors)
//            .sorted();
//
//        return null;
    }

    public static TransportException handle(Throwable throwable) {
        System.out.println("Exceptionally");
        return DEFAULT_EXCEPTION;
    }
}
