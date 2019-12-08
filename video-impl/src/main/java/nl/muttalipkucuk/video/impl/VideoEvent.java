package nl.muttalipkucuk.video.impl;

import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventShards;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTagger;
import com.lightbend.lagom.serialization.Jsonable;

public interface VideoEvent extends Jsonable, AggregateEvent<VideoEvent> {

    AggregateEventShards<VideoEvent> TAG = AggregateEventTag.sharded(VideoEvent.class, 4);

    @Override
    default AggregateEventTagger<VideoEvent> aggregateTag() {
        return TAG;
    }
}
