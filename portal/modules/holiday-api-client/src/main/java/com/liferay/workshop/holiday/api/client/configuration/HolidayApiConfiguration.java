package com.liferay.workshop.holiday.api.client.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "HolidayApi",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM)
@ObjectClassDefinition(
        id = "com.liferay.workshop.holiday.api.client.configuration.HolidayApiConfiguration",
        name = "Holiday API configuration")
public interface HolidayApiConfiguration {

    @AttributeDefinition(
            defaultValue = {"false"},
            required = false)
    String holidayApiBaseUrl();
}
