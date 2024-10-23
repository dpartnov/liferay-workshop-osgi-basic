package com.liferay.workshop.holiday.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.workshop.holiday.api.client.dto.HolidayApiResponse;
import com.liferay.workshop.holiday.api.client.service.HolidayApiClient;
import com.liferay.workshop.holiday.constants.HolidayPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + HolidayPortletKeys.HOLIDAY_PORTLET,
                "mvc.command.name=" + HolidayPortletKeys.HOLIDAY_ACTION
        },
        service = MVCActionCommand.class)
public class HolidayActionCommand extends BaseMVCActionCommand {

    @Reference
    private HolidayApiClient holidayApiClient;

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String dateInputValue = ParamUtil.get(actionRequest, HolidayPortletKeys.ATTR_DATE, "2024-01-01");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate.parse(dateInputValue, formatter);
        } catch (Exception e) {
            SessionErrors.add(actionRequest, "error");
            return;
        }

        HolidayApiResponse result = holidayApiClient.checkHolidayByDate(dateInputValue);

        actionRequest.setAttribute(HolidayPortletKeys.ATTR_IS_HOLIDAY_RESULT, result.getIsHoliday());
        actionRequest.setAttribute(HolidayPortletKeys.ATTR_HOLIDAY_NAME_RESULT, result.getHolidayName());
    }

}
