package nl.muttalipkucuk.video.impl.builder;

import nl.muttalipkucuk.video.api.VideoRequest;
import nl.muttalipkucuk.video.impl.VideoCommand;

import java.util.UUID;

public class CommandBuilder {

    public static VideoCommand.Create build(VideoRequest.Create req) {
        return VideoCommand.Create.builder()
            .id(UUID.randomUUID())
            .name(req.getName())
            .description(req.getDescription())
            .build();
    }

    public static VideoCommand.Get buildGet() {
        return VideoCommand.Get.builder().build();
    }

    public static VideoCommand.Upsert build(VideoRequest.Upsert req) {
        return VideoCommand.Upsert.builder()
            .name(req.getName())
            .description(req.getDescription())
            .likes(req.getLikes())
            .dislikes(req.getDislikes())
            .build();
    }

    public static VideoCommand.Remove buildRemove() {
        return VideoCommand.Remove.builder().build();
    }
}
