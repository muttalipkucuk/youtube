package nl.muttalipkucuk.youtubestream.api;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.namedCall;

/**
 * The YouTube stream interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the YoutubeStreamService service.
 */
public interface YoutubeStreamService extends Service {
    /**
     * This stream is implemented by asking the hello service directly to say
     * hello to each passed in name. It requires the hello service to be up
     * and running to function.
     */
    ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> directStream();

    /**
     * This stream is implemented autonomously, it uses its own store, populated
     * by subscribing to the events published by the hello service, to say hello
     * to each passed in name. It can function even when the hello service is
     * down.
     */
    ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> autonomousStream();

    @Override
    default Descriptor descriptor() {
        return named("youtube-stream")
                .withCalls(
                        namedCall("direct-stream", this::directStream),
                        namedCall("auto-stream", this::autonomousStream)
                )
                .withAutoAcl(true);
    }
}
