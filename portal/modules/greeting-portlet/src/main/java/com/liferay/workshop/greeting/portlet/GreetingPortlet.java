package com.liferay.workshop.greeting.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.workshop.greeting.constants.GreetingPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;

import static com.liferay.workshop.greeting.constants.GreetingPortletKeys.ATTR_RESULT;
import static com.liferay.workshop.greeting.constants.GreetingPortletKeys.ATTR_SHOW_BUTTON;


@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Greeting Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + GreetingPortletKeys.GREETING_PORTLET
        },
        service = Portlet.class
)
public class GreetingPortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        String result = (String) renderRequest.getAttribute(ATTR_RESULT);

        if (result != null && !result.isEmpty()) {
            renderRequest.setAttribute(ATTR_SHOW_BUTTON, false);
        } else {
            renderRequest.setAttribute(ATTR_SHOW_BUTTON, true);
        }

        super.doView(renderRequest, renderResponse);
    }


}