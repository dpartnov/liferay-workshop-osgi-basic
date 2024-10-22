package com.liferay.workshop.holiday.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.workshop.holiday.constants.HolidayPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;


@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Holiday Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + HolidayPortletKeys.GREETING_PORTLET
        },
        service = Portlet.class
)
public class HolidayPortlet extends MVCPortlet {

    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
            throws IOException, PortletException {

        String result = (String) renderRequest.getAttribute(HolidayPortletKeys.ATTR_RESULT);

        if (result != null && !result.isEmpty()) {
            renderRequest.setAttribute(HolidayPortletKeys.ATTR_SHOW_BUTTON, false);
        } else {
            renderRequest.setAttribute(HolidayPortletKeys.ATTR_SHOW_BUTTON, true);
        }



        super.doView(renderRequest, renderResponse);
    }


}