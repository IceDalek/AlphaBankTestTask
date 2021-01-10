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
    @RequestLine("GET /latest.json?app_id={apiKey}")
    ExchangeResponse getTodayExchangeData(@Param("apiKey") String apiKey);

    /**
     * request for historical changes (any date)
     *
     * @param date this param must be ONLY in "yyyy-MM-dd" pattern;
     */
    @RequestLine("GET /historical/{date}.json?app_id={apiKey}")
    ExchangeResponse getTomorrowExchangeData(@Param("date") String date,
                                             @Param("apiKey") String apiKey);
}
