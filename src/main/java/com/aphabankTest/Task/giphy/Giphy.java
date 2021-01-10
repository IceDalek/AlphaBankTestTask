package com.aphabankTest.Task.giphy;

import feign.Param;
import feign.RequestLine;

public interface Giphy {
    @RequestLine("GET /v1/gifs/random?api_key={apiKey}&tag={tag}")
    RandomGifResponse getGif(@Param("tag") String tag,
                             @Param("apiKey") String apiKey);
}