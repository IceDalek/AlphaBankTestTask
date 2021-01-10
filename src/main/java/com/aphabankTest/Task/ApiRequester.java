package com.aphabankTest.Task;

import com.aphabankTest.Task.exchanges.ExchangeResponse;
import com.aphabankTest.Task.exchanges.Exchanges;
import com.aphabankTest.Task.exchanges.Rates;
import com.aphabankTest.Task.giphy.GifUrl;
import com.aphabankTest.Task.giphy.Giphy;
import com.aphabankTest.Task.giphy.GiphyData;
import com.aphabankTest.Task.giphy.RandomGifResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.gson.GsonDecoder;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


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
    private static final File jsonConfigFile = new File("config.json");
    private static HashMap configs = new HashMap<>();

    static {
        try {
            configs = new ObjectMapper().readValue(jsonConfigFile, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            configs.put("giphyAPIKey", "QPayNCt8xoMomlYfVdySXOEGxAxXfiV2");
            configs.put("openExchangeApiKey", "5c17c676313b46b7a2c60572fae3873d");
        }
    }

    public static HashMap getConfigs() {
        return configs;
    }

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

     */
    public static GifUrl getRandomGifUrl(String currency) {
        String openExchangeApiKey = (String) configs.get("openExchangeApiKey");
        String giphyApiKey = (String) configs.get("giphyAPIKey");
        System.out.println(configs.get("openExchangeApiKey"));
        String curr = currency.toUpperCase();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String yesterdayDate = yesterday.format(fmt);
        ExchangeResponse yesterdayResponse = exchanges.getTomorrowExchangeData(yesterdayDate,
                openExchangeApiKey);
        Rates yesterdayRates = yesterdayResponse.getRates();
        ExchangeResponse exchangeResponse = exchanges.getTodayExchangeData(openExchangeApiKey);
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
            RandomGifResponse randomGifResponse = giphy.getGif("rich", giphyApiKey);
            GiphyData gif = randomGifResponse.getDataList();
            return new GifUrl(gif.getUrl(), gif.getEmbedUrl());
        }
        RandomGifResponse randomGifResponse = giphy.getGif("broke", giphyApiKey);
        GiphyData gif = randomGifResponse.getDataList();
        return new GifUrl(gif.getUrl(), gif.getEmbedUrl());
    }


}
