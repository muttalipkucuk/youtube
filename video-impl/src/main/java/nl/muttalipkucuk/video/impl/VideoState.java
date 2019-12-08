package nl.muttalipkucuk.video.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.CompressedJsonable;
import lombok.Value;

@Value
@JsonDeserialize
public class VideoState implements CompressedJsonable {

    public final String video;

    @JsonCreator
    VideoState(String video) {
        this.video = video;
    }
}
