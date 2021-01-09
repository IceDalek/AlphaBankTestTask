package com.aphabankTest.Task;

import com.aphabankTest.Task.exchanges.ExchangeResponse;
import com.aphabankTest.Task.exchanges.Exchanges;
import com.aphabankTest.Task.exchanges.Rates;
import com.aphabankTest.Task.giphy.GifUrl;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
class TaskApplicationTests {


    @Autowired
    private MainController controller;

    private final Exchanges exchanges = Feign.builder()
            .decoder(new GsonDecoder())
            .target(Exchanges.class, "http://openexchangerates.org/api");

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }


    @Test
    public void ShouldReturnGifObject() {
        List<Endpoints> endpointsList = Arrays.asList(Endpoints.values());
        endpointsList.forEach(endpoint -> {
            GifUrl gifUrl = ApiRequester.getRandomGifUrl(endpoint.toString());
            assertNotNull("gif url can't be null", gifUrl);
            assertNotNull("embed url of object can't be null", gifUrl.embedUrl);
            assertNotNull("url of object can't be null", gifUrl.url);
            System.out.println(endpoint.toString() + " passed ");
        });
    }

    @Test
    public void checkReturnedCurrency() {
        List<Endpoints> endpointsList = Arrays.asList(Endpoints.values());
        endpointsList.forEach(endpoint -> {
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
            String currency = endpoint.toString();
            try {
                todayRate = todayRates.getCurrencyByName(currency);
                yesterdayRate = yesterdayRates.getCurrencyByName(currency);
                assertTrue("change must be greater than zero", todayRate >= 0f);
                assertTrue("change must be greater than zero", yesterdayRate >= 0f);
                System.out.printf("currency %s passed with yesterday rate: %f current rate: %f",
                        currency, yesterdayRate, todayRate);
            } catch (UnsupportedOperationException e) {
                fail("no such currency or it is unsupported");
            }
        });
    }
}
