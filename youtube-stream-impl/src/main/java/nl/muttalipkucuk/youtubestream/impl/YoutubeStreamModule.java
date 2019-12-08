package nl.muttalipkucuk.youtubestream.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

import nl.muttalipkucuk.youtube.api.YoutubeService;
import nl.muttalipkucuk.youtubestream.api.YoutubeStreamService;

/**
 * The module that binds the YoutubeStreamService so that it can be served.
 */
public class YoutubeStreamModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        // Bind the YoutubeStreamService service
        bindService(YoutubeStreamService.class, YoutubeStreamServiceImpl.class);
        // Bind the YoutubeService client
        bindClient(YoutubeService.class);
        // Bind the subscriber eagerly to ensure it starts up
        bind(YoutubeStreamSubscriber.class).asEagerSingleton();
    }
}
