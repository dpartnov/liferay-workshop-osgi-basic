package com.liferay.workshop.helloportlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.workshop.helloportlet.HelloPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;

import static com.liferay.workshop.helloportlet.HelloPortletKeys.HELLO_TEXT_RESULT;
import static com.liferay.workshop.helloportlet.HelloPortletKeys.NAME_TEXT_INPUT;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + HelloPortletKeys.PORTLET_NAME,
                "mvc.command.name=" + HelloPortletKeys.CREATE_GREETING_ACTION
        },
        service = MVCActionCommand.class)
public class HelloAction extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        //Load properties from FE:
        String userName = ParamUtil.get(actionRequest, NAME_TEXT_INPUT, "John Doe");

        userName += " HELLO!";
        final PortletSession portletSession = actionRequest.getPortletSession();
        portletSession.setAttribute(HELLO_TEXT_RESULT, userName);
    }
}
