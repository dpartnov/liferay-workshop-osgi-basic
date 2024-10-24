package com.liferay.workshop.holiday.api.client.configuration;

import aQute.bnd.annotation.metatype.Configurable;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import java.util.Map;

//@Component(
//        configurationPid = "com.liferay.workshop.holiday.api.client.configuration.HolidayApiConfiguration",
//        immediate = true,
//        service = HolidayApiConfigurationProvider.class)
//@Designate(ocd = HolidayApiConfiguration.class)
public class HolidayApiConfigurationProviderImpl implements HolidayApiConfigurationProvider {

//    private HolidayApiConfiguration configuration;
//
//    @Activate //declares a method that will be invoked after the component has started
//    @Modified //marks the method that will be invoked when the component is modified, typically indicating that the @Reference(s) were changed
//    protected void activate(Map<String, Object> properties) {
//        configuration = Configurable.createConfigurable(HolidayApiConfiguration.class, properties);
//    }
//
//    @Override
//    public String getHolidayApiBaseUrl() {
//        return configuration.holidayApiBaseUrl();
//    }
}
