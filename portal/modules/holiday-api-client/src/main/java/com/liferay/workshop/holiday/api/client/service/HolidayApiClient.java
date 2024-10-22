package com.liferay.workshop.holiday.api.client.service;

import aQute.bnd.annotation.ProviderType;
import com.liferay.workshop.holiday.api.client.dto.HolidayApiResponse;


//The interface’s @ProviderType annotation tells the service registry that anything implementing the interface is a provider.
@ProviderType
public interface HolidayApiClient {

    HolidayApiResponse checkHolidayToday();
}