package nl.muttalipkucuk.video.impl;

import akka.NotUsed;
import akka.japi.Pair;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.broker.TopicProducer;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import nl.muttalipkucuk.youtube.api.VideoService;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class VideoServiceImpl implements VideoService {
    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public VideoServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
    }

    @Override
    public ServiceCall<NotUsed, NotUsed> health() {
        return request -> CompletableFuture.completedFuture(NotUsed.getInstance());
    }

    @Override
    public Topic<nl.muttalipkucuk.youtube.api.VideoEvent> videoEvents() {
        return TopicProducer.taggedStreamWithOffset(VideoEvent.TAG.allTags(), (tag, offset) ->
                persistentEntityRegistry.eventStream(tag, offset).map(eventAndOffset -> {

                    // TODO
                    return Pair.create(null, eventAndOffset.second());
                })
        );
    }
}
