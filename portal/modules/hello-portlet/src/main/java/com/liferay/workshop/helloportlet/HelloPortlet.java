package com.liferay.workshop.helloportlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import java.io.IOException;

import static com.liferay.workshop.helloportlet.HelloPortletKeys.HELLO_TEXT_RESULT;
import static com.liferay.workshop.helloportlet.HelloPortletKeys.PORTLET_NAME;

@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Hello Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.config-template=/configuration.jsp",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + PORTLET_NAME}
        ,
        service = Portlet.class
)
public class HelloPortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        //Set properties to render request for FE view:
        renderRequest.setAttribute(HELLO_TEXT_RESULT, "RESULT");

        super.doView(renderRequest, renderResponse);
    }
}
