package nl.muttalipkucuk.video.api;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.broker.kafka.KafkaProperties;
import com.lightbend.lagom.javadsl.api.transport.Method;

import java.util.UUID;

import static com.lightbend.lagom.javadsl.api.Service.*;

public interface VideoService extends Service {

    ServiceCall<NotUsed, String> health();

    ServiceCall<VideoRequest.Create, Video> create();

    ServiceCall<NotUsed, Video> get(UUID id);

    ServiceCall<VideoRequest.Update, Video> update(UUID id);

    ServiceCall<NotUsed, Done> remove(UUID id);

    Topic<VideoEvent> videoEvents();

    @Override
    default Descriptor descriptor() {
        return named("video")
            .withCalls(
                restCall(Method.GET, "/health", this::health),
                restCall(Method.POST, "api/video", this::create),
                restCall(Method.GET, "api/video?id", this::get),
                restCall(Method.PUT, "api/video?id", this::update),
                restCall(Method.DELETE, "api/video?id", this::remove)
            )
            .withTopics(
                topic("video-events", this::videoEvents)
                    .withProperty(KafkaProperties.partitionKeyStrategy(), VideoEvent::getName)
            )
            .withAutoAcl(true);
    }


}
