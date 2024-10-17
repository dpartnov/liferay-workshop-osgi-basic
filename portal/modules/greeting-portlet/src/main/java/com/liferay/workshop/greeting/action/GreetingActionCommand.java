package com.liferay.workshop.greeting.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.workshop.greeting.constants.GreetingPortletKeys;
import com.liferay.workshop.helloworld.api.HelloWorldApi;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import static com.liferay.workshop.greeting.constants.GreetingPortletKeys.ATTR_RESULT;
import static com.liferay.workshop.greeting.constants.GreetingPortletKeys.ATTR_USER_NAME;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + GreetingPortletKeys.GREETING_PORTLET,
                "mvc.command.name=" + GreetingPortletKeys.GREETING_ACTION
        },
        service = MVCActionCommand.class)
public class GreetingActionCommand extends BaseMVCActionCommand {

    @Reference
    private HelloWorldApi helloWorldApi;

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String userName = ParamUtil.get(actionRequest, ATTR_USER_NAME, "John Doe");
        String result = helloWorldApi.hello(userName);

        actionRequest.setAttribute(ATTR_RESULT, result);
    }
}
