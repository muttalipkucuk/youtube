package nl.muttalipkucuk.video.impl;

import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import java.util.Optional;

public class VideoEntity extends PersistentEntity<VideoCommand, VideoEvent, VideoState> {

    @Override
    public Behavior initialBehavior(Optional<VideoState> snapshotState) {
        this.entityId();
        // TODO
        return null;
    }
}
