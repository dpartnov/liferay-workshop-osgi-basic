package com.liferay.workshop.holiday.api.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayApiResponse {

    private String date;
    private String dayNumber;
    private String dayInWeek;
    private String monthNumber;
    private String year;
    private String name;
    private Boolean isHoliday;
    private String holidayName;
    private HolidayMonthApiResponse month;

}
