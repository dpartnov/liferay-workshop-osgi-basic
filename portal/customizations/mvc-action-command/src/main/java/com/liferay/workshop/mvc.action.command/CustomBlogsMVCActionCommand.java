package com.liferay.workshop.mvc.action.command;

import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
                "mvc.command.name=/blogs/edit_entry",
                "service.ranking:Integer=100"
        },
        service = MVCActionCommand.class
)
public class CustomBlogsMVCActionCommand  extends BaseMVCActionCommand {

    @Reference(
            target = "(component.name=com.liferay.blogs.web.internal.portlet.action.EditEntryMVCActionCommand)")
    protected MVCActionCommand mvcActionCommand;

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        System.out.println("Custom edit blog command called!");

        // Call the original service implementation
        mvcActionCommand.processAction(actionRequest, actionResponse);
    }
}
