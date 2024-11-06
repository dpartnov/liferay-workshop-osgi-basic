package com.liferay.workshop.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet filters can both pre-process requests as they arrive and post-process responses before
 * they go to the client browser.
 * Common filter use cases: Logging, Auditing, Transaction management, Security.
 *
 * You can use patterns in descriptors to map the filters to servlet URLs.
 * When requests arrive at these URLs, your filters process them.
 *
 * Whenever you alter the order of servlet filters,
 * it’s recommended to set the after-filter to Absolute Redirects Filter.
 * This lowers the risk of various bugs.
 */
@Component(
        property = {
                "after-filter=Absolute Redirects Filter",
                "servlet-context-name=", //This value must always be set to an empty string (required)
                "servlet-filter-name=Custom Servlet Filter",
                "url-pattern=/workshop/*"
        },
        service = Filter.class
)
public class CustomServletFilter extends BaseFilter {

    /**
     * Override the processFilter method to add the desired functionality.
     */
    @Override
    protected void processFilter(HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse, FilterChain filterChain) throws Exception {

        System.out.println(
                "Invoking custom servlet filter for request URI " + httpServletRequest.getRequestURI());

        httpServletResponse.addHeader(
                "X-Custom-Servlet-Filter", httpServletRequest.getRequestURI());

        processFilter(
                CustomServletFilter.class.getName(), httpServletRequest,
                httpServletResponse, filterChain);
    }


    @Override
    protected Log getLog() {
        return LogFactoryUtil.getLog(CustomServletFilter.class);
    }
}
