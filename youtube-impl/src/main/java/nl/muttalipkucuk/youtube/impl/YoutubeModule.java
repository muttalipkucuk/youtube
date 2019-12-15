package nl.muttalipkucuk.youtube.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

import nl.muttalipkucuk.video.api.YoutubeService;

/**
 * The module that binds the YoutubeService so that it can be served.
 */
public class YoutubeModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(YoutubeService.class, YoutubeServiceImpl.class);
    }
}
