package com.aphabankTest.Task.exchanges;

import feign.Param;
import feign.RequestLine;

/**
 * interface with requests for openExchange API
 */
public interface Exchanges {
    /**
     * request for today currency changes
     */
    @RequestLine("GET /latest.json?app_id=2843821a52fe44c8a2d921d49e6bc377")
    ExchangeResponse getTodayExchangeData();

    /**
     * request for historical changes (any date)
     *
     * @param date this param must be ONLY in "yyyy-MM-dd" pattern;
     */
    @RequestLine("GET /historical/{date}.json?app_id=2843821a52fe44c8a2d921d49e6bc377")
    ExchangeResponse getTomorrowExchangeData(@Param("date") String date);
}
