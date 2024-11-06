package com.liferay.workshop.service.override;

import com.liferay.workshop.holiday.api.client.dto.HolidayApiResponse;
import com.liferay.workshop.holiday.api.client.service.HolidayApiClient;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = "service.ranking:Integer=100", service = HolidayApiClient.class)
public class HolidayApiClientOverride implements HolidayApiClient {

    @Reference(
            target = "(component.name=com.liferay.workshop.holiday.api.client.service.impl.HolidayApiClientImpl)"
    )
    private HolidayApiClient _originalClient;

    @Override
    public HolidayApiResponse checkHolidayToday() {
        return _originalClient.checkHolidayToday();
    }

    @Override
    public HolidayApiResponse checkHolidayByDate(String date) {

        System.out.println("Called holiday API from override implementation!");
        return _originalClient.checkHolidayByDate(date);
    }
}
