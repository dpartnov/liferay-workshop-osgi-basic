package com.liferay.workshop.holiday.api.client.configuration;

import aQute.bnd.annotation.metatype.Configurable;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import java.util.Map;

@Component(
        configurationPid = "com.liferay.workshop.holiday.api.client.configuration.HolidayApiConfiguration",
        immediate = true,
        service = HolidayApiConfigurationProvider.class)
@Designate(ocd = HolidayApiConfiguration.class)
public class HolidayApiConfigurationProviderImpl implements HolidayApiConfigurationProvider {

    private volatile HolidayApiConfiguration configuration;

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        configuration = Configurable.createConfigurable(HolidayApiConfiguration.class, properties);
    }

    @Override
    public String holidayApiBaseUrl() {
        return configuration.holidayApiBaseUrl();
    }
}
