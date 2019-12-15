package nl.muttalipkucuk.video.impl.builder;

import nl.muttalipkucuk.video.api.Video;
import nl.muttalipkucuk.video.impl.VideoState;

public class ResponseBuilder {

    public static Video build(VideoState state) {
        return Video.builder()
            .id(state.getId())
            .name(state.getName())
            .description(state.getDescription())
            .likes(state.getLikes())
            .dislikes(state.getDislikes())
            .build();
    }
}
