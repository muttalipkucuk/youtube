package nl.muttalipkucuk.video.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.CompressedJsonable;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.concurrent.Immutable;
import java.util.UUID;

@AllArgsConstructor
@Value
@JsonDeserialize
@Immutable
public class VideoState implements CompressedJsonable {

    @NonNull
    UUID id;

    @NonNull
    String name;

    @NonNull
    Option<String> description;

    @NonNull
    Integer likes;

    @NonNull
    Integer dislikes;

    // TODO: comments?

}
