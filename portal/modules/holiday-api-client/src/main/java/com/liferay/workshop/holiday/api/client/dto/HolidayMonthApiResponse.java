package com.liferay.workshop.holiday.api.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayMonthApiResponse {

    private String nominative;
    private String genitive;
}
