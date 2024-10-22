package com.liferay.workshop.holiday.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.workshop.holiday.api.client.service.HolidayApiClient;
import com.liferay.workshop.holiday.constants.HolidayPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + HolidayPortletKeys.GREETING_PORTLET,
                "mvc.command.name=" + HolidayPortletKeys.GREETING_ACTION
        },
        service = MVCActionCommand.class)
public class HolidayActionCommand extends BaseMVCActionCommand {

    @Reference
    private HolidayApiClient holidayApiClient;

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String userName = ParamUtil.get(actionRequest, HolidayPortletKeys.ATTR_USER_NAME, "John Doe");
        String result = holidayApiClient.checkHolidayToday().getDate();

        actionRequest.setAttribute(HolidayPortletKeys.ATTR_RESULT, result);
    }

}
