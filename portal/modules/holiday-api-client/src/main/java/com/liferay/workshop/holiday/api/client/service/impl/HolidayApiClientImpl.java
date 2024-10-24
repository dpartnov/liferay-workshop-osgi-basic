package com.liferay.workshop.holiday.api.client.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.workshop.holiday.api.client.configuration.HolidayApiConfigurationProvider;
import com.liferay.workshop.holiday.api.client.dto.HolidayApiResponse;
import com.liferay.workshop.holiday.api.client.service.HolidayApiClient;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//@Component(
//        service = HolidayApiClient.class
//)
public class HolidayApiClientImpl  {

//    @Reference
//    private HolidayApiConfigurationProvider configurationProvider;
//
//    @Override
//    public HolidayApiResponse checkHolidayByDate(String date) {
//        return sendHolidayRequest(date);
//    }
//
//    @Override
//    public HolidayApiResponse checkHolidayToday() {
//        return sendHolidayRequest(null);
//    }
//
//    private HolidayApiResponse sendHolidayRequest(String dayParameter) {
//        String apiBaseUrl = configurationProvider.getHolidayApiBaseUrl();
//
//        try {
//            String requestUrl = apiBaseUrl + "/api/day";
//            if (dayParameter != null && !dayParameter.isBlank()) {
//                requestUrl += "/" + dayParameter;
//            }
//
//            URL url = new URL(requestUrl);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("GET");
//            int responseCode = httpURLConnection.getResponseCode();
//            StringBuffer response = new StringBuffer();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//                String inputLine;
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//            }
//
//            ObjectMapper mapper = new ObjectMapper();
//            return mapper.readValue(response.toString(), HolidayApiResponse.class);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
