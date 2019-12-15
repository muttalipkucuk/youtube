package nl.muttalipkucuk.video.impl;

import akka.Done;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;
import io.vavr.control.Option;
import lombok.*;
import lombok.experimental.Wither;

import javax.annotation.concurrent.Immutable;
import java.util.UUID;

public interface VideoCommand extends Jsonable {

    @AllArgsConstructor
    @JsonDeserialize
    @Value
    @Builder
    @Wither
    @Immutable
    @Getter
    final class Create implements VideoCommand, CompressedJsonable, PersistentEntity.ReplyType<VideoState> {
        @NonNull
        UUID id;
        @NonNull
        String name;
        @NonNull
        @Builder.Default
        Option<String> description = Option.none();
    }

    @AllArgsConstructor
    @JsonDeserialize
    @Value
    @Builder
    @Wither
    @Immutable
    @Getter
    final class Get implements VideoCommand, CompressedJsonable, PersistentEntity.ReplyType<VideoState> {
    }

    @AllArgsConstructor
    @JsonDeserialize
    @Value
    @Builder
    @Wither
    @Immutable
    @Getter
    final class Update implements VideoCommand, CompressedJsonable, PersistentEntity.ReplyType<VideoState> {
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

    @AllArgsConstructor
    @JsonDeserialize
    @Value
    @Builder
    @Wither
    @Immutable
    @Getter
    final class Remove implements VideoCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {
    }
}