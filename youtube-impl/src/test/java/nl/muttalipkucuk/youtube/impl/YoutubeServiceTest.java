package nl.muttalipkucuk.youtube.impl;

import org.junit.Test;

import nl.muttalipkucuk.video.api.GreetingMessage;
import nl.muttalipkucuk.video.api.YoutubeService;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.withServer;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class YoutubeServiceTest {
    @Test
    public void shouldStorePersonalizedGreeting() {
        withServer(defaultSetup().withCassandra(), server -> {
            YoutubeService service = server.client(YoutubeService.class);

            String msg1 = service.hello("Alice").invoke().toCompletableFuture().get(5, SECONDS);
            assertEquals("Hello, Alice!", msg1); // default greeting

            service.useGreeting("Alice").invoke(new GreetingMessage("Hi")).toCompletableFuture().get(5, SECONDS);
            String msg2 = service.hello("Alice").invoke().toCompletableFuture().get(5, SECONDS);
            assertEquals("Hi, Alice!", msg2);

            String msg3 = service.hello("Bob").invoke().toCompletableFuture().get(5, SECONDS);
            assertEquals("Hello, Bob!", msg3); // default greeting
        });
    }
}
