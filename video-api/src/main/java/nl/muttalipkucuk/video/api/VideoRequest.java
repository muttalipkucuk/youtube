package nl.muttalipkucuk.video.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.concurrent.Immutable;

public interface VideoRequest {

    @Immutable
    @JsonDeserialize
    @Value
    @Builder
    @AllArgsConstructor
    class Create {
        @NonNull
        String name;

        @NonNull
        Option<String> description;
    }

    @Immutable
    @JsonDeserialize
    @Value
    @Builder
    @AllArgsConstructor
    class Upsert {
        @NonNull
        @Builder.Default
        Option<String> name = Option.none();

        @NonNull
        @Builder.Default
        Option<String> description = Option.none();

        @NonNull
        @Builder.Default
        Option<Integer> likes = Option.none();

        @NonNull
        @Builder.Default
        Option<Integer> dislikes = Option.none();
    }

}
