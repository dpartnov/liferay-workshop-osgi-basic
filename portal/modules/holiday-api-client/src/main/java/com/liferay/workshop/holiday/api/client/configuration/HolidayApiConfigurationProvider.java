package com.liferay.workshop.holiday.api.client.configuration;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface HolidayApiConfigurationProvider {

    String getHolidayApiBaseUrl();
}
