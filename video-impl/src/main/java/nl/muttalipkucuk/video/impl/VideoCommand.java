package nl.muttalipkucuk.video.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;
import lombok.Value;

public interface VideoCommand extends Jsonable {

    // TODO: Add commands

    @Value
    @JsonDeserialize
    // TODO: Change String to Video
    final class GetVideo implements VideoCommand, CompressedJsonable, PersistentEntity.ReplyType<String> {
        public final String video;

        @JsonCreator
        GetVideo(String video) {
            // TODO: message etc
            this.video = Preconditions.checkNotNull(video, "message");
        }
    }
}
