package nl.muttalipkucuk.video.api;

import io.vavr.control.Option;
import lombok.Builder;
import lombok.NonNull;

import java.util.UUID;

@Builder
public class Video {

    @NonNull
    UUID id;

    @NonNull
    String name;

    @NonNull
    Option<String> description;

    @NonNull
    @Builder.Default
    Integer likes = 0;

    @NonNull
    @Builder.Default
    Integer dislikes = 0;

}
