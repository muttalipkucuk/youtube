package nl.muttalipkucuk.video.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import nl.muttalipkucuk.youtube.api.VideoService;

public class VideoModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(VideoService.class, VideoServiceImpl.class);
    }
}
