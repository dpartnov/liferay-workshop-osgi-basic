package com.liferay.workshop.portlet.filter;

import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import java.io.IOException;
import java.util.concurrent.atomic.LongAdder;

/**
 * Portlet filters intercept requests and responses at the start of each portlet request processing phase.
 */
@Component(
        property = {
                "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsPortlet",
                "service.ranking:Integer=100"
        },
        service = PortletFilter.class
)
public class BlogsPortletFilter implements RenderFilter {

    private final LongAdder _count = new LongAdder();
    private final LongAdder _totalTime = new LongAdder();

    /**
     * Called by the portlet container each time a render request/response pair is passed through the chain due to a client request.
     */
    @Override
    public void doFilter(RenderRequest renderRequest, RenderResponse renderResponse, FilterChain filterChain) throws IOException, PortletException {

        long startTime = System.currentTimeMillis();

        filterChain.doFilter(renderRequest, renderResponse);

        long renderTime = (System.currentTimeMillis() - startTime) / 1000;

        _totalTime.add(renderTime);

        _count.increment();
        long count = _count.longValue();

        long averageRenderTime = _totalTime.longValue() / count;

        System.out.println(
                "Blogs portlet rendered in " + renderTime +
                        " ms with an average of " + averageRenderTime +
                        " ms out of " + count + " renders.");
    }

    /**
     * Called when the portlet filter is first deployed to Liferay and initialized within the portlet container.
     */
    @Override
    public void init(FilterConfig filterConfig) throws PortletException {

    }

    /**
     * Called to remove the portlet filter from service.
     */
    @Override
    public void destroy() {

    }
}
