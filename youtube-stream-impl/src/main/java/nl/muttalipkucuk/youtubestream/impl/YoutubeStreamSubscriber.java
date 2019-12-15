package nl.muttalipkucuk.youtubestream.impl;

import akka.Done;
import akka.stream.javadsl.Flow;

import nl.muttalipkucuk.video.api.YoutubeEvent;
import nl.muttalipkucuk.video.api.YoutubeService;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

/**
 * This subscribes to the YoutubeService event stream.
 */
public class YoutubeStreamSubscriber {
    @Inject
    public YoutubeStreamSubscriber(YoutubeService youtubeService, YoutubeStreamRepository repository) {
        // Create a subscriber
        youtubeService.helloEvents().subscribe()
                // And subscribe to it with at least once processing semantics.
                .atLeastOnce(
                        // Create a flow that emits a Done for each message it processes
                        Flow.<YoutubeEvent>create().mapAsync(1, event -> {
                            if (event instanceof YoutubeEvent.GreetingMessageChanged) {
                                YoutubeEvent.GreetingMessageChanged messageChanged = (YoutubeEvent.GreetingMessageChanged) event;
                                // Update the message
                                return repository.updateMessage(messageChanged.getName(), messageChanged.getMessage());
                            } else {
                                // Ignore all other events
                                return CompletableFuture.completedFuture(Done.getInstance());
                            }
                        })
                );
    }
}
