package nl.muttalipkucuk.video.impl;

import akka.Done;
import akka.NotUsed;
import akka.japi.Pair;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.broker.TopicProducer;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import nl.muttalipkucuk.video.api.Video;
import nl.muttalipkucuk.video.api.VideoRequest;
import nl.muttalipkucuk.video.api.VideoService;
import nl.muttalipkucuk.video.impl.builder.CommandBuilder;
import nl.muttalipkucuk.video.impl.builder.ResponseBuilder;
import nl.muttalipkucuk.video.impl.error.ErrorHandler;
import nl.muttalipkucuk.video.impl.validator.RequestValidator;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class VideoServiceImpl implements VideoService {
    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public VideoServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
    }

    @Override
    public ServiceCall<NotUsed, String> health() {
        return request -> CompletableFuture.completedFuture(
            String.format("Video service is OK. ")  // TODO: Create common and add current date time.
        );
    }

    @Override
    public ServiceCall<VideoRequest.Create, Video> create() {
        return req -> RequestValidator.validate(req)
            .map(CommandBuilder::build)
            .map(this::createVideo)
            .fold(
                err -> {
                    throw ErrorHandler.handle(err);
                },
                x -> x.thenApply(ResponseBuilder::build)
            )
            .exceptionally(throwable -> {
                throw ErrorHandler.handle(throwable);
            });
    }

    @Override
    public ServiceCall<NotUsed, Video> get(UUID id) {
        return req -> getVideo(id)
            .thenApply(ResponseBuilder::build)
            .exceptionally(throwable -> {
                throw ErrorHandler.handle(throwable);
            });
    }

    @Override
    public ServiceCall<VideoRequest.Upsert, Video> upsert(UUID id) {
        return req -> RequestValidator.validate(req)
            .map(validatedRequest -> upsertVideo(id, CommandBuilder.build(validatedRequest)))
            .fold(
                err -> {
                    throw ErrorHandler.handle(err);
                },
                x -> x.thenApply(ResponseBuilder::build)
            )
            .exceptionally(throwable -> {
                throw ErrorHandler.handle(throwable);
            });
    }

    @Override
    public ServiceCall<NotUsed, Done> remove(UUID id) {
        return req -> removeVideo(id)
            .exceptionally(throwable -> {
                throw ErrorHandler.handle(throwable);
            });
    }

    private CompletionStage<VideoState> createVideo(VideoCommand.Create cmd) {
        return persistentEntityRegistry
            .refFor(VideoEntity.class, cmd.getId().toString())
            .ask(cmd);
    }

    private CompletionStage<VideoState> getVideo(UUID id) {
        return persistentEntityRegistry
            .refFor(VideoEntity.class, id.toString())
            .ask(CommandBuilder.buildGet());
    }

    private CompletionStage<VideoState> upsertVideo(UUID id, VideoCommand.Upsert cmd) {
        return persistentEntityRegistry
            .refFor(VideoEntity.class, id.toString())
            .ask(cmd);
    }

    private CompletionStage<Done> removeVideo(UUID id) {
        return persistentEntityRegistry
            .refFor(VideoEntity.class, id.toString())
            .ask(CommandBuilder.buildRemove());
    }

    @Override
    public Topic<nl.muttalipkucuk.video.api.VideoEvent> videoEvents() {
        return TopicProducer.taggedStreamWithOffset(VideoEvent.TAG.allTags(), (tag, offset) ->
            persistentEntityRegistry.eventStream(tag, offset).map(eventAndOffset -> {

                // TODO
                return Pair.create(null, eventAndOffset.second());
            })
        );
    }
}
