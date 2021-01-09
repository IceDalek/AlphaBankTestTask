package com.aphabankTest.Task;

import com.aphabankTest.Task.exchanges.ExchangeResponse;
import com.aphabankTest.Task.exchanges.Exchanges;
import com.aphabankTest.Task.exchanges.Rates;
import com.aphabankTest.Task.giphy.GifUrl;
import com.aphabankTest.Task.giphy.Giphy;
import com.aphabankTest.Task.giphy.GiphyData;
import com.aphabankTest.Task.giphy.RandomGifResponse;
import feign.Feign;
import feign.gson.GsonDecoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that allows to request to Giphy API and OpenExchange API using OpenFeign
 */
public class ApiRequester {
    private static final Giphy giphy = Feign.builder()
            .decoder(new GsonDecoder())
            .target(Giphy.class, "http://api.giphy.com"); // Giphy api url
    private static final Exchanges exchanges = Feign.builder()
            .decoder(new GsonDecoder())
            .target(Exchanges.class, "http://openexchangerates.org/api");// Openexchangerates api url

    /**
     * private constructor.
     * Use only static methods
     */
    private ApiRequester() {
    }

    /**
     * this method compares yesterday and today currency rates
     * if today rate is greater than yesterday it returns the GifUrl object
     * that contains URL of random gif from Giphy by "rich" tag,
     * else by "broke tag"
     *
     * @param currency name
     * @return gif object if no exception occurred else return null
     * @throws UnsupportedOperationException
     */
    public static GifUrl getRandomGifUrl(String currency) throws UnsupportedOperationException {
        String curr = currency.toUpperCase();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String yesterdayDate = yesterday.format(fmt);
        ExchangeResponse yesterdayResponse = exchanges.getTomorrowExchangeData(yesterdayDate);
        Rates yesterdayRates = yesterdayResponse.getRates();
        ExchangeResponse exchangeResponse = exchanges.getTodayExchangeData();
        Rates todayRates = exchangeResponse.getRates();
        float todayRate;
        float yesterdayRate;
        try {
            todayRate = todayRates.getCurrencyByName(curr);
            yesterdayRate = yesterdayRates.getCurrencyByName(curr);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            System.out.println("Такой валюты не существует или она не поддерживается");
            return null;
        }

        if (todayRate > yesterdayRate) {
            RandomGifResponse randomGifResponse = giphy.getGif("rich");
            GiphyData gif = randomGifResponse.getDataList();
            GifUrl gifUrl = new GifUrl(gif.getUrl(), gif.getEmbedUrl());
            return gifUrl;
        }
        RandomGifResponse randomGifResponse = giphy.getGif("broke");
        GiphyData gif = randomGifResponse.getDataList();
        GifUrl gifUrl = new GifUrl(gif.getUrl(), gif.getEmbedUrl());
        return gifUrl;
    }


}
