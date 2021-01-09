package com.aphabankTest.Task.exchanges;

import com.google.gson.annotations.SerializedName;

/**
 * Class that contains response from openExchange Api
 */
public class ExchangeResponse {
    @SerializedName("rates")
    private Rates rates; // rates serializes from api response

    /**
     * Class that contains response from openExchange Api
     *
     * @return rates object with all currencies
     */
    public Rates getRates() {
        return rates;
    }
}
