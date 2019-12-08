package nl.muttalipkucuk.youtube.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.broker.Topic;
import com.lightbend.lagom.javadsl.api.broker.kafka.KafkaProperties;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.*;

public interface VideoService extends Service {

    ServiceCall<NotUsed, NotUsed> health();

    Topic<VideoEvent> videoEvents();

    @Override
    default Descriptor descriptor() {
        return named("video")
                .withCalls(
                        restCall(Method.GET, "/health", this::health)
                )
                .withTopics(
                        topic("video-events", this::videoEvents)
                            .withProperty(KafkaProperties.partitionKeyStrategy(), VideoEvent::getName)
                )
                .withAutoAcl(true);
    }


}
