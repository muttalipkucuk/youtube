package nl.muttalipkucuk.youtubestream.impl;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import nl.muttalipkucuk.video.api.YoutubeService;
import nl.muttalipkucuk.youtubestream.api.YoutubeStreamService;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * Implementation of the YoutubeStreamService.
 */
public class YoutubeStreamServiceImpl implements YoutubeStreamService {
    private final YoutubeService youtubeService;
    private final YoutubeStreamRepository repository;

    @Inject
    public YoutubeStreamServiceImpl(YoutubeService youtubeService, YoutubeStreamRepository repository) {
        this.youtubeService = youtubeService;
        this.repository = repository;
    }

    @Override
    public ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> directStream() {
        return hellos -> completedFuture(
                hellos.mapAsync(8, name -> youtubeService.hello(name).invoke()));
    }

    @Override
    public ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> autonomousStream() {
        return hellos -> completedFuture(
                hellos.mapAsync(8, name -> repository.getMessage(name).thenApply(message ->
                        String.format("%s, %s!", message.orElse("Hello"), name)
                ))
        );
    }
}
